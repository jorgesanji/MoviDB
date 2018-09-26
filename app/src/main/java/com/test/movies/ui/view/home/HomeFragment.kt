package com.test.movies.ui.view.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import com.test.movies.R
import com.test.movies.di.InjectorHelper
import com.test.movies.ui.presenter.home.HomePresenter
import com.test.movies.ui.view.base.MVPFragment
import com.test.movies.ui.view.home.adapter.MovieListAdapter
import java.util.*

class HomeFragment : MVPFragment<HomePresenter, HomePresenter.View>(), HomePresenter.View, HomeScreen.Listener, DatePickerDialog.OnDateSetListener {

    private var homeScreen: HomeScreen? = null

    override val rootView: View
        get() {
            homeScreen = HomeScreen(activity!!)
            homeScreen!!.setListener(this)
            return homeScreen as HomeScreen
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_sort_lastest -> presenter!!.sortByLatestMovies()
            R.id.action_sort_oldest -> presenter!!.sortByOldestMovies()
            R.id.action_sort_from -> showDateDialog()
            R.id.action_sort_clear -> presenter.clearFilters()
            android.R.id.home -> {}
        }
        return true
    }

    override fun injectDependencies() {
        InjectorHelper.getPresenterComponent(activity!!).inject(this)
    }

    override fun onDidAppear() {
        presenter.getMovies()
        homeScreen!!.setAdapter(MovieListAdapter(presenter))
    }

    private fun showDateDialog(){
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(activity,this,   calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                Calendar.DAY_OF_MONTH)
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        presenter.filterFromDate(calendar.time)
    }

    /*
    HomePresenter View
    */

    override fun reloadData() {
        homeScreen!!.reloadData()
    }

    /*
     HomeScreen Listener
     */

    override fun onItemClick(view: View, position: Int) {
        presenter.launchDetail(position)
    }

    override fun onLongItemClick(view: View?, position: Int) {}
}