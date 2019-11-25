package com.example.mobquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        auth = FirebaseAuth.getInstance()


        edt_senha.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                edt_senha.error = if(s!!.length < 6) "Mínimo 6 caracteres" else null
            }
        })
    }


    fun cadastrar(view: View) {
        if(!Patterns.EMAIL_ADDRESS.matcher(edt_email.text.toString()).matches()) {
            edt_email.error = "E-mail inválido"
            return
        }



        if(edt_email.text.isEmpty()){
            edt_email.error = "Campo E-mail Obrigatório"
        }else if (edt_senha.text.isEmpty()){
            edt_senha.error = "Campo Senha Obrigatório"
        }else if (edt_senha.text.toString() != edt_conf_senha.text.toString()){
            edt_senha.error = "Os campos de senha devem ser iguais"
        } else {11
            auth.createUserWithEmailAndPassword(edt_email.text.toString(), edt_senha.text.toString())
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){
                        Toast.makeText(this@CadastroActivity,
                            "Usuário Cadastrado com Sucesso", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        val resposta = task.exception!!.toString()
                        Toast.makeText(this@CadastroActivity,
                            resposta, Toast.LENGTH_LONG).show()

                    }

                }
        }
    }
}
