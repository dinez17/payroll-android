package com.payroll.mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UrlSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url_settings)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val urlInput = findViewById<EditText>(R.id.editServerUrlSettings)
        val saveBtn = findViewById<Button>(R.id.btnSaveUrlSettings)

        urlInput.setText(AppPrefs.getServerUrl(this))

        saveBtn.setOnClickListener {
            val enteredUrl = urlInput.text?.toString().orEmpty()
            if (!AppPrefs.isValidHttpsUrl(enteredUrl)) {
                urlInput.error = getString(R.string.error_invalid_url)
                urlInput.requestFocus()
                return@setOnClickListener
            }

            AppPrefs.setServerUrl(this, enteredUrl)
            Toast.makeText(this, R.string.url_updated, Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
