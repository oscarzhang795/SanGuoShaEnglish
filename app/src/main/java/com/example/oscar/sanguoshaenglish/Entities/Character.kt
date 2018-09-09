package com.example.oscar.sanguoshaenglish.Entities


data class Character(
    val name: String,
    val title: String,
    val alignment: String,
    val od: Boolean,
    val emperor: Boolean,
    val image_name: String,
    val abilities: List<Ability>
)