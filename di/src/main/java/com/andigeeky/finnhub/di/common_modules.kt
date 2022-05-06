package com.andigeeky.finnhub.di

import com.andigeeky.finnhub.cache.di.database_modules
import com.andigeeky.finnhub.data.di.data_modules
import com.andigeeky.finnhub.network.di.network_module

val common_modules = data_modules + database_modules + network_module