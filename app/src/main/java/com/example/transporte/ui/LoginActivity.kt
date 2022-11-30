package com.example.transporte.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.transporte.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } // catch block to handle NullPointerException
        catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_login)

        val btnLogin: Button = findViewById(R.id.btn_login)

        val etEmail: EditText = findViewById(R.id.etUsuario)
        val etContrasenia: EditText = findViewById(R.id.etPassword)

        val tvbtnRegistro: TextView = findViewById(R.id.textView)
        val db = FirebaseFirestore.getInstance()

        val email: String = "abel.ss2407@gmail.com"

        tvbtnRegistro.setOnClickListener{
            this.goRegistro()
        }




        btnLogin.setOnClickListener{
            if(etEmail.text.isNotEmpty() && etContrasenia.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(etEmail.text.toString(), etContrasenia.text.toString()).addOnCompleteListener{
                        if (it.isSuccessful){
                            db.collection("users").document(etEmail.text.toString()).get().addOnSuccessListener{
                                val validacion = it.get("Tipo") as String?
                                if (validacion == "cliente"){
                                    goMain()
                                    etEmail.text.clear()
                                    etContrasenia.text.clear()

                                }
                                if (validacion == "admin"){
                                    goAdmin()
                                    etEmail.text.clear()
                                    etContrasenia.text.clear()
                                }
                            }
                        }else{
                            showAlert()
                        }
                    }
            }
        }

    }


    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun goMain(){
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    private fun goAdmin(){
        val intent: Intent = Intent(this, AdminActivity::class.java)
        startActivity(intent)

    }

    private fun goRegistro(){
        val intent: Intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)

    }
}