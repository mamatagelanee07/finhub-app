package com.andigeeky.finhubapp.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.uniflow.core.logger.DebugMessageLogger
import io.uniflow.core.logger.UniFlowLogger
import io.uniflow.test.rule.UniflowTestDispatchersRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class ViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val testDispatcherRule = UniflowTestDispatchersRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    init {
        UniFlowLogger.init(DebugMessageLogger())
    }
}
