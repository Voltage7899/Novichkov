package com.example.novichkov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.novichkov.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sign.setOnClickListener {

            val database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
            database.child("Admin").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.child(binding.phone.text.toString()).exists()){
                        val admin = snapshot.child(binding.phone.text.toString()).getValue(Admin::class.java)
                        if(admin?.pass.equals(binding.pass.text.toString())){
                            startActivity(Intent(this@MainActivity,Info::class.java))
                        }
                    }
                    else{
                        Toast.makeText(this@MainActivity,"Неверные данные", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity,"Проблемы", Toast.LENGTH_LONG).show()

                }

            })
            startActivity(Intent(this,Info::class.java))
        }

        binding.perepis.setOnClickListener {
            startActivity(Intent(this,FirstStep::class.java))
        }

    }
}