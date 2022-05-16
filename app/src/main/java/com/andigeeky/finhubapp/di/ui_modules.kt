package com.andigeeky.finhubapp.di

import com.andigeeky.finhubapp.ipo.ui.UpcomingIPOCalendarViewModel
import com.andigeeky.finnhub.di.common_modules
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val ipo_ui_module = module {
    viewModel { UpcomingIPOCalendarViewModel(get()) }
}
private val ui_modules = module {

} + ipo_ui_module

val di_modules = ui_modules + common_modules