package com.andigeeky.finhubapp.ipo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.andigeeky.finhubapp.ipo.ui.model.IPOUpcomingCalendarState
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import io.uniflow.android.livedata.states
import org.koin.androidx.compose.get

@Composable
fun UpcomingIPOCalendarScreen(vm: UpcomingIPOCalendarViewModel = get()) {
    vm.states.observeAsState().let {
        val state = it.value
        if (state is IPOUpcomingCalendarState) {
            IPOCalendarList(calendars = state.ipoCalendars)
        }
    }
}

@Composable
fun IPOCalendarList(calendars: List<IPOCalendar>) {
    LazyColumn {
        calendars.map { calendar ->
            item {
                IPOCalendarCard(calendar)
            }
        }
    }
}

@Composable
fun IPOCalendarCard(calendar: IPOCalendar) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = calendar.date.orEmpty(),
                    style = MaterialTheme.typography.overline
                )
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = calendar.name,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = calendar.exchange.orEmpty(),
                    style = MaterialTheme.typography.body2
                )
            }
            Column(
                modifier = Modifier.align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = calendar.symbol.orEmpty(),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.End
                )
                Text(
                    text = calendar.price.orEmpty(),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}