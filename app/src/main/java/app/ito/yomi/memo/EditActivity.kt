package app.ito.yomi.memo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.ito.yomi.memo.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        val viewModel: MemoViewModel by viewModels()

        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")  ?: ""
        val text = intent.getStringExtra("text") ?: ""

        binding.editTitle.setText(title)
        binding.editText.setText(text)

        binding.saveBtn.setOnClickListener {
            viewModel.update(MemoData(id = id, title = binding.editTitle.text.toString(), text = binding.editText.text.toString()))
            finish()
        }

        binding.deleteBtn.setOnClickListener {
            viewModel.delete(MemoData(id = id, title = binding.editTitle.text.toString(), text = binding.editText.text.toString()))
            finish()
        }
    }
}