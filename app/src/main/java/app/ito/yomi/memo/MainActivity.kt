package app.ito.yomi.memo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.ito.yomi.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    var list: MutableList<MemoData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

       val viewModel: MemoViewModel by viewModels()

        viewModel.memoAllData.observe(this, Observer { value ->
            list = value.toMutableList()
            updateList(list)
        })

        listInit()

        binding.addBtn.setOnClickListener {
            tapAddButton()
        }

    }

    fun listInit() {
        val adapter = RecyclerAdapter(list, object : RecyclerAdapter.OnMemoCellClickListener {
            override fun onItemClick(view: View, position: Int, memo: MemoData) {
                moveToDetailActivity(memo)
            }
        })
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val divider = androidx.recyclerview.widget.DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        recyclerView.addItemDecoration(divider)
    }

    fun updateList(list: MutableList<MemoData>) {
        recyclerView.adapter = RecyclerAdapter(list, object : RecyclerAdapter.OnMemoCellClickListener {
            override fun onItemClick(view: View, position: Int, memo: MemoData) {
                moveToDetailActivity(memo)
            }
        })
    }

    fun moveToDetailActivity(memo: MemoData) {
        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
        detailIntent.putExtra("id", memo.id)
        detailIntent.putExtra("title", memo.title)
        detailIntent.putExtra("text", memo.text)
        startActivity(detailIntent)
    }

    fun tapAddButton() {
        val addIntent = Intent(this, AddActivity::class.java)
        startActivity(addIntent)
    }

}