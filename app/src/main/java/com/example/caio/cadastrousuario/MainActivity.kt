package com.example.caio.cadastrousuario

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

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
                txtUsuario.error = "O usuário deve conter no mínimo 3 caracteres"

            if (!email.contains("@"))
                txtEmail.error = "Email inválido! O email deve conter um @"

            if (senha.length < 4 || !senha.contains(Regex("[0-9]")) || validarSeq)
                txtSenha.error = "A senha deve conter no mínimo 4 caracteres, não ser uma sequência númerica e conter ao menos um número"

        }

    }
}
