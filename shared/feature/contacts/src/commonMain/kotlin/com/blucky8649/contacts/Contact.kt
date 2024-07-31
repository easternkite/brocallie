package com.blucky8649.contacts

data class Contact(
    val id: Long,
    val name: String = "",
    val phoneNumber: String = "",
    val image: String = ""
)
