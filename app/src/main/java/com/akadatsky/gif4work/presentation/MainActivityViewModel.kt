package com.akadatsky.gif4work.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akadatsky.gif4work.data.Data
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivityViewModel : ViewModel(), KoinComponent {

    private val repository: Repository by inject()

    fun performSearch(query: String): LiveData<List<Data>> {
        val result = MutableLiveData<List<Data>>()
        viewModelScope.launch {
            result.value = repository.performSearch(query)
        }
        return result
    }

}