package com.example.mycalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvtext: TextView?=null
    var lastNumeric:Boolean = false
    var lastDot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     tvtext=findViewById<TextView>(R.id.tvtext)

    }
    fun onDigit(view: View){
        tvtext?.append((view as Button).text)
        lastNumeric=true
    }
    fun onClear(view: View){
        tvtext?.text=""
        lastNumeric=false
        lastDot=false

    }
    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvtext?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }
     fun onOperator(view: View){
         tvtext?.text?.let {
             if(lastNumeric && !isOperatorAdded(it.toString())){
                 tvtext?.append((view as Button).text)
                 lastNumeric=false
                 lastNumeric=false
             }
         }
     }
    fun oneqaul(view: View){
        if(lastNumeric){
            var tvValue = tvtext?.text.toString()
            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.equals("69"))
                    tvtext?.text="Hmm naughty!!"


                if(tvValue.contains("-")){
                    val splitValue=tvValue.split("-")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.equals("-")){
                        one= prefix + one
                    }
                    tvtext?.text=removeZero((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitValue=tvValue.split("+")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvtext?.text=removeZero((one.toDouble() + two.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    val splitValue=tvValue.split("/")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvtext?.text=removeZero((one.toDouble() / two.toDouble()).toString())
                }else if(tvValue.contains("*")){
                    val splitValue=tvValue.split("*")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvtext?.text=removeZero((one.toDouble() * two.toDouble()).toString())
                }



            }catch(e:ArithmeticException){
                e.printStackTrace()
                tvtext?.setText("ERROR")
            }
        }
    }
    private fun removeZero(result:String):String{
        var value=result
        if(result.contains(".0")){
            value=result.substring(0, result.length - 2)
        }
        return value
    }
    private fun isOperatorAdded(value:String):Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("+") || value.contains("*") ||value.contains("-")
        }
    }
}