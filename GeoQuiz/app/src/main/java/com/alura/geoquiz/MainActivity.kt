package com.alura.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "GeoQuiz"

        var trueButton = findViewById<View>(R.id.bt_verdade)
        var falseButton = findViewById<View>(R.id.bt_falso)

        trueButton.setOnClickListener { view: View ->
            Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
                )
                .show()
        }

        falseButton.setOnClickListener{ view: View ->
            Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_LONG)
                .show()
        }
        }
}