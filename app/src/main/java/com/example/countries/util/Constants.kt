package com.example.countries.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.squareup.picasso.Picasso


// url for api.
const val END_POINT = "DevTides/countries/master/countriesV2.json"
const val BASE_URL = "https://raw.githubusercontent.com/"


// make progress loading for image.
fun getProgressDrawable( context : Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth =10f
        centerRadius =50f
        start()
    }
}
// function show image using picasso.
fun ImageView.loadImage( uri : String? , progressDrawable: CircularProgressDrawable){
    Picasso.get().load(uri).fit().centerInside().placeholder(progressDrawable).into(this)
}