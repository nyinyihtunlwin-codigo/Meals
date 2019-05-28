package com.nyinyihtunlwin.meals.events

import com.nyinyihtunlwin.meals.vos.MealVo

object RestApiEvents {
    class ErrorInvokingAPIEvent(val message: String)
    class LatestMealsDataLoadedEvent(val meals: List<MealVo>)
}