package com.sofia.studentprofileapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val etNama = findViewById<EditText>(R.id.etNama)
        val etNim = findViewById<EditText>(R.id.etNim)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnBatal = findViewById<Button>(R.id.btnBatal)

        val nama = intent.getStringExtra("nama")
        val nim = intent.getStringExtra("nim")

        etNama.setText(nama)
        etNim.setText(nim)

        btnSimpan.setOnClickListener {
            val result = Intent()
            result.putExtra("nama", etNama.text.toString())
            result.putExtra("nim", etNim.text.toString())
            result.putExtra("posisi", intent.getIntExtra("posisi", -1))

            setResult(Activity.RESULT_OK, result)
            finish()
        }

        btnBatal.setOnClickListener {
            finish()
        }
    }
}