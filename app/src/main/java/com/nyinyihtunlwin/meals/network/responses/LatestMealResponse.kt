package com.nyinyihtunlwin.meals.network.responses

import com.google.gson.annotations.SerializedName
import com.nyinyihtunlwin.meals.vos.MealVo

class LatestMealResponse (@SerializedName("meals") var meals: List<MealVo> = ArrayList())