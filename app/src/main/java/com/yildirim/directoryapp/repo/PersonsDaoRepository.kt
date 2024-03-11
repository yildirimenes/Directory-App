package com.yildirim.directoryapp.repo

import androidx.lifecycle.MutableLiveData
import com.yildirim.directoryapp.entity.CRUDResponse
import com.yildirim.directoryapp.entity.Persons
import com.yildirim.directoryapp.entity.PersonsResponse
import com.yildirim.directoryapp.retrofit.ApiUtils
import com.yildirim.directoryapp.retrofit.PersonsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonsDaoRepository {
    var personsList = MutableLiveData<List<Persons>>()
    var personsDaoInterface: PersonsDaoInterface

    init {
        personsDaoInterface = ApiUtils.getPersonsDaoInterface()
        personsList = MutableLiveData()
    }

    fun getPersons(): MutableLiveData<List<Persons>> {
        return personsList
    }

    fun getAllPersons() {
        personsDaoInterface.allPersons().enqueue(object : Callback<PersonsResponse> {
            override fun onResponse(
                call: Call<PersonsResponse>?,
                response: Response<PersonsResponse>?
            ) {
                val list = response?.body()?.persons
                personsList.value = list!!
            }

            override fun onFailure(call: Call<PersonsResponse>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        })
    }

    fun searchPerson(searchWord: String) {
        personsDaoInterface.searchPerson(searchWord).enqueue(object : Callback<PersonsResponse> {
            override fun onResponse(
                call: Call<PersonsResponse>?,
                response: Response<PersonsResponse>?
            ) {
                val list = response?.body()?.persons
                personsList.value = list!!
            }

            override fun onFailure(call: Call<PersonsResponse>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        })
    }

    fun registerPerson(person_name: String, person_telephone: String) {
        personsDaoInterface.addPerson(person_name, person_telephone)
            .enqueue(object : Callback<CRUDResponse>{
                override fun onResponse(
                    call: Call<CRUDResponse>?,
                    response: Response<CRUDResponse>?
                ) {
                    response?.isSuccessful == true
                }

                override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun personUpdate(person_id:Int,person_name:String,person_telephone: String) {
        personsDaoInterface.updatePerson(person_id,person_name,person_telephone).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>?) {
                response?.isSuccessful == true
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        })
    }

    fun deletePerson(person_id:Int) {
        personsDaoInterface.deletePerson(person_id).enqueue(object : Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>?) {
                getAllPersons()
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        })
    }
}