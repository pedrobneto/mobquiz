package com.example.mobquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
    }


    fun login(view: View){

        auth.signInWithEmailAndPassword(edt_email.text.toString(), edt_senha.text.toString())
            .addOnCompleteListener(this){task ->

                if (task.isSuccessful){
                    Toast.makeText(this@LoginActivity,
                        "Usuário logado com Sucesso", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@LoginActivity, JogoActivity::class.java))
                }else{
                    Toast.makeText(this@LoginActivity,
                        "Autenticação Falhou!!", Toast.LENGTH_LONG).show()
                }

            }

    }

    fun cadastro(view: View) {
        startActivity(Intent(this,CadastroActivity::class.java))
    }
}
