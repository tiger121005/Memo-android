package app.ito.yomi.memo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    val title: TextView = item.findViewById(R.id.title)
    val text: TextView = item.findViewById(R.id.text)
}