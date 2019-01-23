package com.example.caio.cadastrousuario

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCadastro.setOnClickListener {

            val usuario = txtUsuario.text.toString()
            val email = txtEmail.text.toString()
            val senha = txtSenha.text.toString()

            if (usuario.length < 3)
                txtUsuario.error = "O usuário deve conter no mínimo 3 caracteres"

            if (!email.contains("@"))
                txtEmail.error = "Email inválido! O email deve conter um @"

            if (senha.length < 4 || !senha.contains(Regex("[0-9]"))){

                txtSenha.error = "A senha deve conter no mínimo 4 caracteres, não ser uma sequência númerica e conter ao menos um número"

            }

        }

    }
}
