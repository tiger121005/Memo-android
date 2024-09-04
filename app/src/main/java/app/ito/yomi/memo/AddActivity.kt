package app.ito.yomi.memo

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import app.ito.yomi.memo.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    val viewModel: MemoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }


        binding.addBtn.setOnClickListener {

            tapAddButton()
        }
    }

    fun tapAddButton() {
        viewModel.insert(binding.editTitle.text.toString(), binding.editText.text.toString())
        finish()
    }
}