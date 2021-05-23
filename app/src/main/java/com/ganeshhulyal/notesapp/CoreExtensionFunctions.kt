package com.ganeshhulyal.notesapp


import java.text.SimpleDateFormat
import java.util.*

fun Long?.getDateInDisplayFormFromLong():String {
    val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())
    val calender = Calendar.getInstance()
    return if(this!=null){
        calender.timeInMillis = this
        val date = Date()
        date.time = this
        try{
            simpleDateFormat.format(date)
        }catch (e : Exception){
            "N/A"
        }
    }else{
        "N/A"
    }
}


