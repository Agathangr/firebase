package com.example.firebase.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firebase.R
import com.example.firebase.databinding.ActivityMidiaBinding
import java.net.URI

class MidiaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMidiaBinding.inflate(layoutInflater)
    }

    private var permissaoCamera = false
    private var permissaoGaleria = false

    private val gestaoGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            binding.imgperfil.setImageURI(uri)
            Toast.makeText(this, "Imagem Selecionada", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Nenhuma imagem Selecionada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        acessarGaleria()
    }

    private fun acessarGaleria() {
        binding.btnGaleria.setOnClickListener {
            gestaoGaleria.launch("image/*")
        }
    }
}