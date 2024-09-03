package app.ito.yomi.memo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.ito.yomi.memo.databinding.ActivityAddBinding
import app.ito.yomi.memo.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        val viewModel: MemoViewModel by viewModels()

        binding.addBtn.setOnClickListener {

            viewModel.insert(binding.editText.text.toString())
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
}