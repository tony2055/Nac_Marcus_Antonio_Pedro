package com.fiap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.password
import android.content.Intent


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        btEntrar.setOnClickListener {
            entrar()
        }

        btNovo.setOnClickListener {
            criarUsuario()
        }
    }

    private fun criarUsuario(){
        mAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etSenha.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    abrirTela()
                } else {
                    Toast.makeText(
                        this, task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun entrar(){
        mAuth.signInWithEmailAndPassword(etEmail.text.toString(), etSenha.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    abrirTela()
                } else {
                    Toast.makeText(
                        this, task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun abrirTela(){
        val intent = Intent(this, GrupoActivity::class.java)
        startActivity(intent)
        finish()
    }

}
