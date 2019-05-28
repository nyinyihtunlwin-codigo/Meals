package com.nyinyihtunlwin.meals.mvp.views

import com.nyinyihtunlwin.meals.vos.MealVo

interface LatestMealsView {
    fun displayMeals(meals: List<MealVo>)
    fun showPrompt(message: String)
    fun showLoading()
    fun dismissLoading()
}