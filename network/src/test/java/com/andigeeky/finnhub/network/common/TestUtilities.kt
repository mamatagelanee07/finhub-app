package com.andigeeky.finnhub.network.common

import okhttp3.mockwebserver.MockResponse
import java.io.File

object TestUtilities {
    fun getMockedHttpResponse(
        fileName: String? = null,
        responseCode: Int? = null
    ): MockResponse {
        val mockResponse = MockResponse()
        responseCode?.let {
            mockResponse.setResponseCode(it)
        }
        fileName?.let {
            mockResponse.setBody(getJson("api-responses/$fileName"))
        }
        return mockResponse
    }

    private fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}