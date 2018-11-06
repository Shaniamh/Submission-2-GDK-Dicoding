package com.example.x441u.submission2.model

import com.google.gson.annotations.SerializedName


data class Team(
        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,

        @SerializedName("idTeam")
        var teamId: String? = null
)