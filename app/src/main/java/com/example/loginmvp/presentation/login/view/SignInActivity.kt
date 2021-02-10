package com.example.loginmvp.presentation.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.loginmvp.databinding.ActivityMainBinding
import com.example.loginmvp.domain.interactor.logininteractor.SignInInteractorImpl
import com.example.loginmvp.presentation.login.LoginContract
import com.example.loginmvp.presentation.login.presenter.SignInPresenter
import com.example.loginmvp.presentation.main.view.Main2Activity
import com.example.loginmvp.presentation.registro.view.SignUpActivity

class SignInActivity : AppCompatActivity(), LoginContract.LoginView  {
    //@Inject, dagger sabra q debe inyectar a esta vista, inyecta el SignInPresenter a esta vista
    lateinit var presenter: SignInPresenter

    private lateinit var binding: ActivityMainBinding
    /*La extencion o plugin de android  nos permite encontar
        * la vita por su ID, sin necesidad de creear la instancia
        * reconoce que la vista esta en esta actividad.
        * y si hay varias vista con el mismo nombre??*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = SignInPresenter(SignInInteractorImpl())
        presenter.attachView(this)
        //Dagger es para inyeccion dependencia y nos ahorraremos esto, pero no se manejar eso
        //instancio el presenter e inyecto el interactor, le estoy inyectando al SignInPresenter una dependencia de interactor
        //si no hago esto no estaria, no estaria inyectando la dependencia que necesita el SignInPresenter para hacer el login es decir ingresar al sistema, Paso1
        //principio de unica responsabilidad dice,esta clase debe ser solo para ñla vista y no debe preocuparse por la inyeccion de dependencia anterior
        //dagger abstrae mas las inyeccion dependencia con un modulo que esta atras q sabra q inyectarle a SignInActivity

        binding.btnSignIn.setOnClickListener {
            signIn()
        }
        binding.txtRegister.setOnClickListener {
            navigateToRegister()
        }
    }

    override fun showError(msgError: String?) {
        Toast.makeText(this,msgError,Toast.LENGTH_SHORT).show()
    }
    //cuando implemente la interfaz LoginContract.View
    //sobreescribo cada metodo
    //el msg de error que muestra es del proceso,el presenter es el encargado de buscar la data en el dominio
    //el dominio es el encargado de decirle al repositorio necesito crear un usuario lo crea y lo devuelve
    //y el dominio hace la logica de registrar el ususario,si hay un error el dominio le habisa al presenter
    //y le habisa a la vista que debe mostar.


    override fun showProgressBar() {
        binding.progressBarSignIn.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressBarSignIn.visibility = View.GONE
    }

    override fun signIn() {
        val email = binding.etxtEmail.text.toString().trim()
        val password = binding.etxtPassword.text.toString().trim()
        if (presenter.checkEmptyFields(email, password))
            Toast.makeText(this, "clave y correo incorrecto", Toast.LENGTH_LONG).show()
        else
            presenter.signInUserWithEmailAndPassword(email, password)

    }
    //MVP entar por la vista, va al presenter con esos datos 30:28

    override fun navigateToMain() {
        val intent = Intent(this,Main2Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }

    override fun navigateToRegister() {
        startActivity(Intent(this, SignUpActivity::class.java ))


    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dettachView()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
    }

    override fun navigateToPasswordRecover() {

    }
}