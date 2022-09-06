package com.example.novichkov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.novichkov.databinding.ActivityFirstStepBinding
import com.example.novichkov.databinding.ActivityMainBinding
import com.google.firebase.database.*

class FirstStep : AppCompatActivity() {
    lateinit var binding: ActivityFirstStepBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstStepBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.nextStep.setOnClickListener {

            val userFirst = User(binding.fio.text.toString(),binding.phone.text.toString(),binding.age.text.toString(),binding.citizenship.text.toString(),binding.languages.text.toString(),binding.placeOfBorn.text.toString(),binding.education.text.toString())

            val database: DatabaseReference = FirebaseDatabase.getInstance().getReference()

            database.child("User").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.child(userFirst.phone.toString()).exists()){
                        Toast.makeText(this@FirstStep,"Переписчик уже есть",Toast.LENGTH_LONG).show()
                    }
                    else{
                        database.child("User").child(userFirst.phone.toString()).setValue(userFirst)
                        val intent=Intent(this@FirstStep,SecondStep::class.java)
                        intent.putExtra("phone",userFirst.phone.toString())
                        startActivity(intent)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@FirstStep,"Проблемы",Toast.LENGTH_LONG).show()

                }

            })
        }
    }
}