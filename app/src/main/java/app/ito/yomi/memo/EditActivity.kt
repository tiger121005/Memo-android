package app.ito.yomi.memo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import app.ito.yomi.memo.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity(), NoticeDialogListener {

    private lateinit var binding: ActivityEditBinding
    private val viewModel: MemoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater).apply { setContentView(this.root) }



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
            DeleteDialogFragment().show(supportFragmentManager, "delete")
        }
    }

    override fun onDialogPositiveClick(dialog: DeleteDialogFragment) {
        viewModel.delete(MemoData(id = intent.getIntExtra("id", 0), title = binding.editTitle.text.toString(), text = binding.editText.text.toString()))
        finish()
    }

    override fun onDialogNegativeClick(dialog: DeleteDialogFragment) {

    }
}