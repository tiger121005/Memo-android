package app.ito.yomi.memo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(memo: MemoData)

    @Update
    fun update(memo: MemoData)

    @Delete
    fun delete(memo: MemoData)

    @Query("SELECT * FROM memos ORDER BY id DESC")
    fun getAllMemos(): LiveData<List<MemoData>>

    @Query("SELECT * FROM memos WHERE id = :id")
    fun getMemo(id: Int): LiveData<MemoData>
}