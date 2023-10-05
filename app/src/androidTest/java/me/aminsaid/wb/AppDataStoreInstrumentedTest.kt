package me.aminsaid.wb

import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.cityinput.domain.model.Country
import me.aminsaid.data.di.DataStoreModule
import me.aminsaid.data.local.AppDataStore
import me.aminsaid.wb.presentation.MainActivity
import org.junit.*
import org.junit.runner.RunWith

@HiltAndroidTest
@UninstallModules(DataStoreModule::class)
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class AppDataStoreInstrumentedTest {

    private val mockCity = City(
        LocalizedName = "Cairo",
        Key = "12345",
        Country = Country(LocalizedName = "Egypt")
    )

    private lateinit var appDataStore: AppDataStore

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val dataStoreFile = context.dataStoreFile("test_task_weather_app.preferences_pb")
        val dataStore = PreferenceDataStoreFactory.create { dataStoreFile }
        appDataStore = AppDataStore(dataStore)
    }

    @Test
    fun testDataStore() {
        // Launch the activity using ActivityScenario
        val activityScenario = launch(MainActivity::class.java)

        // Wait for the activity to be in a stable state
        activityScenario.onActivity { activity ->
            runBlocking {
                val city = mockCity

                // When saving the city
                appDataStore.saveCity(city)

                // Then the cityFlow should emit the saved city
                val savedCity = appDataStore.cityFlow.first()
                assertEquals(city, savedCity)

                // When clearing the city
                appDataStore.clearCity()

                // Then the cityFlow should emit null
                val clearedCity = appDataStore.cityFlow.first()
                Assert.assertNull(clearedCity?.Key)
            }
        }

        // Close the activity when done
        activityScenario.close()
    }

}