package com.nyinyihtunlwin.meals.ui.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.nyinyihtunlwin.meals.R
import com.nyinyihtunlwin.meals.mvp.presenters.LatestMealsPresenter
import com.nyinyihtunlwin.meals.mvp.views.LatestMealsView
import com.nyinyihtunlwin.meals.ui.adapters.MealsAdapter
import com.nyinyihtunlwin.meals.vos.MealVo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), LatestMealsView {

    private lateinit var mPresenter: LatestMealsPresenter

    private lateinit var mAdapter: MealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mPresenter = LatestMealsPresenter(this)
        mPresenter.startLoadingLatestMeals()

        mAdapter = MealsAdapter(this)
        rvMeals.adapter = mAdapter
        rvMeals.setHasFixedSize(true)
        rvMeals.layoutManager = GridLayoutManager(this,2)

        swipeRefresh.setOnRefreshListener {
            mPresenter.startLoadingLatestMeals()
        }

    }

    override fun displayMeals(meals: List<MealVo>) {
        mAdapter.setNewData(meals)
    }

    override fun showPrompt(message: String) {
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        if (!swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = true
        }
    }

    override fun dismissLoading() {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }
}
