package com.payroll.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SetupUrlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup_url)

        val urlInput = findViewById<EditText>(R.id.editServerUrl)
        val saveBtn = findViewById<Button>(R.id.btnSaveUrl)
        val helperText = findViewById<TextView>(R.id.textUrlHelper)

        helperText.text = getString(R.string.setup_url_helper)

        saveBtn.setOnClickListener {
            val enteredUrl = urlInput.text?.toString().orEmpty()
            if (!AppPrefs.isValidHttpsUrl(enteredUrl)) {
                urlInput.error = getString(R.string.error_invalid_url)
                urlInput.requestFocus()
                return@setOnClickListener
            }

            AppPrefs.setServerUrl(this, enteredUrl)
            Toast.makeText(this, R.string.url_saved, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
