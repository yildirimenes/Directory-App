package com.yildirim.directoryapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonsResponse(
    @SerializedName("persons")
    @Expose
    var persons:List<Persons>,
    @SerializedName("success")
    @Expose
    var success:Int)