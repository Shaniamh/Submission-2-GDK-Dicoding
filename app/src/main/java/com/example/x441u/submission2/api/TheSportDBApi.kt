package com.example.x441u.submission2.api

import android.net.Uri
import com.example.x441u.submission2.BuildConfig


object TheSportDBApi {

    fun getMatches(schedule: String?): String {
        var matchType: String? = null

        if (schedule == "Last Match") {
            matchType = "eventspastleague.php"
        } else if (schedule == "Next Match")  {
            matchType = "eventsnextleague.php"
        }

        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath(matchType)
                .appendQueryParameter("id", "4334")
                .build()
                .toString()
    }

    fun getTeamsById(teamId: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", teamId)
                .build()
                .toString()
    }
}