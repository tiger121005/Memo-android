package app.ito.yomi.memo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: MemoDao
    init {

        dao = MemoDatabase.getDatabase(application).memoDao() // 使用するDaoを指定

    }

    // DBに保管された内容を表示
    val memoAllData = dao.getAllMemos()

    // DBにデータを保存
    fun insert(title: String, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(MemoData(title = title, text = text))
        }
    }

    fun update(memo: MemoData) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(memo)
        }
    }

    fun delete(memo: MemoData) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(memo)
        }
    }

}