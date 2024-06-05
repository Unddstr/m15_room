package com.example.room

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.room.databinding.ActivityMainBinding
import com.example.room.db.Word
import com.example.room.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val userDao = (application as App).db.userDao()
                return MainViewModel(userDao) as T
            }
        }
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            viewModel.onAddButton(binding.enteredText.text.toString())
        }

        binding.enteredText.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty() && text.matches(Regex("""^[А-Яа-яA-Za-z\-]+${'$'}"""))) {
                binding.buttonAdd.setEnabled(true)
                binding.textInput.isErrorEnabled = false
            } else {
                binding.buttonAdd.setEnabled(false)
                binding.textInput.isErrorEnabled = true
                binding.textInput.error = resources.getString(R.string.error_text)
            }
        }

        binding.buttonClear.setOnClickListener {
            viewModel.onResetButton()
        }

        fun getMainText(words: List<Word>): String {
            var text = ""

            for (word in words) {
                text += "${word.word} - ${word.repeatCount} reps.\n"
            }

            return text
        }

        lifecycleScope.launchWhenCreated {
            viewModel.firstFiveWords
                .collect { words ->
                    binding.textView
                        .text = getMainText(words)
                }
        }
    }
}