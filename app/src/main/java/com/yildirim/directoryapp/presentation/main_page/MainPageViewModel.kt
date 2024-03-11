package com.yildirim.directoryapp.presentation.main_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yildirim.directoryapp.entity.Persons
import com.yildirim.directoryapp.repo.PersonsDaoRepository

class MainPageViewModel : ViewModel() {
    val vrepo = PersonsDaoRepository()
    var personList = MutableLiveData<List<Persons>>()

    init {
        loadPersons()
        personList = vrepo.getPersons()
    }

    fun loadPersons(){
        vrepo.getAllPersons()
    }

    fun search(searchWord:String){
        vrepo.searchPerson(searchWord)
    }

    fun delete(person_id:Int){
        vrepo.deletePerson(person_id)
    }
}