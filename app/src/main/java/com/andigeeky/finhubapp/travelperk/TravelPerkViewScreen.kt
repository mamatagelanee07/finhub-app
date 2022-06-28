package com.andigeeky.finhubapp.travelperk

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import io.uniflow.android.livedata.states
import org.koin.androidx.compose.get

@Composable
fun TravelPerkViewScreen(vm: PointOfInterestsViewModel = get()) {
    vm.states.observeAsState().value?.let { state ->
        (state as? PointOfInterestsState)?.let {
            GoogleMap(modifier = Modifier.fillMaxSize()) {
                it.interests?.forEach { interest ->
                    Marker(
                        position = LatLng(
                            interest.location.coordinates.lat,
                            interest.location.coordinates.long,
                        ),
                        title = interest.name,
                        snippet =  interest.location.formattedAddress.first()
                    )
                }
            }
        }
    }
}