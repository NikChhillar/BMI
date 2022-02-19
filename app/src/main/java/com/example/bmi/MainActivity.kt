package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalculate.setOnClickListener {
            val weight = eTWeight.text.toString()
            val height = eTHeight.text.toString()
            if (validateInput(weight, height)){
                val bmi = weight.toFloat()/((height.toFloat()/100)*(height.toFloat()/100))
                val bmi2digit = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2digit)
            }
        }
    }

    private fun validateInput(weight:String?, height:String?) : Boolean{
        return when{
            weight.isNullOrBlank() -> {
                Toast.makeText(this, "Don't be shy to enter your weight", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrBlank() -> {
                Toast.makeText(this, "Don't be shy to enter your height", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
    private fun displayResult(bmi: Float){
       tVIndex.text = bmi.toString()
        tVInfo.text = "(Normal range is 18.5-24.9)"

        var resultText =""
        var color = 0

        when{
            bmi<18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.Over_weight
            }
            bmi>30.00 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }
        tVResult.setTextColor(ContextCompat.getColor(this,color))
        tVResult.text = resultText
    }
}