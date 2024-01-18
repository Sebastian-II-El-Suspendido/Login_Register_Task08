package com.example.task08_guiloginyregistro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView

class SignInActivity : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var sex : EditText
    private lateinit var natinality : EditText
    private lateinit var hobbies : EditText
    private lateinit var cv : EditText

    private lateinit var radioButtonH : RadioButton
    private lateinit var radioButtonM : RadioButton
    private lateinit var spinnerNationality : Spinner
    private lateinit var checkBoxTennis : CheckBox
    private lateinit var checkBoxFootball : CheckBox
    private lateinit var editTextCV : EditText
    private lateinit var btnSignIn: Button
    private lateinit var btnGoBack: ImageButton
    private lateinit var textViewResult: TextView

    private lateinit var auth: FirebaseAuth
    //VARIABLE SISTEMA LOG
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        radioButtonH = findViewById(R.id.radioButtonH)
        radioButtonM = findViewById(R.id.radioButtonM)
        spinnerNationality = findViewById(R.id.spinnerNationality)
        checkBoxTennis = findViewById(R.id.checkBoxTennis)
        checkBoxFootball = findViewById(R.id.checkBoxFootball)
        editTextCV = findViewById(R.id.editTextCV)
        btnSignIn = findViewById(R.id.btnSignIn)
        btnGoBack = findViewById(R.id.imageBtnGoBack)


        auth= Firebase.auth

            try {
                btnSignIn.setOnClickListener{

                    if (email.text.isNotEmpty() && password.text.isNotEmpty()){

                        signInUser(email.text.toString(),password.text.toString(),)

                    } else{
                        textViewResult.visibility = View.VISIBLE
                        textViewResult.text = "There cannot be empty fields."
                    }
                }

            } catch (e: Exception) {
                Log.d(TAG, "Error no esperado")
            }
        }


    //Funcion SignIn para crear cuenta
    fun signInUser(email: String, password: String, sex: Character, nationality: String, hobbies: ArrayList<String>, cv: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                Log.d(TAG, "Usuario Creado Correctamente")

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

            } else {
                Log.d(TAG, "Usuario No Creado.")
            }
        }
    }
    }
