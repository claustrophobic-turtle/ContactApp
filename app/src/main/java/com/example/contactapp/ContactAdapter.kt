package com.example.contactapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val listOfContacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.profile_image)
        val name: TextView = itemView.findViewById(R.id.text_name)
        val phoneNumber: TextView = itemView.findViewById(R.id.text_phone_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = listOfContacts[position]
        holder.name.text = currentContact.name
        holder.phoneNumber.text = currentContact.phoneNumber
        holder.image.setImageURI(currentContact.image)
    }

    override fun getItemCount(): Int {
        return listOfContacts.size
    }

}