package com.andigeeky.finhubapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.andigeeky.finhubapp.ipo.ui.UpcomingIPOCalendarScreen
import com.andigeeky.finhubapp.ui.theme.FinhubAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinhubAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    UpcomingIPOCalendarScreen()
                }
            }
        }
    }
}