package com.andigeeky.finhubapp.ipo.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.andigeeky.finhubapp.ipo.ui.model.IPOUpcomingCalendarState
import io.uniflow.android.livedata.states
import org.koin.androidx.compose.get

@Composable
fun UpcomingIPOCalendarScreen(vm : UpcomingIPOCalendarViewModel = get()) {
    vm.states.observeAsState().let {
        val state = it.value
        if (state is IPOUpcomingCalendarState){
            Text(state.toString())
        }
    }
}