package net.azarquiel.appforopaco.handlers

import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import net.azarquiel.appforopaco.api.ForoApi
import net.azarquiel.appforopaco.model.User
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Alejandro on 19/02/2018.
 */

class GestorUsers private constructor() : Gestor() {


    companion object {
        val gestorUsers = GestorUsers()
        var loggedUser:User = User()
    }

    fun getUsers(): List<User> {

        var users:List<User> = ArrayList()

        foroApi.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        { body ->

                            users = body.users

                            synchronized(monitor){
                                monitor.notifyAll()
                            }



                        },
                        { error ->
                            Log.d("Gestor",error.message)
                            synchronized(monitor){
                                monitor.notifyAll()
                            }
                        }


                )

        synchronized(monitor){
            monitor.wait()
        }



    return users


    }

     fun getUser(tlf: String):User {

         var user = User()

         foroApi.getUser(tlf)
                 .subscribeOn(Schedulers.io())
                 .observeOn(Schedulers.newThread())
                 .subscribe(
                         { body ->

                             user = body.user
                             Log.d("getUser",body.user.toString())

                             synchronized(monitor){
                                 monitor.notifyAll()
                             }



                         },
                         { error ->
                             Log.d("Gestor",error.message)
                             synchronized(monitor){
                                 monitor.notifyAll()
                             }
                         }


                 )

         synchronized(monitor){
             monitor.wait()
         }



         return user


     }



     fun postUser(user: User) {

                foroApi.addUser(user).subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.newThread())
                        .subscribe(
                                { page ->



                                },
                                { error ->
                                    Log.d("ERROR",error.message)
                                }
                        )








    }



}
