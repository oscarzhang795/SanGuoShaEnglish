package com.example.oscar.sanguoshaenglish.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oscar.sanguoshaenglish.Activities.MainMenuActivity
import com.example.oscar.sanguoshaenglish.Adapters.CharactersAdapter
import com.example.oscar.sanguoshaenglish.Entities.Character
import com.example.oscar.sanguoshaenglish.R
import kotlinx.android.synthetic.main.fragment_characters.*

class CharactersFragment : Fragment() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val charactersList = ArrayList<Character>()

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
        viewManager = GridLayoutManager(this.context, 3)
        viewAdapter = CharactersAdapter(charactersList)

        rv_char_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }


}