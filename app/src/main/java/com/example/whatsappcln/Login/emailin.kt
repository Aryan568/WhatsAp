package com.example.whatsappcln.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.whatsappcln.MainActivity
import com.example.whatsappcln.R
import com.example.whatsappcln.activity.ProfileActivity
import com.example.whatsappcln.databinding.ActivityEmailinBinding
import com.google.firebase.auth.FirebaseAuth

class emailin : AppCompatActivity() {
    private lateinit var binding: ActivityEmailinBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()

        binding.toSignup.setOnClickListener {
            val intent = Intent(this, email::class.java)
            startActivity(intent)
        }
//        if (firebaseAuth.currentUser!=null){
//            startActivity(Intent(this, ProfileActivity::class.java))
//            startActivity(intent)
//            finish()
//        }
        binding.button.setOnClickListener {
            val email= binding.Signin.text.toString()
            val pass= binding.Signinpass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}