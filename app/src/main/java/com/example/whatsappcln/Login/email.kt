package com.example.whatsappcln.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.whatsappcln.MainActivity
import com.example.whatsappcln.R
import com.example.whatsappcln.databinding.ActivityEmailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.nio.file.FileStore

class email : AppCompatActivity() {
    private lateinit var binding: ActivityEmailBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore
    private lateinit var db: DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        fStore= FirebaseFirestore.getInstance()

        binding.toSignin.setOnClickListener {
            val intent = Intent(this, emailin::class.java)
            startActivity(intent)
        }

//        if (firebaseAuth.currentUser!=null){
//            startActivity(Intent(this, MainActivity::class.java))
//            startActivity(intent)
//        }
        binding.button.setOnClickListener {
            val email = binding.Signup.text.toString()
            val pass = binding.Signuppass.text.toString()

            if (TextUtils.isEmpty(email)){
                binding.Signup.error= "Email required"
            }else if (TextUtils.isEmpty(pass)){
                binding.Signuppass.error= "Password required"
            }else{
                if (pass.length<6){
                    binding.Signuppass.error= "Password > 6"
                }else {
                    createAcc(email, pass)
                }
            }
        }

    }

    private fun createAcc(email: String, pass: String) {
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, emailin::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

//    private fun createAcc(em: String, pas : String) {
//        firebaseAuth.createUserWithEmailAndPassword(em, pas).addOnCompleteListener { task->
//            if (task.isSuccessful){
//                val userinfo= firebaseAuth.currentUser?.uid
//                db= fStore.collection("users").document(userinfo.toString())
//                val obj= mutableMapOf<String, String>()
//                obj["userEmail"]= em
//                obj["userPass"]= pas
//                db.set(obj).addOnSuccessListener {
//                    Log.d("onSuccess", "User created successfully")
//                }
//            }
//        }
//    }
}