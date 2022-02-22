package edu.temple.activitystate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    // A companion object is used here to store references
    // Think of this as something similar to a static reference
    // You can use 'const val', but constants have global namespace
    companion object {
        val KEY_PERSON = "personKey"
    }

    var person: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Fetch info from saveInstanceState, but only if it's not-null
        // argument will be null first time activity loads, and will continue
        // to be null if we do not store items in bundle argument of
        // onSaveInstanceState()
        savedInstanceState?.getString(KEY_PERSON)?.run {
            person = Person(this)
        }

        val nameEditText = findViewById<EditText>(R.id.editTextTextPersonName)
        val imageView = findViewById<ImageView>(R.id.imageView)

        findViewById<ToggleButton>(R.id.toggleButton).setOnCheckedChangeListener { _, p1 ->
            mainViewModel.setImageId(
                if (p1) R.drawable.cat2 else R.drawable.cat1
            )
        }


        // Observe changes to LiveData object stored in ViewModel
        // and update UI when changes occur
        mainViewModel.getImageId().observe(this) {
            imageView.setImageResource(it)
        }

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            person = Person(nameEditText.text.toString())
        }

        findViewById<Button>(R.id.showButton).setOnClickListener {
            person?.run {
                Toast.makeText(this@MainActivity, "Name: $name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Alternative to onCreate()
    // You can use one, but probably not both - Nothing will break, it's just inefficient
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString(KEY_PERSON)?.run {
            person = Person(this)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // If person is not-null, then execute the scope function
        person?.run{
            outState.putString(KEY_PERSON, name)
        }
    }
}