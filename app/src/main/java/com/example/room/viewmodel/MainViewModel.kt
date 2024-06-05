package com.example.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.db.NewWord
import com.example.room.db.WordDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val wordDao: WordDao) : ViewModel() {

    val firstFiveWords = this.wordDao.getFirstFive()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )

    fun onAddButton(wordToAdd: String) {
        viewModelScope.launch {
            when (wordDao.getWord(wordToAdd)) {
                null -> wordDao.insert(NewWord(word = wordToAdd, repeatCount = 1))
                else -> wordDao.update(wordToAdd)
            }
        }
    }

    fun onResetButton() {
        viewModelScope.launch {
            wordDao.delete()
        }
    }
}
