package com.example.x441u.submission2.detail

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.x441u.submission2.R
import com.example.x441u.submission2.api.ApiRepository
import com.example.x441u.submission2.util.invisible
import com.example.x441u.submission2.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*


class DetailMatchActivity: AppCompatActivity(), DetailView {

    private lateinit var presenter: DetailPresenter
    private lateinit var progressBar: ProgressBar

    private var homeTeam: String = ""
    private var awayTeam : String = ""
    private var homeId: String = ""
    private var awayId : String = ""
    private var homeScore: String = ""
    private var awayScore : String = ""
    private var homeGoals : String = ""
    private var awayGoals : String = ""
    private var homeShots: String = ""
    private var awayShots : String = ""
    private var homeGoalKeeper : String = ""
    private var awayGoalKeeper : String = ""
    private var homeDefense : String = ""
    private var awayDefense : String = ""
    private var homeMidfield : String = ""
    private var awayMidfield : String = ""
    private var homeForward : String = ""
    private var awayForward : String = ""
    private var homeSubtitutes : String = ""
    private var awaySubtitutes: String = ""
    private var homeBadge: String = ""
    private var awayBadge: String = ""
    private var eventDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MatchDetailUI().setContentView(this)

        progressBar = find (MatchDetailUI.progressBar)
        showActionBar()
        SetMatchHeaders()

        val request = ApiRepository()
        val gson = Gson()

        presenter = DetailPresenter(this, request, gson)
        presenter.getTeamDetail(homeId, awayId)

