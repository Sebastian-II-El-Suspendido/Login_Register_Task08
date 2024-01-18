package com.example.task08_guiloginyregistro

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    //VARIABLES
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var textViewResult : TextView
    private lateinit var btnLogin : Button
    private lateinit var btnSingIn : Button
    private lateinit var auth: FirebaseAuth
    //VARIABLE SISTEMA LOG
    private val TAG = "LoginActivity"


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        //CREACION DE LA VISTA
        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            //ASIGNACION DE VARIABLES CON ELEMENTOS LAYOUT
            Log.d(TAG, "ASIGNACION DE VARIABLES CON ELEMENTOS LAYOUT")

            email = findViewById(R.id.editTextEmail)
            password = findViewById(R.id.editTextPassword)
            textViewResult = findViewById(R.id.textViewResult)
            btnLogin = findViewById(R.id.btnLogin)
            btnSingIn = findViewById(R.id.btnSignIn)


            //BOTON LOGIN PARA ACCEDER

            try {
                btnLogin.setOnClickListener{
                    if (email.text.isNotEmpty() && password.text.isNotEmpty()){

                        loginUser(email.text.toString(),password.text.toString())

                    } else {
                        textViewResult.visibility = View.VISIBLE
                        textViewResult.text = "There cannot be empty fields."
                    }
                }
            }
            //Control de errores
            catch (e: Exception) {
                Log.d(TAG, "Error en la autentificacion del usuario")

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("There is a mistake on the athentication")
                builder.setPositiveButton("OK",null)
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }


            //BOTON SIGNIN

            try {
                btnSingIn.setOnClickListener{
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }
            }
            //Control de errores
            catch (e: Exception) {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("There is a mistake for Sign In.")
                builder.setPositiveButton("OK",null)
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }

    //Funcion para acceder a la cuenta
    fun loginUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                Log.d(TAG, "Autentificacion del ususario Correcta")

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("There is a mistake on the athentication")
                builder.setPositiveButton("OK",null)
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
    }
    }
