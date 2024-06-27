package com.example.firebase.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.R
import com.example.firebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val autenticar by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        varificarUsuarioLogado()
    }

    private fun varificarUsuarioLogado() {
        val usuario = autenticar.currentUser
        val id = usuario?.uid

        if (usuario != null) {
            Toast.makeText(this, "Usuario $id", Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(this, OpcoesActivity::class.java)
            )
        } else {
            Toast.makeText(this, "Sem Usuário Logado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnentrar.setOnClickListener {
            cadastrarUsuario()
            logarUsuario()
        }
    }

    fun logarUsuario() {
        //Dados digitados pelo usuario
        val email = "agathangrodriges@gmail.com"
        val senha = "Agatha100407"

        //autenticação
        autenticar.signInWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { authResult ->
            binding.txtResultado.text = "Sucesso ao logar"
            startActivity(
                Intent(this, OpcoesActivity::class.java)
            )
        }.addOnFailureListener { exception ->
            binding.txtResultado.text = "Falha ${exception.message}"
        }
    }

    private fun cadastrarUsuario() {
        val email = "agathangrodriges@gmail.com"
        val senha = "Agatha100407"

        autenticar.createUserWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { authResult ->
            val email = authResult.user?.email
            val id = authResult.user?.uid

            //EXIBIR MENSAGEM
            binding.txtResultado.text = "Sucesso a cadastrar: $id, $email"
        }.addOnFailureListener { exception ->
            val msgErro = exception.message
            binding.txtResultado.text = "Falha ao logar $msgErro"
        }
    }

}