package net.azarquiel.appforopaco.api

/**
 * Created by Alejandro on 19/02/2018.
 */
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rx.Observable
import com.google.gson.JsonObject
import net.azarquiel.appforopaco.model.*


interface ForoApi {


    @GET("users")
    fun getUsers(): Observable<Respuesta>

    @GET("user/{tlf}")
    fun getUser(@Path ("tlf")tlf:String):Observable<Respuesta>

    @GET("temas")
    fun getTemas():Observable<Respuesta>

    @GET("tema/{tema}/comentarios")
    fun getComentarios(@Path("tema")idTema: String):Observable<Respuesta>

    @POST("user")
    fun addUser(@Body user: User):Observable<String>

    @POST("tema")
    fun addTema(@Body tema:Tema):Observable<String>

    @POST("tema/{id}/comentario")
    fun addComentario(
            @Path ("id") idTema: String ,
            @Body  comentario: Comentario

    ):Observable<String>



    companion object {
        fun create(): ForoApi {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://www.iesazarquiel.es/paco/foroslim/")
                    .build()
            return retrofit.create(ForoApi::class.java)
        }
    }


}