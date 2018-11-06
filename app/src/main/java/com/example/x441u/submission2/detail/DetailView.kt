package com.example.x441u.submission2.detail


interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showBadge (homeBadge: String, awayBadge: String)
}