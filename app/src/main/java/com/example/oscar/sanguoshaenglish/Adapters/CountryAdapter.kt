package com.example.oscar.sanguoshaenglish.Adapters

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.example.oscar.sanguoshaenglish.entities.Character
import com.example.oscar.sanguoshaenglish.R
import kotlinx.android.synthetic.main.card_country.view.*

class CountryAdapter(private val shuCountry: ArrayList<Character>,
                     private val wuCountry: ArrayList<Character>,
                     private val weiCountry: ArrayList<Character>,
                     private val kingdomlessCountry: ArrayList<Character>,
                     private val mContext: Context) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    private val headerSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70.toFloat(), mContext.resources.displayMetrics).toInt()
    private val shuRvSize =
            if (shuCountry.size % 3 == 0) {
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (shuCountry.size/3) * 205.toFloat(), mContext.resources.displayMetrics).toInt()
            } else {
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (shuCountry.size/3 + 1) * 205.toFloat(), mContext.resources.displayMetrics).toInt()
            }
    private val wuRvSize =
            if (wuCountry.size % 3 == 0) {
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (wuCountry.size/3) * 205.toFloat(), mContext.resources.displayMetrics).toInt()
            } else {
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (wuCountry.size/3 + 1) * 205.toFloat(), mContext.resources.displayMetrics).toInt()
            }
    private val weiRvSize =
            if (weiCountry.size % 3 == 0) {
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (weiCountry.size/3) * 205.toFloat(), mContext.resources.displayMetrics).toInt()
            } else {
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (weiCountry.size/3 + 1) * 205.toFloat(), mContext.resources.displayMetrics).toInt()
            }
    private val kingdomlessRvSize =
            if (kingdomlessCountry.size % 3 == 0) {
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (kingdomlessCountry.size/3) * 205.toFloat(), mContext.resources.displayMetrics).toInt()
            } else {
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (kingdomlessCountry.size/3 + 1) * 205.toFloat(), mContext.resources.displayMetrics).toInt()
            }


    class ViewHolder(var cardView: CardView) : RecyclerView.ViewHolder(cardView) {
        var isCollapsed = true
        var size = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_country, parent, false) as CardView
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when(position) {
            0 -> {
                holder.itemView.tv_country_name.text = "Shu"
                holder.cardView.rv_characters_list.adapter = CharactersAdapter(shuCountry, mContext)
                holder.size = shuRvSize
            }
            1 -> {
                holder.itemView.tv_country_name.text = "Wu"
                holder.cardView.rv_characters_list.adapter = CharactersAdapter(wuCountry, mContext)
                holder.size = wuRvSize
            }
            2 -> {
                holder.itemView.tv_country_name.text = "Wei"
                holder.cardView.rv_characters_list.adapter = CharactersAdapter(weiCountry, mContext)
                holder.size = weiRvSize
            }
            3 -> {
                holder.itemView.tv_country_name.text = "Heroes"
                holder.cardView.rv_characters_list.adapter = CharactersAdapter(kingdomlessCountry, mContext)
                holder.size = kingdomlessRvSize
            }
        }

        holder.cardView.rv_characters_list.layoutManager = GridLayoutManager(mContext, 3)
        holder.cardView.rv_characters_list.recycledViewPool = viewPool

        holder.cardView.cv_country_banner.setOnClickListener {
            val valueAnimator: ValueAnimator = if (!holder.isCollapsed) {
                ValueAnimator.ofInt(holder.itemView.height, headerSize).apply {
                    addListener(object: Animator.AnimatorListener {
                        override fun onAnimationRepeat(p0: Animator?) {}

                        override fun onAnimationEnd(p0: Animator?) {
                            holder.isCollapsed = true
                        }

                        override fun onAnimationCancel(p0: Animator?) {}

                        override fun onAnimationStart(p0: Animator?) {}
                    })
                }
            } else {
                holder.isCollapsed = false
                ValueAnimator.ofInt(holder.itemView.height, headerSize + holder.size)
            }
            valueAnimator.duration = 300
            valueAnimator.interpolator = AccelerateDecelerateInterpolator()
            valueAnimator.addUpdateListener { animation ->
                holder.itemView.layoutParams.height = animation.animatedValue as Int
                holder.itemView.requestLayout()
            }
            valueAnimator.start()
        }

    }

}