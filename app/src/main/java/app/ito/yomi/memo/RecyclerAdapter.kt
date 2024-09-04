package app.ito.yomi.memo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    val list: MutableList<MemoData>,
    private val listener: OnMemoCellClickListener
) : RecyclerView.Adapter<ViewHolder>() {

    interface OnMemoCellClickListener {
        fun onItemClick(view: View, position: Int, memo: MemoData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val memo = list[position]
        holder.title.text = memo.title
        holder.text.text = memo.text

        holder.itemView.setOnClickListener {
            Log.d("RecyclerAdapter", "onBindViewHolder: $position")
            listener.onItemClick(it, position, memo)
        }
    }

    override fun getItemCount() = list.size

}