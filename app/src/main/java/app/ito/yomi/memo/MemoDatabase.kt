package app.ito.yomi.memo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.ito.yomi.memo.MemoDao
import app.ito.yomi.memo.MemoData

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [MemoData::class], version = 2, exportSchema = false)
abstract class MemoDatabase : RoomDatabase() {

    abstract fun memoDao(): MemoDao
    companion object {
        @Volatile
        private var instance: MemoDatabase? = null

        fun getDatabase(context: Context): MemoDatabase{
            return instance ?: synchronized(this){
                Room.databaseBuilder(context, MemoDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}