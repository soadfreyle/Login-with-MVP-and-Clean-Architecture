package com.example.loginmvp.presentation.registro.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.loginmvp.R
import com.example.loginmvp.databinding.ActivityMainBinding
import com.example.loginmvp.databinding.ActivitySignUpBinding
import com.example.loginmvp.domain.interactor.logininteractor.SignInInteractorImpl
import com.example.loginmvp.domain.interactor.registerinteractor.SignUpInteractorImpl
import com.example.loginmvp.presentation.main.view.Main2Activity
import com.example.loginmvp.presentation.registro.RegisterContract
import com.example.loginmvp.presentation.registro.presenter.SignUpPresenter

class SignUpActivity : AppCompatActivity(),RegisterContract.RegisterView {
    lateinit var presenter:SignUpPresenter


    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SignUpPresenter(SignUpInteractorImpl())
        presenter.attachView(this)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            signUp()
        }
    }

    override fun navigateToMain() {
        val intent = Intent(this, Main2Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun signUp() {
        val fullname:String = binding.etxFullname.text.toString().trim()
        val email:String = binding.etxtEmailRegistro.text.toString().trim()
        val pw1:String = binding.etxPw1.text.toString().trim()
        val pw2:String = binding.etxPw2.text.toString().trim()

        if(presenter.checkEmptyName(fullname)){
            binding.etxFullname.error = "The name is empty."
            return
        }

        if(!presenter.checkValidEmail(email)){
            binding.etxtEmailRegistro.error = "The email is invalid"
            return
        }

        if(presenter.checkEmptyPasswords(pw1,pw2)){
            binding.etxPw1.error = "Empty field"
            binding.etxPw2.error = "Empty field"
            return
        }

        if(!presenter.checkPasswordsMatch(pw1,pw2)){
            binding.etxPw1.error = "Passwords dont match"
            binding.etxPw2.error = "Passwords dont match"
            return
        }

        presenter.signUp(fullname,email,pw1)

    }

    override fun showProgress() {
        binding.progressSignup.visibility = View.VISIBLE
        binding.btnSignup.visibility = View.GONE

    }

    override fun hideProgress() {
        binding.progressSignup.visibility = View.GONE
        binding.btnSignup.visibility = View.VISIBLE

    }

    override fun showError(errormsg: String) {
        Toast.makeText(this, "tienes un error", Toast.LENGTH_LONG).show()

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}