package com.andigeeky.finhubapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.andigeeky.finhubapp.common.ui.theme.FinhubAppTheme
import com.andigeeky.finhubapp.travelperk.TravelPerkViewScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinhubAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TravelPerkViewScreen()
                }
            }
        }
    }
}