package app.ito.yomi.memo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import app.ito.yomi.memo.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private val viewModel: MemoViewModel by viewModels()

    private var id: Int = 0
    private var title: String = ""
    private var text: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        getFromDetail()
        setText()

        binding.saveBtn.setOnClickListener {
            tapSaveButton()
        }


    }

    fun getFromDetail() {
        id = intent.getIntExtra("id", 0)
        title = intent.getStringExtra("title")  ?: ""
        text = intent.getStringExtra("text") ?: ""
    }

    fun setText() {
        binding.editTitle.setText(title)
        binding.editText.setText(text)
    }

    fun tapSaveButton() {
        viewModel.update(MemoData(id = id, title = binding.editTitle.text.toString(), text = binding.editText.text.toString()))
        finish()
    }


}