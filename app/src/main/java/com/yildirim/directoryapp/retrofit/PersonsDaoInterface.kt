package com.yildirim.directoryapp.retrofit

import com.yildirim.directoryapp.entity.CRUDResponse
import com.yildirim.directoryapp.entity.PersonsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface PersonsDaoInterface {
    @GET("persons/all_persons.php")
    fun allPersons(): Call<PersonsResponse>

    @POST("persons/all_persons_search.php")
    @FormUrlEncoded
    fun searchPerson(@Field("person_name") person_name: String): Call<PersonsResponse>

    @POST("persons/delete_persons.php")
    @FormUrlEncoded
    fun deletePerson(@Field("person_id") person_id: Int): Call<CRUDResponse>

    @POST("persons/insert_persons.php")
    @FormUrlEncoded
    fun addPerson(
        @Field("person_name") person_name: String,
        @Field("person_telephone") person_telephone: String
    ): Call<CRUDResponse>

    @POST("persons/update_persons.php")
    @FormUrlEncoded
    fun updatePerson(
        @Field("person_id") person_id: Int,
        @Field("person_name") kisi_ad: String,
        @Field("person_telephone") person_telephone: String
    ): Call<CRUDResponse>

}
