package com.example.wikitest.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wikitest.R
import com.example.wikitest.view.fragment.MainCaseFragement
import com.example.wikitest.view.service.response.Entry

class EntryAdapter(private val mList: List<Entry>
                   , var listner: EntryClickLister
                   , var context: Context
) : RecyclerView.Adapter<EntryAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.cell)
        val button : Button = itemView.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entry_cell, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]


        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text
        holder.button.setOnClickListener {
            listner.onClicked(ItemsViewModel)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}


interface EntryClickLister{
    fun onClicked(entry : Entry)
}