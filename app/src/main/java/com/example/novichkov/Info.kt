package com.example.novichkov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.novichkov.databinding.ActivityFirstStepBinding
import com.example.novichkov.databinding.ActivityInfoBinding
import com.google.firebase.database.*

class Info : AppCompatActivity() {
    lateinit var binding: ActivityInfoBinding
    var ListAdapter:AdapterFilt?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recFind.layoutManager=  LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        ListAdapter= AdapterFilt()
        binding.recFind.adapter=ListAdapter

        binding.find.setOnClickListener {

            val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("User")

            database.addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list=ArrayList<User>()

                    for(i in snapshot.children){
                        val user = i.getValue(User::class.java)
                        if ( user?.citizenship==binding.citizenFind.text.toString()){
                            list.add(user)
                        }
                    }

                    ListAdapter?.loadListToAdapter(list)

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}