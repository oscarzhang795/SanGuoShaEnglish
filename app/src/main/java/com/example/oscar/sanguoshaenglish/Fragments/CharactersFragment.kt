package com.example.oscar.sanguoshaenglish.Fragments

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
import com.example.oscar.sanguoshaenglish.Entities.Character
import com.example.oscar.sanguoshaenglish.Entities.CharacterData
import com.example.oscar.sanguoshaenglish.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_characters.*


class CharactersFragment : Fragment() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val characterDataList = ArrayList<CharacterData>()
    private val characterList = ArrayList<Character>()
    private val dbReference = FirebaseFirestore.getInstance()

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
        loadShuCharacters()

        viewManager = GridLayoutManager(this.context, 3)
        viewAdapter = CharactersAdapter(characterList, this.context!!)
        rv_char_list.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }
    }

    fun loadShuCharacters() {
        dbReference.collection("shucharacters").get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        it.result.forEach { document -> characterDataList.add(document.toObject(CharacterData::class.java)) }
                        characterDataList.forEach { characterData ->
                            characterData.image_name
                            val charImage = ImageView(context)
                            charImage.setImageBitmap(BitmapFactory.decodeResource(resources, resources.getIdentifier(characterData.image_name, "drawable", context!!.packageName)))
                            characterList.add(Character( characterData, resources.getIdentifier(characterData.image_name, "drawable", context!!.packageName)))
                        }
                        rv_char_list.apply {
                            setHasFixedSize(true)
                            adapter = viewAdapter
                            layoutManager = viewManager
                        }
                    }
                }
    }


}