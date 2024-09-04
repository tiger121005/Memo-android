package app.ito.yomi.memo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.switchMap
import kotlinx.coroutines.withContext

class MemoViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: MemoDao
    init {

        dao = MemoDatabase.getDatabase(application).memoDao() // 使用するDaoを指定

    }

    // 現在表示するメモのLiveData
    private val _memoData = MutableLiveData<LiveData<MemoData>>()
    val memoData: LiveData<MemoData> = _memoData.switchMap { memo ->
        memo
    }

    // idを基にメモを取得し、LiveDataを更新する
    fun fetchMemo(id: Int) {
        _memoData.value = dao.getMemo(id)
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