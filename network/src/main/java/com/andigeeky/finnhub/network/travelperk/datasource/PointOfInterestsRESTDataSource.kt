package com.andigeeky.finnhub.network.travelperk.datasource

import com.andigeeky.finnhub.data.travelperk.datasource.PointOfInterestsNetworkDataSource
import com.andigeeky.finnhub.domain.ipo.models.*
import com.andigeeky.finnhub.network.common.retrofitApiCall
import com.andigeeky.finnhub.network.travelperk.service.FourSquareService

class PointOfInterestsRESTDataSource(private val service: FourSquareService) :
    PointOfInterestsNetworkDataSource {

    override suspend fun getPointOfInterest(latLog: LatLong) = retrofitApiCall(
        call = { service.getPointOfInterests(
            latLong = "${latLog.lat},${latLog.long}",
            clientId = "SFTEX4E45MNUAZXMABQOF4NVEBEIOYKE53ZNMIP40RX2S4CK",
            clientSecret = "5AGNN0IDYHURCDITIZX1PAGMFHEO4O0I4VEBLPKMJEPTQPH4",
            version = "20190717"
        ) },
        parse = {
            it.response.interests.map { remote ->
                POInterest(
                    name = remote.name,
                    location = Location(
                        coordinates = LatLong(
                            lat = remote.location.lat,
                            long = remote.location.lng,
                        ),
                        formattedAddress = remote.location.formattedAddress.orEmpty()
                    )
                )
            }
        }
    )
}