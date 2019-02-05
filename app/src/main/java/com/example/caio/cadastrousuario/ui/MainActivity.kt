package com.example.caio.cadastrousuario.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.caio.cadastrousuario.R
import com.example.caio.cadastrousuario.viewmodel.CadastroViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    companion object {

        val ERRO_USUARIO = "O usuário deve conter no mínimo 3 caracteres"
        val ERRO_EMAIL = "Email inválido! O email deve conter um @"
        val ERRO_SENHA = "A senha deve conter no mínimo 4 caracteres, não ser uma sequência númerica e conter ao menos um número"

    }

    val viewModel by lazy {

        ViewModelProviders.of(this).get(CadastroViewModel::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCadastro.setOnClickListener {

            val usuario = txtUsuario.text.toString()
            val email = txtEmail.text.toString()
            val senha = txtSenha.text.toString()

            val p = Pattern.compile("[0-9]+")
            val m = p.matcher(senha)

            val nroExtraidos = StringBuilder()
            while (m.find()) {

                nroExtraidos.append(m.group())

            }

            var validarSeq = false

            if (nroExtraidos.length > 0){

                val segundoNum = nroExtraidos.first()+1
                val terceiroNum = segundoNum+1

                if (nroExtraidos.length == 1 || nroExtraidos.length == 2)
                    validarSeq = false
                else
                    if (nroExtraidos.substring(1, 2).equals(segundoNum.toString()) && nroExtraidos.substring(2, 3).equals(terceiroNum.toString()))
                        validarSeq = true

            }

            if (usuario.length < 3)
                txtUsuario.error = ERRO_USUARIO

            if (!email.contains("@"))
                txtEmail.error = ERRO_EMAIL

            if (senha.length < 4 || !senha.contains(Regex("[0-9]")) || validarSeq)
                txtSenha.error = ERRO_SENHA


            viewModel.cadastrarUsuario()

        }

        viewModel.loading.observe(this, Observer {
            updateLoading(it)
        })

    }

    fun updateLoading(loading:Boolean?){

        loading?.let {

            if (loading){

                btnCadastro.visibility = View.GONE
                progressBar.visibility = View.VISIBLE

            }else{

                btnCadastro.visibility = View.VISIBLE
                progressBar.visibility = View.GONE

            }

        }

    }

}
