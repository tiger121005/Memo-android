package app.ito.yomi.memo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memos")
data class MemoData (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String
){

}