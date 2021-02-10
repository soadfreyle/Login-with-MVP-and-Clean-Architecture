package com.example.loginmvp.presentation.login.presenter

import com.example.loginmvp.domain.interactor.logininteractor.SignInInteractor
import com.example.loginmvp.presentation.login.LoginContract


//cuando nos registramos
class SignInPresenter ( signInInteractor: SignInInteractor) : LoginContract.LoginPresenter{
    var view: LoginContract.LoginView? = null
    //25:31
    var signInInteractor: SignInInteractor? = null
    init {
        this.signInInteractor = signInInteractor
    }
    /* CoroutimeScope , si aprendo debo colocarla en la clase SignInPresenter
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
        */





    override fun attachView(view: LoginContract.LoginView) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun dettachJob() {
        TODO("Not yet implemented")
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }
    //hay que hacer una clase de test para saber si esta logica funciona
    //continua paso 1

    //MVP este metodo va a llamar el met del iteractor i por el callback del
    //iteractor implemento Los onSignI.....
    //despues pasa al presente q ya escuha esto el interactor y llega aca
    //y el presenter actualiza la vista

    override fun signInUserWithEmailAndPassword(email: String, password: String) {
        view?.showProgressBar()
        //27:25
        signInInteractor?.signInWithEmailAndPassword(email,password,object: SignInInteractor.SigninCallback{
            override fun onSignInSuccess() {
                if (isViewAttached()){
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }
            }

            override fun onSingInFailure(errorMsg: String) {
                if(isViewAttached()){
                    view?.hideProgressBar()
                    view?.showError(errorMsg)
                }
            }
        })

    }

    override fun checkEmptyFields(email: String, password: String): Boolean {
        return email.isEmpty() || password.isEmpty()
    }
}