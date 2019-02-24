package com.example.oscar.sanguoshaenglish.Fragments

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import com.beust.klaxon.Klaxon
import com.example.oscar.sanguoshaenglish.Activities.MainMenuActivity
import com.example.oscar.sanguoshaenglish.Adapters.CountryAdapter
import com.example.oscar.sanguoshaenglish.R
import com.example.oscar.sanguoshaenglish.SanGuoShaApplication
import com.example.oscar.sanguoshaenglish.entities.CharacterData
import com.example.oscar.sanguoshaenglish.entities.CharacterData_
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import kotlinx.android.synthetic.main.card_country.*
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : Fragment() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var characterBox: Box<CharacterData>

    private lateinit var shuCharacters: List<CharacterData>
    private lateinit var wuCharacters: List<CharacterData>
    private lateinit var weiCharacters: List<CharacterData>
    private lateinit var kingdomlesssCharacters: List<CharacterData>

    private var viewState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainMenuActivity).supportActionBar?.title = "Characters"

        // Create character box instance for objectbox
        characterBox = ((activity as MainMenuActivity).application as SanGuoShaApplication).boxStore.boxFor()

        // Load database if not already setup
        if (characterBox.query().build().findFirst() == null) {
            loadJson()
        }
        sharedElementReturnTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.move)

        shuCharacters = characterBox.query().equal(CharacterData_.alignment, "SHU").build().find()
        wuCharacters = characterBox.query().equal(CharacterData_.alignment, "WU").build().find()
        weiCharacters = characterBox.query().equal(CharacterData_.alignment, "WEI").build().find()
        kingdomlesssCharacters = characterBox.query().equal(CharacterData_.alignment, "KINGDOMLESS").build().find()

        viewAdapter = CountryAdapter(shuCharacters, wuCharacters, weiCharacters, kingdomlesssCharacters, this.context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        postponeEnterTransition()
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(this.context)

        rv_char_list.apply {
            setHasFixedSize(true)
            adapter = viewAdapter
            layoutManager = viewManager
        }

        //Start transition after recycler view is loaded
        rv_char_list.viewTreeObserver
                .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        startPostponedEnterTransition()
                        rv_char_list.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
    }

    private fun loadJson() {
        val inputStream = this.resources.assets.open("characters.json")
        val klaxon = Klaxon()
        val parsed = klaxon.parseArray<CharacterData>(inputStream)
        parsed?.forEach { characterBox.put(it) }
    }
}