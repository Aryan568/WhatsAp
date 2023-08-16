package com.example.whatsappcln

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.whatsappcln.activity.LogActivity
import com.example.whatsappcln.activity.ProfileActivity
import com.example.whatsappcln.adapter.ViewPagerAdapter
import com.example.whatsappcln.databinding.ActivityMainBinding
import com.example.whatsappcln.ui.CallFragment
import com.example.whatsappcln.ui.ChatFragment
import com.example.whatsappcln.ui.StatusFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding?= null
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val fragmentArrayList= ArrayList<Fragment>()

        fragmentArrayList.add(ChatFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())

        auth= FirebaseAuth.getInstance()

//        if (auth.currentUser == null){
//            startActivity(Intent(this, ProfileActivity::class.java))
//            finish()
//        }

//        val sharedPref= this?.getPreferences(Context.MODE_PRIVATE)?:return
//        val isLogin= sharedPref.getString("Email", "1")
//        val email= intent.getStringExtra("email")
//        if (isLogin=="1"){
//            with(sharedPref.edit()){
//                putString("Email", email)
//                apply()
//            }
//        }else{
//            var intent= Intent(this, LogActivity::class.java)
//            startActivity(intent)
//            finish()
//        }


        val adapter= ViewPagerAdapter(this, supportFragmentManager, fragmentArrayList)
        binding!!.viewPager.adapter= adapter

        binding!!.tabs.setupWithViewPager(binding!!.viewPager)
    }
}