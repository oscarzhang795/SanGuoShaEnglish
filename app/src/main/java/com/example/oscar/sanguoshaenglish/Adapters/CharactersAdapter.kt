package com.example.oscar.sanguoshaenglish.Adapters

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.oscar.sanguoshaenglish.R

class CharactersAdapter(private val cards: ArrayList<Bitmap>) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = LayoutInflater.from(parent.context).inflate(R.layout.imageview_cards, parent, false) as ImageView
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageBitmap(cards[position])
    }

    override fun getItemCount() = cards.size
}