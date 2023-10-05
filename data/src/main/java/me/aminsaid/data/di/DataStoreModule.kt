package me.aminsaid.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.aminsaid.core.utils.Constants.APP_PREFERENCES_NAME
import me.aminsaid.data.local.AppDataStore
import me.aminsaid.data.local.AppDataStoreBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create { context.dataStoreFile(APP_PREFERENCES_NAME) }
    }

    @Provides
    @Singleton
    fun provideAppDataStore(dataStore: DataStore<Preferences>): AppDataStoreBase {
        return AppDataStore(dataStore)
    }
}