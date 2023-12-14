package com.bloo.shrimps2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val _labelSetChannel = Channel<Set<String>>(Channel.BUFFERED)
    val labelSetFlow = _labelSetChannel.receiveAsFlow()

    fun setLabelSet(labelSet: Set<String>) {
        viewModelScope.launch {
            _labelSetChannel.send(labelSet)
        }
    }
}