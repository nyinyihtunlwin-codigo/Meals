package com.nyinyihtunlwin.meals.mvp.presenters

import com.nyinyihtunlwin.meals.events.RestApiEvents
import com.nyinyihtunlwin.meals.mvp.models.MealsModel
import com.nyinyihtunlwin.meals.mvp.views.LatestMealsView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class LatestMealsPresenter constructor(val mView: LatestMealsView) : BasePresenter() {

    override fun onStart() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    fun startLoadingLatestMeals() {
        mView.showLoading()
        MealsModel.getInstance().getLatestMeals()
    }

    override fun onStop() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onLatestMealsLoaded(event: RestApiEvents.LatestMealsDataLoadedEvent) {
        mView.dismissLoading()
        mView.displayMeals(event.meals)
    }

    @Subscribe
    fun onError(event: RestApiEvents.ErrorInvokingAPIEvent) {
        mView.dismissLoading()
        mView.showPrompt(event.message)
    }
}