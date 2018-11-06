package com.example.x441u.submission2.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.x441u.submission2.R
import com.example.x441u.submission2.model.Match
import org.jetbrains.anko.find


class MainAdapter (private val context: Context, private val events: List<Match>, val listener: (Match) -> Unit): RecyclerView.Adapter<EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            EventViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))


    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }
}

class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val homeTeam: TextView = view.find(R.id.homeNameTV)
    private val eventDate: TextView = view.find(R.id.dateEventTV)
    private val homeScore: TextView = view.find(R.id.homeScoreTV)
    private val awayScore: TextView = view.find(R.id.awayScoreTV)
    private val awayTeam: TextView = view.find(R.id.awaynameTV)
    fun bindItem(events: Match, listener: (Match) -> Unit) {
        homeTeam.text = events.homeTeam
        eventDate.text = events.eventDate
        homeScore.text = events.homeScore
        awayScore.text = events.awayScore
        awayTeam.text = events.awayTeam
        itemView.setOnClickListener {
            listener(events)
        }
    }
}