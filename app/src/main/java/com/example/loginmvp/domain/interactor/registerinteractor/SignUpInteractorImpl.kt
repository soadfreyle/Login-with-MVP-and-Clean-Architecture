package com.example.loginmvp.domain.interactor.registerinteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class SignUpInteractorImpl: SignUpInteractor {
    override fun signUp(fullname: String, email: String, password: String,
        listener: SignUpInteractor.RegisterCallback) {


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener { itSignUp ->
            if(itSignUp.isSuccessful){

                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(fullname)
                    .build()

                FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener {
                    if(it.isSuccessful){
                        listener.onRegisterSuccess()
                    }
                }

            }else{
                listener.onRegisterFailure(itSignUp.exception?.message.toString())
            }
        }





    }
}