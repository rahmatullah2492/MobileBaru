package com.example.kelompokbaru.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //penanda jika masuk ke menu favorite akan tampil tulisan berikut
        value = "Halaman ini merupakan favorite"
    }
    val text: LiveData<String> = _text
}