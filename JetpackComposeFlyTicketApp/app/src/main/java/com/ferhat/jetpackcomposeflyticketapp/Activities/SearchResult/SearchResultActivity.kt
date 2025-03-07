package com.ferhat.jetpackcomposeflyticketapp.Activities.SearchResult

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ferhat.jetpackcomposeflyticketapp.Activities.Splash.StatusTopBarColor
import com.ferhat.jetpackcomposeflyticketapp.ViewModel.MainViewModel

class SearchResultActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()
    private var from: String = ""
    private var to: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        from = intent.getStringExtra("from") ?: ""
        to = intent.getStringExtra("to") ?: ""



        setContent {
            StatusTopBarColor()
        }


    }
}