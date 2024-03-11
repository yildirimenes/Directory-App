package com.yildirim.directoryapp.presentation.person_register_page

import androidx.lifecycle.ViewModel
import com.yildirim.directoryapp.repo.PersonsDaoRepository

class PersonRegisterPageViewModel : ViewModel() {
    var vrepo = PersonsDaoRepository()

    fun register(person_name:String,person_telephone:String){
        vrepo.registerPerson(person_name,person_telephone)
    }
}