package com.example.e_commerce_dummy.DataOperations.Room.ss.DAO.Entity

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class Converter {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String>? {
        var arraySequence : Sequence<String>
        var arrayList = ArrayList<String>()
        if (value!!.contains(",")){
             arraySequence = value.trim().splitToSequence(",")
             for (i in arraySequence){
                arrayList.add(i)
             }
        }else {
            arrayList.add(value)
        }

        return arrayList
    }

    @TypeConverter
    fun fromArrayList(arrayList: ArrayList<String>?): String? {
        var str = arrayList!!.joinToString (",","","",10,"",null)
        return str
    }
}