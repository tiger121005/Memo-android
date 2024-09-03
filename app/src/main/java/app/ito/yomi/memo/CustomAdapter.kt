package app.ito.yomi.memo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter(context: Context, list: MutableList<MemoData>): ArrayAdapter<MemoData>(context, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view  = convertView
        if (view == null) {
            // 一行分のレイアウトを生成
            view  = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        // 一行分のデータを取得
        val data = getItem(position) as MemoData
        view?.findViewById<TextView>(R.id.title)?.apply { text = data.title }
        view?.findViewById<TextView>(R.id.text)?.apply { text = data.text }
        return view!!
    }

}