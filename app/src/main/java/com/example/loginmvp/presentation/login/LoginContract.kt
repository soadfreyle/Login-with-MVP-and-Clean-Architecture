package com.example.loginmvp.presentation.login
//las interfaces tienen metodos
//esta intefaz son metodo que tienen impacto en la vista
//esta interfaz la implemento en el Main Activity
// me pide imolementar estos metodos
interface LoginContract {
    interface LoginView {
        fun showError(msgError: String?)
        fun showProgressBar()
        fun hideProgressBar()
        //oculto el progress dialogo
        fun signIn()
        //cuando nos registramos
        fun navigateToMain()
        fun navigateToRegister()
        fun navigateToPasswordRecover()
    }
    //crear estos metodos describe la accion que se va hacer
    //ingreso dato1, dato2, oprimo el boton

    interface LoginPresenter {
        fun attachView(view: LoginView)
        fun dettachView()
        fun dettachJob()
        fun isViewAttached(): Boolean
        fun signInUserWithEmailAndPassword(email: String, password: String)
        fun checkEmptyFields(email: String, password: String): Boolean
    }
}