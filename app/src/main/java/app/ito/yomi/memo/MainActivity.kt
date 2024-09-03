package app.ito.yomi.memo

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.ito.yomi.memo.databinding.ActivityMainBinding
import java.io.File

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


        binding.addBtn.setOnClickListener {
            val addIntent = Intent(this, AddActivity::class.java)
            startActivity(addIntent)
        }

        val adapter = RecyclerAdapter(list, object : RecyclerAdapter.OnMemoCellClickListener {
            override fun onItemClick(view: View, position: Int, memo: MemoData) {
                val editIntent = Intent(this@MainActivity, EditActivity::class.java)
                editIntent.putExtra("id", memo.id)
                editIntent.putExtra("title", memo.title)
                editIntent.putExtra("text", memo.text)
                startActivity(editIntent)
            }
        })
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val divider = androidx.recyclerview.widget.DividerItemDecoration(this, LinearLayoutManager(this).orientation)
        recyclerView.addItemDecoration(divider)

//        adapter.setOnMemoCellClickListener(
//
//            object : RecyclerAdapter.OnMemoCellClickListener {
//                override fun onItemClick(view: View, position: Int, memo: MemoData) {
//                    val editIntent = Intent(this@MainActivity, EditActivity::class.java)
//                    editIntent.putExtra("id", memo.id)
//                    editIntent.putExtra("title", memo.title)
//                    editIntent.putExtra("text", memo.text)
//                    startActivity(editIntent)
//                }
//            }
//        )


    }

    fun updateList(list: MutableList<MemoData>) {
        recyclerView.adapter = RecyclerAdapter(list, object : RecyclerAdapter.OnMemoCellClickListener {
            override fun onItemClick(view: View, position: Int, memo: MemoData) {
                val editIntent = Intent(this@MainActivity, EditActivity::class.java)
                editIntent.putExtra("id", memo.id)
                editIntent.putExtra("title", memo.title)
                editIntent.putExtra("text", memo.text)
                startActivity(editIntent)
            }
        })
    }

}