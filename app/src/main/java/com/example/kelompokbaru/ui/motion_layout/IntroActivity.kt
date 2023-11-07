package com.example.kelompokbaru.ui.motion_layout

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.kelompokbaru.MainActivity
import com.example.kelompokbaru.R
import com.example.kelompokbaru.ui.login.MainActivity_Login
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList
import java.util.List

class IntroActivity : AppCompatActivity() {

    private lateinit var screenPager: ViewPager
    private lateinit var introViewPagerAdapter: IntroViewPagerAdapter
    private lateinit var tabIndicator: TabLayout
    private lateinit var btnNext: Button
    private var position = 0
    private lateinit var btnGetStarted: Button
    private lateinit var btnAnim: Animation
    private lateinit var tvSkip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Make the activity full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Check if the intro screen has been opened before
        if (restorePrefData()) {
            val mainActivity = Intent(applicationContext, MainActivity_Login::class.java)
            startActivity(mainActivity)
            finish()
        }

        setContentView(R.layout.activity_intro)

        // Hide the action bar
        supportActionBar?.hide()

        // Initialize views
        btnNext = findViewById(R.id.btn_next)
        btnGetStarted = findViewById(R.id.btn_get_started)
        tabIndicator = findViewById(R.id.tab_indicator)
        btnAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)
        tvSkip = findViewById(R.id.tv_skip)

        // Fill list screen
        val mList = ArrayList<ScreenItem>()
        mList.add(ScreenItem("Fresh Food", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit", R.drawable.img1))
        mList.add(ScreenItem("Fast Delivery", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit", R.drawable.img2))
        mList.add(ScreenItem("Easy Payment", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua, consectetur  consectetur adipiscing elit", R.drawable.img3))

        // Setup viewpager
        screenPager = findViewById(R.id.screen_viewpager)
        introViewPagerAdapter = IntroViewPagerAdapter(this, mList)
        screenPager.adapter = introViewPagerAdapter

        // Setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager)

        // Next button click listener
        btnNext.setOnClickListener {
            position = screenPager.currentItem
            if (position < mList.size) {
                position++
                screenPager.currentItem = position
            }
            if (position == mList.size - 1) {
                loaddLastScreen()
            }
        }

        // Tablayout add change listener
        tabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == mList.size - 1) {
                    loaddLastScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // Get Started button click listener
        btnGetStarted.setOnClickListener {
            val mainActivity = Intent(applicationContext, MainActivity_Login::class.java)
            startActivity(mainActivity)
            savePrefsData()
            finish()
        }

        // Skip button click listener
        tvSkip.setOnClickListener { screenPager.currentItem = mList.size }
    }

    private fun restorePrefData(): Boolean {
        val pref = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        return pref.getBoolean("isIntroOpnend", false)
    }

    private fun savePrefsData() {
        val pref = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isIntroOpnend", true)
        editor.apply()
    }

    private fun loaddLastScreen() {
        btnNext.visibility = View.INVISIBLE
        btnGetStarted.visibility = View.VISIBLE
        tvSkip.visibility = View.INVISIBLE
        tabIndicator.visibility = View.INVISIBLE
        // TODO: Add an animation to the getstarted button
        // Setup animation
        btnGetStarted.startAnimation(btnAnim)
    }
}