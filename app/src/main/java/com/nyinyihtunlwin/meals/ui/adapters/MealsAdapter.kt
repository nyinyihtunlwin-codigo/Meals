package com.nyinyihtunlwin.meals.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nyinyihtunlwin.meals.R
import com.nyinyihtunlwin.meals.ui.viewholders.MealsViewHolder
import com.nyinyihtunlwin.meals.vos.MealVo

class MealsAdapter(val context: Context) : RecyclerView.Adapter<MealsViewHolder>() {

    private var mMeals: List<MealVo> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MealsViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.view_item_meal, parent, false)
        return MealsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mMeals.size
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.bindData(mMeals[position])
    }

    fun setNewData(meals: List<MealVo>) {
        mMeals = meals
        notifyDataSetChanged()
    }
}