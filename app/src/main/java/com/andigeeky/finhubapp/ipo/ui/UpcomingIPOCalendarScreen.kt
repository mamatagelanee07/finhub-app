package com.andigeeky.finhubapp.ipo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.andigeeky.finhubapp.R
import com.andigeeky.finhubapp.common.ui.components.ContentErrorView
import com.andigeeky.finhubapp.common.ui.components.LoadingView
import com.andigeeky.finhubapp.ipo.ui.model.IPOUpcomingCalendarState
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import io.uniflow.android.livedata.states
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun UpcomingIPOCalendarScreen(vm: UpcomingIPOCalendarViewModel = get()) {
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    vm.states.observeAsState().let { vmState ->
        Scaffold(scaffoldState = rememberScaffoldState(snackbarHostState = snackBarState)) {
            val state = vmState.value
            if (state is IPOUpcomingCalendarState) {
                Column {
                    UpComingIPOTopAppBar()
                    LoadingView(state.loading) {
                        if (state.error != null && state.ipoCalendars.isNullOrEmpty()) ContentErrorView(
                            click = { vm.reload() },
                            message = stringResource(state.error),
                            buttonText = stringResource(R.string.retry)
                        ) else {
                            state.ipoCalendars?.let { calendars ->
                                IPOCalendarList(calendars = calendars)
                            }
                        }
                    }
                    state.error?.let {
                        if (state.ipoCalendars?.isNotEmpty() == true) {
                            val message = stringResource(state.error)
                            val retry = stringResource(R.string.retry)
                            scope.launch {
                                val result = snackBarState.showSnackbar(
                                    message = message,
                                    actionLabel = retry,
                                    duration = SnackbarDuration.Indefinite
                                )
                                when (result) {
                                    SnackbarResult.Dismissed -> {
                                        // Do nothing
                                    }
                                    SnackbarResult.ActionPerformed -> {
                                        vm.reload()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UpComingIPOTopAppBar() {
    TopAppBar(
        modifier = Modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp
                ),
            text = stringResource(id = R.string.ipo_calendar_title),
            style = MaterialTheme.typography.h6
        )
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