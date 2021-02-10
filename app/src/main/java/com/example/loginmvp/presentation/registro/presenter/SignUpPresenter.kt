package com.example.loginmvp.presentation.registro.presenter

import androidx.core.util.PatternsCompat
import com.example.loginmvp.domain.interactor.registerinteractor.SignUpInteractor
import com.example.loginmvp.presentation.registro.RegisterContract

class SignUpPresenter(signUpInteractor: SignUpInteractor): RegisterContract.RegisterPresenter {

    var view:RegisterContract.RegisterView? = null
    var signUpInteractor:SignUpInteractor? = null
    init {
        this.signUpInteractor = signUpInteractor
    }

    override fun attachView(view: RegisterContract.RegisterView) {
        this.view = view
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun detachView() {
        view = null
    }

    override fun checkEmptyName(fullname: String): Boolean {
        return fullname.isEmpty()

    }



    override fun checkValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkEmptyPasswords(pw1: String, pws2: String): Boolean {
        return pw1.isEmpty() or pws2.isEmpty()
    }

    override fun checkPasswordsMatch(pw1: String, pws2: String): Boolean {
        return pw1 == pws2
    }

    override fun signUp(fullname: String, email: String, password: String) {
        view?.showProgress()
        signUpInteractor?.signUp(fullname,email,password,object: SignUpInteractor.RegisterCallback{
            override fun onRegisterSuccess() {
                view?.navigateToMain()
                view?.hideProgress()
            }

            override fun onRegisterFailure(errorMsg: String) {
                view?.showError(errorMsg)
                view?.hideProgress()
            }
        })

    }
}