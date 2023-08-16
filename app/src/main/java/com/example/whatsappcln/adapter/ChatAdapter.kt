package com.example.whatsappcln.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatsappcln.R
import com.example.whatsappcln.activity.ChatActivity
import com.example.whatsappcln.databinding.ChatUserItemLayoutBinding
import com.example.whatsappcln.model.UserModel

class ChatAdapter(var context: Context, var list: ArrayList<UserModel>): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    inner class ChatViewHolder(view: View): RecyclerView.ViewHolder(view){
        var binding: ChatUserItemLayoutBinding= ChatUserItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_user_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        var user= list[position]
        Glide.with(context).load(user.imageUrl).into(holder.binding.userImage)
        holder.binding.userName.text= user.name

        holder.itemView.setOnClickListener {
            val intent= Intent(context, ChatActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}