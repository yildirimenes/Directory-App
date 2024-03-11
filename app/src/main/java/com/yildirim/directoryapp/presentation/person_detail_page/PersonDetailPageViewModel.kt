package com.yildirim.directoryapp.presentation.person_detail_page

import androidx.lifecycle.ViewModel
import com.yildirim.directoryapp.repo.PersonsDaoRepository

class PersonDetailPageViewModel : ViewModel() {
    var vrepo = PersonsDaoRepository()

    fun update(person_id:Int,person_name:String,person_telephone: String) {
        vrepo.personUpdate(person_id,person_name,person_telephone)
    }
}