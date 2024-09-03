package app.ito.yomi.memo

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import app.ito.yomi.memo.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        binding.listview.setOnItemClickListener { parent, view, position, id ->
            val memo = list[position]
            val editIntent = Intent(this, EditActivity::class.java)
            editIntent.putExtra("id", memo.id)
            editIntent.putExtra("title", memo.title)
            editIntent.putExtra("text", memo.text)
            startActivity(editIntent)
        }
    }

    fun updateList(data: MutableList<MemoData>) {
        val adapter = CustomAdapter(this, list)
        binding.listview.adapter = adapter
    }

}