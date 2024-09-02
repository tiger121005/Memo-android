package app.ito.yomi.memo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.ito.yomi.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var list: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        updateList(list)

        binding.addBtn.setOnClickListener {
            list.add(binding.editText.text.toString())
            binding.editText.setText("")
            updateList(list)
        }
    }

    fun updateList(data: MutableList<String>) {
        val adapter = CustomAdapter(this, list)
        binding.listview.adapter = adapter
    }

}