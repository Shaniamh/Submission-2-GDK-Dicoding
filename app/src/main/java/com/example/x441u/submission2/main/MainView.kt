package com.example.x441u.submission2.main

import com.example.x441u.submission2.model.Match


interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Match>)
}