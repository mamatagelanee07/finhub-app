package com.andigeeky.finhubapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.andigeeky.finhubapp.ipo.ui.UpcomingIPOCalendarViewModel
import com.andigeeky.finhubapp.ui.theme.FinhubAppTheme
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinhubAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(vm : UpcomingIPOCalendarViewModel = get()) {
    Text(text = vm.getName())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FinhubAppTheme {
        Greeting()
    }
}