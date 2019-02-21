package com.example.oscar.sanguoshaenglish.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.example.oscar.sanguoshaenglish.R
import kotlinx.android.synthetic.main.fragment_character_card.*

class CharacterCardFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_character_card, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        center_character_image.setImageResource(arguments!!.getInt("CHARACTER_IMAGE"))
        center_character_image.transitionName = (arguments!!.getString("TRANSITION_NAME"))
        tv_abilities.text = arguments!!.getString("CHARACTER_ABILITY")
        tv_abilities.movementMethod = ScrollingMovementMethod()
    }
}