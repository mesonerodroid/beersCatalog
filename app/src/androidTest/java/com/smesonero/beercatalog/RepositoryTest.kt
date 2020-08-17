package com.smesonero.beercatalog

import android.content.Context
import android.os.SystemClock
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.smesonero.beercatalog.ddbb.BeerDatabase
import com.smesonero.beercatalog.ddbb.dao.BeerDao
import com.smesonero.beercatalog.domain_model.Beer
import com.smesonero.beercatalog.service.BeerRepository
import com.smesonero.beercatalog.viewmodel.BeerViewModel
import junit.framework.Assert.*
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import java.io.IOException
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class RepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var  beerDao:BeerDao
    lateinit var beerViewModel: BeerViewModel
    lateinit var beerRepository: BeerRepository
    lateinit var db:BeerDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BeerDatabase::class.java).build()
        beerDao = db.beerDao()
        beerRepository = BeerRepository(beerDao)
        beerViewModel = BeerViewModel(beerRepository)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.smesonero.beercatalog", appContext.packageName)
    }

    @Test
    fun numEntriesTest(){
        var list : List<Beer> = listOf()
        beerViewModel.getLiveData()
        beerViewModel.beerLiveData.observeOnce {
            list = it
        }
        Thread.sleep(2_000)
        assertEquals(list.size, 100)
    }

    @Test
    fun beerNamesNotNull(){
        var list : List<Beer> = listOf()
        beerViewModel.getLiveData()
        beerViewModel.beerLiveData.observeOnce {
            list = it
        }
        Thread.sleep(2_000)
        list.forEach{
            assertNotNull(it.name)
        }
    }
    @Test
    fun beerNamesNotEmpty(){
        var list : List<Beer> = listOf()
        beerViewModel.getLiveData()
        beerViewModel.beerLiveData.observeOnce {
            list = it
        }
        Thread.sleep(2_000)
        list.forEach{
            assertNotSame(it.name, "")
        }
    }

    @Test
    fun checkFirstElementLetter(){
        var list : List<Beer> = listOf()
        beerViewModel.getLiveData()
        beerViewModel.beerLiveData.observeOnce {
            list = it
        }
        Thread.sleep(2_000)
        assertNotNull(list.get(0).name)
        assertTrue(list.get(0).name.startsWith("B"))
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}