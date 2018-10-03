package com.example.oscar.sanguoshaenglish.Adapters

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.oscar.sanguoshaenglish.Activities.MainMenuActivity
import com.example.oscar.sanguoshaenglish.Entities.Character
import com.example.oscar.sanguoshaenglish.R

class CharactersAdapter(private val cards: ArrayList<Character>, private val mContext: Context) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = LayoutInflater.from(parent.context).inflate(R.layout.imageview_cards, parent, false) as ImageView
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(cards[position].charImage)
        holder.imageView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("CHARACTER_IMAGE", cards[position].charImage)
            bundle.putString("CHARACTER_ABILITY", cards[position].characterData.toString())
            (mContext as MainMenuActivity).showCharacterCardFragment(bundle)
        }
    }

    override fun getItemCount() = cards.size
}