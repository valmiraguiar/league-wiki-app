package com.valmiraguiar.detail.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.azul.domain.business.DetailChampionBusiness
import com.valmiraguiar.core.sharedentity.champion.ChampionDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChampionDetailViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val detailChampionBusiness: DetailChampionBusiness = mockk(relaxed = true)

    private lateinit var viewModel: ChampionDetailViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        viewModel = ChampionDetailViewModel(
            coroutineDispatcher = testDispatcher,
            detailChampionBusiness = detailChampionBusiness
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadChampionDetail() = runTest {
        // ARRANGE
        val mockChampionDetail = ChampionDetail(
            id = "1",
            key = "1",
            name = "Graves",
            title = "1",
            lore = "1",
            blurb = "1",
        )
        coEvery { detailChampionBusiness.getDetailChampion(any()) } returns flowOf(
            mockChampionDetail
        )

        // ACT
        viewModel.loadChampionDetail("Graves")

        // ASSERT
        viewModel.detailChampionState.test {
            var value = awaitItem()
            assertEquals(DetailChampionState.Loading, value)

            value = awaitItem()
            assertEquals(DetailChampionState.Success(mockChampionDetail), value)
        }
    }
}