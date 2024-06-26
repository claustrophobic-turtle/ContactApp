package com.example.contactapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var fab: FloatingActionButton

    private lateinit var nameEdt: EditText
    private lateinit var phoneEdt: EditText
    private lateinit var previewImage: ImageView
    private lateinit var btnChooseImage: Button
    private lateinit var btnAddContact: Button
    private lateinit var dialog: Dialog

    private lateinit var contactAdapter: ContactAdapter
    val listOfContacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rv = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        contactAdapter = ContactAdapter(listOfContacts)
        rv.adapter = contactAdapter
        fab = findViewById(R.id.fab)

        fab.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog() {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_dialogue)

        nameEdt = dialog.findViewById(R.id.edt_name)
        phoneEdt = dialog.findViewById(R.id.edt_phone_number)
        previewImage = dialog.findViewById(R.id.image_preview)
        btnChooseImage = dialog.findViewById(R.id.btn_choose_image)
        btnAddContact = dialog.findViewById(R.id.btn_add_contact)

        btnChooseImage.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 101)
        }

        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {
            previewImage.visibility = View.VISIBLE
            previewImage.setImageURI(data?.data)

            btnAddContact.setOnClickListener {
                val nameForEdt = nameEdt.text.toString()
                val phoneNumberForEdt = phoneEdt.text.toString()
                val previewImage = data?.data

                val contact = Contact(
                    name = nameForEdt,
                    phoneNumber = phoneNumberForEdt,
                    image = previewImage!!
                )

                listOfContacts.add(contact)
                contactAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

        }
    }

}