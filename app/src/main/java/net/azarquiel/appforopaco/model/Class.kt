package net.azarquiel.appforopaco.model

import org.json.JSONObject
import java.io.Serializable
import java.util.*

/**
 * Created by Alejandro on 19/02/2018.
 */
//Usuarios
//data class Users(val users:List<User>)
data class User(var telefono:String="",var nick:String=""):JSONObject()

//Temas
//data class Temas(val temas:List<Tema>)
data class Tema(val _id:String,val descripcion:String):Serializable

//Comentarios
//data class Comentarios(val comentarios: List<Comentario>)
data class Comentario(var telefono: String="", var _id: String="", var fecha:String="", var post:String="", var nick:String=""):Serializable

//Respuesta
data class Respuesta(val user:User, val tema:Tema, val comentarios: List<Comentario>, val temas: List<Tema>, val users: List<User>)