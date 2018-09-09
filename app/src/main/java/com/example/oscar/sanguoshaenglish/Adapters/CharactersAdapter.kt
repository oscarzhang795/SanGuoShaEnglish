package com.example.oscar.sanguoshaenglish.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.oscar.sanguoshaenglish.R

class CharactersAdapter(private val cards: ArrayList<ImageView>) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = LayoutInflater.from(parent.context).inflate(R.layout.imageview_cards, parent, false) as ImageView
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.imageView.setImageBitmap(cards[position].characterImage)
    }

    override fun getItemCount() = cards.size
}