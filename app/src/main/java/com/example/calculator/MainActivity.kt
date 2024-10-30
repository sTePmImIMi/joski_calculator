package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {                      //вызывается при первом запуске программы
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
                                                                                        ////setOnClickListener повесили обработку события на кнопку
        }                                                                               //setTextFields символ/строка которая будет написана в текстовое поле
        findViewById<TextView>(R.id.btn_0).setOnClickListener { setTextFields("0") }
        findViewById<TextView>(R.id.btn_1).setOnClickListener { setTextFields("1") }
        findViewById<TextView>(R.id.btn_2).setOnClickListener { setTextFields("2") }
        findViewById<TextView>(R.id.btn_3).setOnClickListener { setTextFields("3") }
        findViewById<TextView>(R.id.btn_4).setOnClickListener { setTextFields("4") }
        findViewById<TextView>(R.id.btn_5).setOnClickListener { setTextFields("5") }
        findViewById<TextView>(R.id.btn_6).setOnClickListener { setTextFields("6") }
        findViewById<TextView>(R.id.btn_7).setOnClickListener { setTextFields("7") }
        findViewById<TextView>(R.id.btn_8).setOnClickListener { setTextFields("8") }
        findViewById<TextView>(R.id.btn_9).setOnClickListener { setTextFields("9") }
        findViewById<TextView>(R.id.btn_1sk).setOnClickListener { setTextFields("(") }
        findViewById<TextView>(R.id.btn_2sk).setOnClickListener { setTextFields(")") }
        findViewById<TextView>(R.id.btn_del).setOnClickListener { setTextFields("/") }
        findViewById<TextView>(R.id.btn_minus).setOnClickListener { setTextFields("-") }
        findViewById<TextView>(R.id.btn_ravno).setOnClickListener { setTextFields("=") }
        findViewById<TextView>(R.id.btn_umn).setOnClickListener { setTextFields("*") }
        findViewById<TextView>(R.id.btn_plus).setOnClickListener { setTextFields("+") }
        findViewById<TextView>(R.id.btn__).setOnClickListener { setTextFields(".") }

        findViewById<TextView>(R.id.btn_AC).setOnClickListener {
            findViewById<TextView>(R.id.math_operation).text = ""           //текст очищается .text = ""
            findViewById<TextView>(R.id.result_text).text = ""
        }

        findViewById<TextView>(R.id.btn_back).setOnClickListener {
            val str = findViewById<TextView>(R.id.math_operation).text.toString()
            if (str.isNotEmpty())                                           //true если строка не пуста, false наоборот
                findViewById<TextView>(R.id.math_operation).text = str.substring(0, str.length - 1) //substring позволяет обрезать строку по определенным критериям

            findViewById<TextView>(R.id.result_text).text = "" //если будем нажимать на конпку, будем дополнительно очищать поле результата
        }

        findViewById<TextView>(R.id.btn_ravno).setOnClickListener {
            try {
                val ex = ExpressionBuilder(findViewById<TextView>(R.id.math_operation).text.toString()).build() //класс ExpressionBuilder на основе которого мы создаем операции
                val result = ex.evaluate() //функция evaluate() позволяет высчитать мат операцию из той строки которую мы передали из объекта ExpressionBuilder

                val longRes = result.toLong()
                if (result == longRes.toDouble())
                    findViewById<TextView>(R.id.result_text).text = longRes.toString()
                else
                    findViewById<TextView>(R.id.result_text).text = result.toString()

            } catch (e:Exception) {             //класс Exception позволяет найти любую ошибку которая может произойти в программе; в catch мы будем отслеживать ошибки с exception
                Log.d("Ошибка", "сообщение: ${e.message}")  //если будет ошибка, мы будем это выводить в терминал
            }
        }

    }
    fun setTextFields(str: String) {
        if (findViewById<TextView>(R.id.result_text).text!="")
            findViewById<TextView>(R.id.math_operation).text = findViewById<TextView>(R.id.result_text).text
            findViewById<TextView>(R.id.result_text).text = ""

        findViewById<TextView>(R.id.math_operation).append(str)
    }


}
