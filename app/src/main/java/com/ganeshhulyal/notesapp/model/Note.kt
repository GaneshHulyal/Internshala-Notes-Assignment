package com.ganeshhulyal.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") var id: Int?,
    @ColumnInfo(name="email") var email : String?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "createTimeStamp") var createTimeStamp: Long?,
){
    constructor():this(-1,"","","",-1)

}