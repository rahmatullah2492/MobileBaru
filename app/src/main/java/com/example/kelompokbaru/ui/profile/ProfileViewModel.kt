package com.example.kelompokbaru.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //penanda jika masuk ke menu profil akan tampil tulisan berikut
        value = "Halaman ini merupakan Profile User "
    }
    val text: LiveData<String> = _text
}