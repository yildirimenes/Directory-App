package com.yildirim.directoryapp.retrofit

class ApiUtils {
    companion object{
        private const val BASE_URL = "http://www.yildirimenes.com/"

        fun getPersonsDaoInterface():PersonsDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(PersonsDaoInterface::class.java)
        }
    }
}