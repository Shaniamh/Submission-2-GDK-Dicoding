package com.example.x441u.submission2.main

import com.example.x441u.submission2.api.TheSportDBApi
import com.example.x441u.submission2.api.ApiRepository
import com.example.x441u.submission2.model.MatchesResponses
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread



class MainPresenter (private val view: MainView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getEventList(matchschedule: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatches(matchschedule)),
                    MatchesResponses::class.java)
            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }
}