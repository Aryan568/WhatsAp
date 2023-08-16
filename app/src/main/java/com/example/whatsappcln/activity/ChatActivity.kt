package com.example.whatsappcln.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import com.example.whatsappcln.R
import com.example.whatsappcln.databinding.ActivityChatBinding
import com.example.whatsappcln.model.MessageModel
import com.example.whatsappcln.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.Date

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var senderUid: String
    private lateinit var receiverUid: String
    private lateinit var senderRoom: String
    private lateinit var receiverRoom: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        senderUid= FirebaseAuth.getInstance().uid.toString()
        receiverUid= intent.getStringExtra("uid")!!

        senderRoom= senderUid+receiverUid
        receiverRoom= receiverUid+senderRoom

        database= FirebaseDatabase.getInstance()

        binding.sendImg.setOnClickListener {
            if (binding.messageBox.text.isEmpty()){
                Toast.makeText(this, "Please enter your message", Toast.LENGTH_SHORT).show()
            } else {
                val message= MessageModel(binding.messageBox.text.toString(), senderUid, Date().time)
                val randomKey= database.reference.push().key
                database.reference.child("chats")
                    .child(senderRoom).child("message").child(randomKey!!).setValue(message)
                    .addOnSuccessListener {
                        database.reference.child("chats")
                            .child(receiverRoom).child("message").child(randomKey).setValue(message)
                            .addOnSuccessListener {
                                binding.messageBox.text= null
                                Toast.makeText(this, "Message sent!!", Toast.LENGTH_SHORT).show()
                            }
                    }
            }

        }
    }
}