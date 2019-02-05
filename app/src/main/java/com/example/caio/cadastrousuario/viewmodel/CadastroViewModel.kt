package com.example.caio.cadastrousuario.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CadastroViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun cadastrarUsuario(){

        loading.postValue(true)

        //Efetuar o cadastro em si
        doAsync {

            SystemClock.sleep(2000)

            uiThread {

                cadastrarUsuarioSucesso()

            }

        }


    }

    fun cadastrarUsuarioSucesso(){

        loading.postValue(false)

    }

}