package com.example.novichkov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.novichkov.databinding.ActivityFirstStepBinding
import com.example.novichkov.databinding.ActivitySecondStepBinding
import com.google.firebase.database.*

class SecondStep : AppCompatActivity() {
    lateinit var binding: ActivitySecondStepBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondStepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phone=intent.getStringExtra("phone")

        binding.End.setOnClickListener {

            val extraInfo= ExtraInfo(phone,binding.typeOfHouse.text.toString(),binding.timeOfBuild.text.toString(),binding.totalAre.text.toString(),binding.numberOfRooms.text.toString())

            val database: DatabaseReference = FirebaseDatabase.getInstance().getReference()

            database.child("Extra").addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    database.child("Extra").child(phone.toString()).setValue(extraInfo)
                     startActivity(Intent(this@SecondStep,MainActivity::class.java))
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}