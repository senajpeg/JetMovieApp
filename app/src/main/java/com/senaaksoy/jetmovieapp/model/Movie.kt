package com.senaaksoy.jetmovieapp.model

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

data class Movie (
    @StringRes val id : Int,
    @StringRes  val title:Int,
    @StringRes val year : Int,
    @StringRes val genre: Int,
    @StringRes val director: Int,
    @StringRes val actors: Int,
    @StringRes val plot: Int,
    @StringRes val poster: Int,
    @ArrayRes val images: Int,
    @StringRes val rating: Int
)