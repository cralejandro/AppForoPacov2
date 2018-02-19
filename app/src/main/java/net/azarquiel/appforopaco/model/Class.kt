package net.azarquiel.appforopaco.model

import java.io.Serializable
import java.util.*

/**
 * Created by Alejandro on 19/02/2018.
 */
//Usuarios
data class Users(val users:List<User>)
data class User(val tlf:String,val nick:String):Serializable

//Temas
data class Temas(val temas:List<Tema>)
data class Tema(val _id:Int,val description:String):Serializable

//Comentarios
data class Comentarios(val comentarios: List<Comentario>)
data class Comentario(val tlf: String,val _id: Int,val date:Date,val post:String):Serializable

