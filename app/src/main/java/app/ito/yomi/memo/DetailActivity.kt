package app.ito.yomi.memo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.ito.yomi.memo.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), NoticeDialogListener {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: MemoViewModel by viewModels()
    private lateinit var memo: MemoData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val id = intent.getIntExtra("id", 0)

        viewModel.fetchMemo(id)

        viewModel.memoData.observe(this) { memoData ->
            memoData?.let { memoData ->
                memo = memoData
                binding.titleView.text = memoData.title
                binding.textView.text = memoData.text
            } ?: run {
                // メモデータが存在しない場合の処理
                binding.titleView.text = "No Memo Found"
                binding.textView.text = ""
            }
        }


        binding.editBtn.setOnClickListener {
            val editIntent = intent.apply {
                setClass(this@DetailActivity, EditActivity::class.java)
            }
            editIntent.putExtra("id", id)
            editIntent.putExtra("title", memo.title)
            editIntent.putExtra("text", memo.text)
            startActivity(editIntent)
        }

        binding.deleteBtn.setOnClickListener {
            DeleteDialogFragment().show(supportFragmentManager, "delete")
        }
    }

    override fun onDialogPositiveClick(dialog: DeleteDialogFragment) {
        viewModel.delete(MemoData(id = intent.getIntExtra("id", 0), title = binding.titleView.text.toString(), text = binding.textView.text.toString()))
        finish()
    }

    override fun onDialogNegativeClick(dialog: DeleteDialogFragment) {

    }
}