package br.com.senaijandira.cadastro

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.Button
import android.widget.EditText
import com.example.caio.cadastrousuario.MainActivity
import com.example.caio.cadastrousuario.R
import org.hamcrest.CoreMatchers.equalTo
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule =  ActivityTestRule(MainActivity::class.java)

    lateinit var activity: MainActivity

    @Before
    fun setUp(){
        activity = rule.activity
    }

    @Test
    fun a_activityCarregaHintCorretamente(){

        val usuario = activity.findViewById<EditText>(R.id.txtUsuario)
        val email = activity.findViewById<EditText>(R.id.txtEmail)
        val senha = activity.findViewById<EditText>(R.id.txtSenha)

        Assert.assertThat(usuario.text.toString(), equalTo(""))
        Assert.assertThat(email.text.toString(), equalTo(""))
        Assert.assertThat(senha.text.toString(), equalTo(""))

    }

    @Test
    fun b_botaoCarregaCorretamente(){

        val botao = activity.findViewById<Button>(R.id.btnCadastro)

        Assert.assertThat(botao.text.toString(), equalTo("Cadastrar"))

    }

    @Test
    fun c_testeNomeComDoisCaracteres(){

        // Acessar o componente da tela com determinado hint
        val usuario = onView(withId(R.id.txtUsuario))

        // Escrever o texto na caixinha
        usuario.perform(typeText("al"))

        // Clica no botao com texto 'Cadastrar'
        onView(withText("Cadastrar")).perform(click())

        val textError = "O usuário deve conter no mínimo 3 caracteres"

        usuario.check(matches(hasErrorText(textError)))

    }

    @Test
    fun d_testeEmailSemArroba(){

        val email = onView(withId(R.id.txtEmail))

        email.perform(typeText("caio.costacarmo.com"))

        onView(withText("Cadastrar")).perform(click())

        val textError = "Email inválido! O email deve conter um @"

        email.check(matches(hasErrorText(textError)))

    }

    @Test
    fun e_testeSenhaTresCaracteres(){


        val senha = onView(withId(R.id.txtSenha))

        senha.perform(typeText("ca1"))

        onView(withText("Cadastrar")).perform(click())

        val textError =  "A senha deve conter no mínimo 4 caracteres, não ser uma sequência númerica e conter ao menos um número"

        senha.check(matches(hasErrorText(textError)))

    }

    @Test
    fun f_testeSenhaSemNumero(){

        val senha = onView(withId(R.id.txtSenha))

        senha.perform(typeText("caio"))

        onView(withText("Cadastrar")).perform(click())

        val textError =  "A senha deve conter no mínimo 4 caracteres, não ser uma sequência númerica e conter ao menos um número"

        senha.check(matches(hasErrorText(textError)))

    }

    @Test
    fun g_testeSenhaSequenciaNumerica(){

        val senha = onView(withId(R.id.txtSenha))

        senha.perform(typeText("1234"))

        onView(withText("Cadastrar")).perform(click())

        val textError =  "A senha deve conter no mínimo 4 caracteres, não ser uma sequência númerica e conter ao menos um número"

        senha.check(matches(hasErrorText(textError)))

    }

}