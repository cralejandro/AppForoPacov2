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
    fun getUsers(): Observable<Users>

    @GET("user/{tlf}")
    fun getUser(@Path ("tlf")tlf:String)



    @GET("temas")
    fun getTemas():Observable<Temas>

    @GET("tema/{tema}/comentarios")
    fun getComentarios(@Path("tema")idTema: Int)

    @POST("user")
    fun addUser(@Body user: User)

    @POST("tema")
    fun addTema(@Body tema:Tema)

    @POST("tema/{id}/comentario")
    fun addComentario(
            @Path ("id") idTema: Int ,
            @Body  comentario: Comentario

    )



    companion object {
        fun create(): ForoApi {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("172.19.105.1/foroslim/")
                    .build()
            return retrofit.create(ForoApi::class.java)
        }
    }


}