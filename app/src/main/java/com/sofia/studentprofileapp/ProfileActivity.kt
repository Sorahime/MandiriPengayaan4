package com.sofia.studentprofileapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvNama: TextView

    private val editLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = Intent()
            data.putExtras(result.data!!)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvNama = findViewById(R.id.tvNama)
        val btnEdit = findViewById<Button>(R.id.btnEdit)
        val btnShare = findViewById<Button>(R.id.btnShare)

        val nama = intent.getStringExtra("nama")
        val nim = intent.getStringExtra("nim")
        val posisi = intent.getIntExtra("posisi", -1)

        tvNama.text = "$nama\n$nim"

        btnEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("nama", nama)
            intent.putExtra("nim", nim)
            intent.putExtra("posisi", posisi)
            editLauncher.launch(intent)
        }

        btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Nama: $nama\nNIM: $nim")
            }
            startActivity(Intent.createChooser(intent, "Share via"))
        }
    }
}