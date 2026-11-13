package com.griffith.smoothdrive


import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

@Database(entities = [Vehicle::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder<AppDatabase>(
                    context = context.applicationContext,
                    name = context.applicationContext
                        .getDatabasePath("smoothdrive.db").absolutePath
                )
                    .setDriver(BundledSQLiteDriver())
                    .build()
                    .also { instance = it }
            }
    }
}