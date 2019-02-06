package com.example.caio.cadastrousuario.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import com.example.caio.cadastrousuario.model.Usuario
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CadastroViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    val error = MutableLiveData<Boolean>()



    fun cadastrarUsuario(user:Usuario){

        loading.postValue(true)
        error.postValue(false)

        //Efetuar o cadastro em si
        doAsync {

            SystemClock.sleep(2000)

            uiThread {

                //cadastrarUsuarioSucesso()

                error.postValue(true)

            }

        }


    }

    fun cadastrarUsuarioSucesso(){

        loading.postValue(false)

    }

}