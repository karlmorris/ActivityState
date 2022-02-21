package edu.temple.activitystate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var person: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameEditText = findViewById<EditText>(R.id.editTextTextPersonName)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            person = Person(nameEditText.text.toString())
        }

        findViewById<Button>(R.id.showButton).setOnClickListener {
            person?.run {
                Toast.makeText(this@MainActivity, "Name: $name", Toast.LENGTH_SHORT).show()
            }

        }
    }
}