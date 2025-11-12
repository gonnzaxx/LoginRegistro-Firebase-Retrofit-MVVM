package com.example.mvvm.data.models

import com.google.gson.annotations.SerializedName

data class StarWarsResponse (@SerializedName("results") var results : ArrayList<Jedai>){

}