        if(intent.getStringExtra("homeGoal")!=null)
        {
            SetMatchData()
        }
    }

    private fun showActionBar(){
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }


    fun SetMatchHeaders() {
        val textMatchDate = find<TextView>(MatchDetailUI.matchDate)

        val textHomeTeam = find<TextView>(MatchDetailUI.teamHome)
        val textAwayTeam = find<TextView>(MatchDetailUI.teamAway)
        val textHomeScore = find<TextView>(MatchDetailUI.teamHomeScore)
        val textAwayScore = find<TextView>(MatchDetailUI.teamAwayScore)

        homeScore = intent.getStringExtra("homeScore")
        awayScore = intent.getStringExtra("awayScore")
        homeTeam = intent.getStringExtra("homeTeam")
        awayTeam = intent.getStringExtra("awayTeam")
        homeId = intent.getStringExtra("homeTeamId")
        awayId = intent.getStringExtra("awayTeamId")
        eventDate = intent.getStringExtra("eventDate")

        textMatchDate.text = eventDate
        textHomeTeam.text = homeTeam
        textAwayTeam.text = awayTeam
        textHomeScore.text = if (homeScore == "null") null else homeScore
        textAwayScore.text = if (awayScore == "null") null else awayScore
    }


    fun SetMatchData(){

        homeGoals = intent.getStringExtra("homeGoal")
        awayGoals = intent.getStringExtra("awayGoal")
        homeShots = intent.getStringExtra("homeShots")
        awayShots = intent.getStringExtra("awayShots")
        homeGoalKeeper = intent.getStringExtra("homeGoalKeeper")
        awayGoalKeeper = intent.getStringExtra("awayGoalKeeper")
        homeDefense = intent.getStringExtra("homeDefense")
        awayDefense = intent.getStringExtra("awayDefense")
        homeMidfield = intent.getStringExtra("homeMidfield")
        awayMidfield = intent.getStringExtra("awayMidfield")
        homeForward = intent.getStringExtra("homeForward")
        awayForward = intent.getStringExtra("awayForward")
        homeSubtitutes = intent.getStringExtra("homeSubtitutes")
        awaySubtitutes = intent.getStringExtra("awaySubtitutes")

        val textHomeGoals = find<TextView>(MatchDetailUI.teamHomeGoals)
        val textAwayGoals = find<TextView>(MatchDetailUI.teamAwayGoals)

        val textHomeGK = find<TextView>(MatchDetailUI.teamHomeGK)
        val textAwayGK = find<TextView>(MatchDetailUI.teamAwayGK)

        val textHomeDF = find<TextView>(MatchDetailUI.teamHomeDF)
        val textAwayDF = find<TextView>(MatchDetailUI.teamAwayDF)

        val textHomeMF = find<TextView>(MatchDetailUI.teamHomeMF)
        val textAwayMF = find<TextView>(MatchDetailUI.teamAwayMF)

        val textHomeFW = find<TextView>(MatchDetailUI.teamHomeFW)
        val textAwayFW = find<TextView>(MatchDetailUI.teamAwayFW)

        val textHomeSubs = find<TextView>(MatchDetailUI.teamHomeSub)
        val textAwaySubs = find<TextView>(MatchDetailUI.teamAwaySub)
        textHomeGoals.text = homeGoals
        textAwayGoals.text = awayGoals
        textHomeGK.text = homeGoalKeeper
        textAwayGK.text = awayGoalKeeper
        textHomeDF.text = homeDefense
        textAwayDF.text = awayDefense
        textHomeMF.text = homeMidfield
        textAwayMF.text = awayMidfield
        textHomeFW.text = homeForward
        textAwayFW.text = awayForward
        textHomeSubs.text = homeSubtitutes
        textAwaySubs.text = awaySubtitutes

    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showBadge(homeBadge: String, awayBadge: String) {
        val homeImage= find<ImageView>(MatchDetailUI.teamHomeLogo)
        val awayImage = find<ImageView>(MatchDetailUI.teamAwayLogo)
        Glide.with(this).load(homeBadge).into(homeImage)
        Glide.with(this).load(awayBadge).into(awayImage)
    }

    override fun onSupportNavigateUp() : Boolean{
        onBackPressed()
        return true
    }

    class MatchDetailUI : AnkoComponent<DetailMatchActivity> {
        companion object {
            val progressBar = 2
            val matchDate = 3
            val teamHomeLogo = 4
            val teamAwayLogo = 5
            val teamHome = 6
            val teamAway = 7
            val teamHomeScore = 8
            val teamAwayScore = 9
            val teamHomeGoals = 10
            val teamAwayGoals = 11
            val teamHomeShots = 12
            val teamAwayShots = 13
            val teamHomeGK = 14
            val teamAwayGK = 15
            val teamHomeDF = 16
            val teamAwayDF = 17
            val teamHomeMF = 18
            val teamAwayMF = 19
            val teamHomeFW = 20
            val teamAwayFW = 21
            val teamHomeSub = 22
            val teamAwaySub = 23
        }

        override fun createView(ui: AnkoContext<DetailMatchActivity>) = with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(22)
                orientation = LinearLayout.VERTICAL
                backgroundColor = Color.WHITE

                scrollView {
                    isVerticalScrollBarEnabled = false
                    verticalLayout {
                        lparams(width = matchParent, height = wrapContent)

                        verticalLayout {
                            lparams(width = matchParent, height = matchParent)
                            gravity = Gravity.CENTER_HORIZONTAL
                            padding = dip(12)

                            textView {
                                id = matchDate
                                typeface = Typeface.DEFAULT_BOLD
                                verticalPadding = dip(5)
                                textSize = 17f
                            }.lparams(width = wrapContent, height = wrapContent) {
                                gravity = Gravity.CENTER
                            }

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER
                                lparams(width = matchParent, height = wrapContent) {
                                    topMargin = dip(5)
                                    bottomMargin = dip(13)
                                }

                                verticalLayout {
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                    imageView {
                                        id = teamHomeLogo
                                        verticalPadding = dip(12)

                                    }.lparams(width = dip(128), height = dip(128))
                                    textView {
                                        id = teamHome
                                        textSize = 20f
                                        gravity = Gravity.CENTER
                                        textColor = R.color.colorPrimaryDark
                                        typeface = Typeface.DEFAULT_BOLD
                                        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE

                                    }.lparams(width = dip(128), height = wrapContent)
                                }.lparams(width = matchParent, height = wrapContent) {
                                    weight = 1F;
                                }

                                linearLayout {
                                    orientation = LinearLayout.HORIZONTAL
                                    gravity = Gravity.CENTER
                                    horizontalPadding = dip(16)
                                    textView {
                                        id = teamHomeScore
                                        gravity = Gravity.CENTER
                                        textSize = 40f
                                        typeface = Typeface.DEFAULT_BOLD

                                    }.lparams(width = wrapContent, height = wrapContent)
                                    textView {
                                        text = " - "
                                        horizontalPadding = dip(4)
                                        textSize = 34f
                                        gravity = Gravity.CENTER
                                        typeface = Typeface.DEFAULT_BOLD

                                    }.lparams(width = wrapContent, height = wrapContent)
                                    textView {
                                        id = teamAwayScore
                                        gravity = Gravity.CENTER
                                        textSize = 40f
                                        typeface = Typeface.DEFAULT_BOLD

                                    }.lparams(width = wrapContent, height = wrapContent)
                                }.lparams(width = wrapContent, height = matchParent){
                                    weight = 0F;
                                }

                                verticalLayout {
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                    imageView {
                                        id = teamAwayLogo
                                        verticalPadding = dip(10)
                                    }.lparams(width = dip(125), height = dip(125))
                                    textView {
                                        id = teamAway
                                        textSize = 20f
                                        gravity = Gravity.CENTER
                                        textColor = R.color.colorPrimaryDark
                                        typeface = Typeface.DEFAULT_BOLD
                                        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
                                    }.lparams(width = dip(125), height = wrapContent)
                                }.lparams(width = matchParent, height = wrapContent) {
                                    weight = 1F;
                                }
                            }

                            view {
                                background = ContextCompat.getDrawable(ctx, R.color.colorText2)
                            }.lparams(width = matchParent, height = dip(2))

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                lparams(width = matchParent, height = wrapContent) {
                                    verticalPadding = dip(6)
                                }
                                textView {
                                    id = teamHomeGoals
                                    typeface = Typeface.DEFAULT_BOLD
                                    textSize = 16f
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.START
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    text = context.getString(R.string.Goals)
                                    typeface = Typeface.DEFAULT_BOLD
                                    horizontalPadding = dip(6)
                                    textSize = 16f
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    id = teamAwayGoals
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.END
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                            }

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                lparams(width = matchParent, height = wrapContent) {
                                    bottomPadding = dip(16)
                                }
                                textView {
                                    id = teamHomeShots
                                    textSize = 18f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.START
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    text = context.getString(R.string.Shots)
                                    horizontalPadding = dip(6)
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    id = teamAwayShots
                                    textSize = 18f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.END
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                            }

                            view {
                                background = ContextCompat.getDrawable(ctx, R.color.colorText2)
                            }.lparams(width = matchParent, height = dip(2))

                            textView {
                                text = "Lineups"
                                verticalPadding = dip(8)
                                textSize = 18f
                                typeface = Typeface.DEFAULT_BOLD
                                gravity = Gravity.CENTER
                            }.lparams(width = matchParent, height = wrapContent)

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                lparams(width = matchParent, height = wrapContent) {
                                    verticalPadding = dip(4)
                                }
                                textView {
                                    id = teamHomeGK
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.START
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    text = context.getString(R.string.GK)
                                    horizontalPadding = dip(6)
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    id = teamAwayGK
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.END
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                            }

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                lparams(width = matchParent, height = wrapContent) {
                                    verticalPadding = dip(4)
                                }
                                textView {
                                    id = teamHomeDF
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.START
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    text = context.getString(R.string.DF)
                                    horizontalPadding = dip(6)
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    id = teamAwayDF
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.END
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                            }

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                lparams(width = matchParent, height = wrapContent) {
                                    verticalPadding = dip(4)
                                }
                                textView {
                                    id = teamHomeMF
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.START
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    text = context.getString(R.string.MF)
                                    horizontalPadding = dip(6)
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    id = teamAwayMF
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.END
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                            }

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                lparams(width = matchParent, height = wrapContent) {
                                    verticalPadding = dip(4)
                                }
                                textView {
                                    id = teamHomeFW
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.START
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    text = context.getString(R.string.FW)
                                    horizontalPadding = dip(6)
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    id = teamAwayFW
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.END
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                            }

                            linearLayout {
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                lparams(width = matchParent, height = wrapContent) {
                                    verticalPadding = dip(4)
                                }
                                textView {
                                    id = teamHomeSub
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.START
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    text = context.getString(R.string.Subs)
                                    horizontalPadding = dip(6)
                                    textSize = 16f
                                    typeface = Typeface.DEFAULT_BOLD
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                                textView {
                                    id = teamAwaySub
                                    textSize = 16f
                                    gravity = Gravity.CENTER_HORIZONTAL or Gravity.END
                                    typeface = Typeface.DEFAULT_BOLD
                                }.lparams(width = matchParent, height = wrapContent){
                                    weight = 1F
                                }
                            }
                        }

                        progressBar {
                            id = progressBar
                        }
                    }
                }
            }
        }
    }
}
