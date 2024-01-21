package com.example.task08_guiloginyregistro

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.task08_guiloginyregistro.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth



class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.btnSignIn.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            // Aquí puedes agregar más lógica para validar el resto de los campos

            if (email.isNotEmpty() && password.isNotEmpty()) {
                registerUser(email, password)
            } else {
                Toast.makeText(this, "Por favor ingrese los campos requeridos.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageBtnGoBack.setOnClickListener {
            // Handle back button action
            // finish()
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration success
                    Toast.makeText(this, "Registro exitoso.", Toast.LENGTH_SHORT).show()
                    // Navigate to the next screen or finish activity
                } else {
                    // If registration fails, display a message to the user.
                    Toast.makeText(this, "Registro fallido: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun setupSpinner() {
        // Crear un ArrayAdapter usando el array de strings y un spinner layout predeterminado
        ArrayAdapter.createFromResource(
            this,
            R.array.nationality_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Especificar el layout a usar cuando la lista de opciones aparece
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Aplicar el adapter al spinner
            binding.spinnerNationality.adapter = adapter
        }

        // Establecer un listener para el spinner
        binding.spinnerNationality.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Obtener el ítem seleccionado
                val selectedItem = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Acción a realizar cuando no se selecciona nada
            }
        }
    }
}
