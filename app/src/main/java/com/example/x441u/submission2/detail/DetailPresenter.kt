package com.example.x441u.submission2.detail

import com.example.x441u.submission2.api.ApiRepository
import com.example.x441u.submission2.api.TheSportDBApi
import com.example.x441u.submission2.model.TeamResponses
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson) {
    fun getTeamDetail(homeTeamId: String, awayTeamId: String) {

        view.showLoading()
        doAsync {
            val data1 = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsById(homeTeamId)),
                    TeamResponses::class.java)
            val data2 = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsById(awayTeamId)),
                    TeamResponses::class.java)
            uiThread {
                view.hideLoading()
                view.showBadge(data1.teams[0].teamBadge!!, data2.teams[0].teamBadge!!)
            }
        }
    }

}