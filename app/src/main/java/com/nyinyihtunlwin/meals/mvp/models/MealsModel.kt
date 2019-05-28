package com.nyinyihtunlwin.meals.mvp.models

import com.nyinyihtunlwin.meals.network.MealsDataAgentImpl

class MealsModel {

    companion object {
        private var INSTANCE: MealsModel? = null
        fun getInstance(): MealsModel {
            if (INSTANCE == null) {
                INSTANCE = MealsModel()
            }
            val i = INSTANCE
            return i!!
        }
    }

    fun getLatestMeals() {
        MealsDataAgentImpl.getInstance().getLatestMeals()
    }
}