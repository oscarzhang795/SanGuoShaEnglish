package com.example.oscar.sanguoshaenglish.Entities

import android.graphics.Bitmap
import android.provider.ContactsContract
import android.widget.ImageView

class CharacterCard {

    lateinit var alignment: Alignment
    lateinit var characterName: String
    lateinit var characterImage: ImageView
    var isEmperor = false
    lateinit var abilities: String

    enum class Alignment(val string: String) {
        SHU("Shu"),
        WEI("Wei"),
        WU("Wu"),
        HERO("Hero")
    }
}