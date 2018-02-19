package net.azarquiel.appforopaco.handlers

import net.azarquiel.appforopaco.api.ForoApi

/**
 * Created by Alejandro on 19/02/2018.
 */
abstract class Gestor {

    val monitor:Object   = Object()

     val foroApi by lazy {
        ForoApi.create()
    }




}