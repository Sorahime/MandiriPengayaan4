package com.sofia.studentprofileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val listMahasiswa = mutableListOf(
        Mahasiswa("Sofia Melati Bareut Runa", "105222005"),
        Mahasiswa("Soranna Annelise von Eisengrimm", "104222005")
    )

    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {

            val nama = result.data?.getStringExtra("nama") ?: return@registerForActivityResult
            val nim = result.data?.getStringExtra("nim") ?: return@registerForActivityResult
            val posisi = result.data?.getIntExtra("posisi", -1) ?: -1

            if (posisi >= 0) {
                listMahasiswa[posisi] = Mahasiswa(nama, nim)
            } else {
                listMahasiswa.add(Mahasiswa(nama, nim))
            }

            updateUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btnMahasiswa1)
        val btn2 = findViewById<Button>(R.id.btnMahasiswa2)
        val btnTambah = findViewById<Button>(R.id.btnTambah)

        updateUI()

        btn1.setOnClickListener { bukaProfile(0) }
        btn2.setOnClickListener { bukaProfile(1) }

        btnTambah.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            launcher.launch(intent)
        }
    }

    private fun bukaProfile(index: Int) {
        val mhs = listMahasiswa[index]

        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("nama", mhs.nama)
        intent.putExtra("nim", mhs.nim)
        intent.putExtra("posisi", index)

        launcher.launch(intent)
    }

    private fun updateUI() {
        val btn1 = findViewById<Button>(R.id.btnMahasiswa1)
        val btn2 = findViewById<Button>(R.id.btnMahasiswa2)

        if (listMahasiswa.size > 0)
            btn1.text = "${listMahasiswa[0].nama}\n${listMahasiswa[0].nim}"

        if (listMahasiswa.size > 1)
            btn2.text = "${listMahasiswa[1].nama}\n${listMahasiswa[1].nim}"
    }
}