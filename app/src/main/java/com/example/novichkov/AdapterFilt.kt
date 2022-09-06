package com.example.novichkov

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novichkov.databinding.ElementBinding

class AdapterFilt: RecyclerView.Adapter<AdapterFilt.ViewHolder>() {

    private var ListInAdapter = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFilt.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterFilt.ViewHolder, position: Int) {
        holder.bind(ListInAdapter[position])
    }

    override fun getItemCount(): Int {
        return ListInAdapter.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ElementBinding.bind(itemView)
        fun bind(user: User) {
            binding.elFio.text=user.fio
            binding.elPhone.text=user.phone




        }
    }

    fun loadListToAdapter(productList: ArrayList<User>) {
        ListInAdapter = productList
        notifyDataSetChanged()
    }




}