package com.mykoatlincoadingsample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mykoatlincoadingsample.model.Employee
import com.mykoatlincoadingsample.room.RoomDetails.SAMPLE_DATABASE

// Annotates class to be a Room Database with a table (entity)
@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class SampleRoomDatabase : RoomDatabase() {

    abstract fun sampleDao(): SampleDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: SampleRoomDatabase? = null

        fun getDatabase(context: Context): SampleRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SampleRoomDatabase::class.java,
                    SAMPLE_DATABASE
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}