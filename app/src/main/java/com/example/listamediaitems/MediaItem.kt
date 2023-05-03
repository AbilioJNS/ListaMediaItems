package com.example.listamediaitems

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class MediaItem(
    val id: Int,
    val titulo: String,
    val thumb: String,
    val type: Type,
    val descipcion:String
){
    enum class Type {PHOTO,VIDEO}
    fun toJson():String {
        return Json.encodeToString(this)
    }
}
fun getMediaItem() = (1..10).map {
    MediaItem(
        id = it,
        titulo = "Titulo de ${it}",
        thumb = "https://loremflickr.com/400/400/dog?random=${it}",
        type = if(it % 3 == 0) MediaItem.Type.PHOTO else MediaItem.Type.VIDEO,
        descipcion = "Esta es la descripción del item ${it}"
    )
}

