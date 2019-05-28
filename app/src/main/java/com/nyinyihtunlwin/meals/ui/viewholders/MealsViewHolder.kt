package com.nyinyihtunlwin.meals.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.nyinyihtunlwin.meals.vos.MealVo
import kotlinx.android.synthetic.main.view_item_meal.view.*

class MealsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindData(mealVo: MealVo) {
        itemView.tvName.text = mealVo.strMeal
    }

}