package com.example.x441u.submission2.main

import android.R
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.example.x441u.submission2.R.array.schedulesType
import com.example.x441u.submission2.api.ApiRepository
import com.example.x441u.submission2.detail.DetailMatchActivity
import com.example.x441u.submission2.model.Match
import com.example.x441u.submission2.util.invisible
import com.example.x441u.submission2.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class MainActivity : AppCompatActivity(), MainView {
    private var events: MutableList<Match> = mutableListOf()
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var eventType : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()
            swipeRefresh = swipeRefreshLayout{
                setColorSchemeResources(R.color.holo_blue_bright,
                        R.color.holo_green_light,
                        R.color.holo_orange_light,
                        R.color.holo_red_light)
                relativeLayout{
                    lparams(width= matchParent, height= wrapContent)

                    listEvent = recyclerView{
                        lparams(width= matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                    progressBar =  progressBar{}
                            .lparams{
                                centerHorizontally()
                            }
                }
            }
        }
        adapter = MainAdapter(ctx, events) {
            startActivity(intentFor<DetailMatchActivity>(
                    "eventDate" to it.eventDate,
                    "homeTeam" to it.homeTeam,
                    "awayTeam" to it.awayTeam,
                    "homeTeamId" to it.homeId,
                    "awayTeamId" to it.awayId,
                    "homeScore" to it.homeScore.toString(),
                    "awayScore" to it.awayScore.toString(),
                    "homeGoal" to it.homeGoalDetails,
                    "awayGoal" to it.awayGoalDetails,
                    "homeShots" to it.homeShots.toString(),
                    "awayShots" to it.awayShots.toString(),
                    "homeGoalKeeper" to it.homeGoalKeeper,
                    "awayGoalKeeper" to it.awayGoalKeeper,
                    "homeDefense" to it.homeDefense,
                    "awayDefense" to it.awayDefense,
                    "homeMidfield" to it.homeMidfield,
                    "awayMidfield" to it.awayMidfield,
                    "homeForward" to it.homeForward,
                    "awayForward" to it.awayForward,
                    "homeSubtitutes" to it.homeSubtitutes,
                    "awaySubtitutes" to it.awaySubtitutes))
        }
        listEvent.adapter = adapter
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(schedulesType)
        val spinnerAdapter = ArrayAdapter(ctx, R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                events.clear()
                eventType = spinner.selectedItem.toString()
                presenter.getEventList(eventType)
            }
        }
        swipeRefresh.onRefresh {
            presenter.getEventList(eventType)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}

