package com.example.contactapp

import android.net.Uri

data class Contact(
    val image: Uri,
    val name: String,
    val phoneNumber: String
)