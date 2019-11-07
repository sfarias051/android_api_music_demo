package com.sebastianfarias.simpleapimusic.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.sebastianfarias.simpleapimusic.R

open class BaseActivity : AppCompatActivity() {

    private val TAG = BaseActivity::class.java.simpleName
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    fun hasInternetConnection(): Boolean {
        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connManager.activeNetworkInfo
        val connected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        if(!connected){
            Toast.makeText(this, R.string.general_error_connection, Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    fun sendAnalyticsData(screenName: String){
        val bundle = Bundle()
        bundle.putString("screen_name", screenName)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, screenName)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
        Log.d(TAG, "FIREBASE DATA FOR : $screenName")
    }

}