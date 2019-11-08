package com.sebastianfarias.simpleapimusic.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.sebastianfarias.simpleapimusic.R
import com.sebastianfarias.simpleapimusic.view.search.SearchActivity
import kotlinx.android.synthetic.main.splash_screen.*

class SplashActivity : AppCompatActivity() {

    private val time = 2500L
    private lateinit var handlerDelay: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        supportActionBar?.hide()

        val bounceAnimation = AnimationUtils.loadAnimation(this,R.anim.bounce)
        iv_splash.startAnimation(bounceAnimation)

        handlerDelay = Handler()
        handlerDelay.postDelayed({
            startActivity(Intent(applicationContext, SearchActivity::class.java))
            finish()
        }, time)
    }


}