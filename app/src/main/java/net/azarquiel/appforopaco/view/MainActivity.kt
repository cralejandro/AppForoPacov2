package net.azarquiel.appforopaco.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.appforopaco.R
import net.azarquiel.appforopaco.handlers.GestorUsers
import net.azarquiel.appforopaco.handlers.GestorUsers.Companion.gestorUsers
import net.azarquiel.appforopaco.handlers.GestorUsers.Companion.loggedUser
import net.azarquiel.appforopaco.model.User
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        comprobarUser()
        btnLogin.setOnClickListener{login()}
        btnRegister.setOnClickListener{alertRegister()}


            

    }

    private fun alertRegister() {


        alert {
            customView {
                verticalLayout {
                    val telefono = editText {
                        hint = "Telefono"
                    }
                    val nick = editText {
                        hint = "Nick"
                    }
                    positiveButton("Register") { register(telefono.text.toString(), nick.text.toString()) }

                }
            }
        }.show()




    }

    private fun register(telefono: String, nick: String) {

        if(!isDuplicate(telefono)){
            val user = User(telefono,nick)

            gestorUsers.postUser(user)
            saveUser(user)
            navigateToTemas(user)



        }else{
            toast("Ese telefono ya esta registrado")
        }


    }

    private fun isDuplicate(telefono: String): Boolean {

       return !gestorUsers.getUser(telefono).telefono.isBlank()




    }


    private fun comprobarUser() {
        val preferencias = getSharedPreferences("user", Context.MODE_PRIVATE)
        var user= User()
        preferencias.all.keys.forEach {
           user = gestorUsers.getUser(it)
        }
        if (!user.telefono.isBlank())   {
          navigateToTemas(user)
        }


    }

    private fun login(){

       val user = gestorUsers.getUser(etLogin.text.toString())
        if(!user.telefono.isBlank()){
            saveUser(user)
            navigateToTemas(user)

        }else{
            toast("El telefono introducido no esta registrado")
        }


    }

    private fun navigateToTemas(user:User){
        loggedUser = user
        val intent = Intent(this,TemasActivity::class.java)
        this.startActivity(intent)
        this.finish()
    }

    private fun saveUser(user:User) {
        val preferencias = getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit= preferencias.edit()
        edit.putString(user.telefono,user.nick)
        edit.apply()
    }

}
