package com.valmiraguiar.home.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.valmiraguiar.core.sharedentity.champion.Champion
import com.valmiraguiar.domain.business.ChampionBusiness
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
class HomeViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val championsBusiness: ChampionBusiness = mockk(relaxed = true)

    private lateinit var viewModel: HomeViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        viewModel = HomeViewModel(
            coroutineDispatcher = testDispatcher,
            championsBusiness = championsBusiness
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadChampions_shouldLoadChampion_whenViewModelHasStarted() = runTest {
        val mockList = listOf(
            Champion(
                id = "1",
                key = "1",
                name = "Graves",
                title = "1",
                blurb = "1"
            )
        )
        coEvery { championsBusiness.getChampionList() } returns flowOf(mockList)

        viewModel.championListState.test {
            var value = awaitItem()
            assertEquals(ChampionListState.Loading, value)

            value = awaitItem()
            assertEquals(ChampionListState.Success(mockList), value)

        }
    }

    @Test
    fun sortChampions_shouldSortListByNameAscending() = runTest {
        val mockList = listOf(
            Champion(
                id = "1",
                key = "1",
                name = "Graves",
                title = "1",
                blurb = "1"
            ),
            Champion(
                id = "2",
                key = "2",
                name = "Ahri",
                title = "2",
                blurb = "2"
            ),
            Champion(
                id = "3",
                key = "3",
                name = "Zed",
                title = "3",
                blurb = "3"
            )
        )
        coEvery { championsBusiness.getChampionList() } returns flowOf(mockList.sortedBy { it.name })

        val sortedListByNameAscending = mockList.sortedBy {
            it.name
        }

        viewModel.championListState.test {
            var value = awaitItem()
            assertEquals(ChampionListState.Loading, value)

            value = awaitItem()
            assertEquals(ChampionListState.Success(sortedListByNameAscending), value)
        }
    }

    @Test
    fun sortChampions_shouldSortListByNameDescending() = runTest {
        val mockList = listOf(
            Champion(
                id = "1",
                key = "1",
                name = "Graves",
                title = "1",
                blurb = "1"
            ),
            Champion(
                id = "2",
                key = "2",
                name = "Ahri",
                title = "2",
                blurb = "2"
            ),
            Champion(
                id = "3",
                key = "3",
                name = "Zed",
                title = "3",
                blurb = "3"
            )
        )
        coEvery { championsBusiness.getChampionList() } returns flowOf(mockList.sortedByDescending { it.name })

        val sortedListByNameAscending = mockList.sortedByDescending {
            it.name
        }

        viewModel.championListState.test {
            var value = awaitItem()
            assertEquals(ChampionListState.Loading, value)

            value = awaitItem()
            assertEquals(ChampionListState.Success(sortedListByNameAscending), value)
        }
    }

}