package com.example.oscar.sanguoshaenglish.Fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.oscar.sanguoshaenglish.Activities.MainMenuActivity
import com.example.oscar.sanguoshaenglish.Adapters.CharactersAdapter
import com.example.oscar.sanguoshaenglish.Entities.CharacterCard
import com.example.oscar.sanguoshaenglish.R
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : Fragment() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val charactersList = ArrayList<CharacterCard>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainMenuActivity).supportActionBar?.title = "Characters"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_characters, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun loadShuCharacters() {
        //Liu Bei
        val liubeiCharacter = CharacterCard()
        liubeiCharacter.characterName = "Liu Bei"
        liubeiCharacter.alignment = CharacterCard.Alignment.SHU
        liubeiCharacter.isEmperor = true
        liubeiCharacter.characterImage = convertToImage(R.drawable.image001)
        liubeiCharacter.abilities = "Kindness: During your main phase, You may give any number of cards in your hand to any number of heroes, the first time you give out 2 or more cards to a hero , you gain 1 Health\n" +
                "\n" +
                "Rouse: (Emperor Ability) Whenever you need to play or discard an ‘Attack”, any other Shu heroes can play or discard for you\n"
        charactersList.add(liubeiCharacter)
    }

    fun convertToImage(x: Int): ImageView {
        val imageView = ImageView(activity)
        imageView.setImageBitmap(BitmapFactory.decodeResource(resources, x))
        return imageView
    }

}