package com.example.loginmvp.domain.interactor.logininteractor

import com.google.firebase.auth.FirebaseAuth


class SignInInteractorImpl:SignInInteractor {


    override fun signInWithEmailAndPassword(email: String, password: String,
                                            listener: SignInInteractor.SigninCallback) {

        //el parametro .addOnCompleteListener es el callback, asi dice la documentacion
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {

            //si el tag termino , se logue el usuario y si no pues muestra el else
            if (it.isSuccessful){
                listener.onSignInSuccess()
            }else{
                listener.onSingInFailure(it.exception?.message!!)
            }
        }
    }
}
/*
el interactor es los casos de usos,se crean en la capa de dominio es aqui donde
me doy cuenta que ya debo aplicar la clean arquitectura , porque ya cumpli el fin de
unir vista-presenter, ya el model lo mando a la clean arquit.(Antonio Leiva).
creo logininteractor porque cumpli el fin o caso de uso de Sing In que tiene
cualquier loguin. es la logica que va a manejar el loguin para poder ingresar al
sistema.
SignInInteractorImpl es decir con la interfaz , la logica de Autenticacion de contraseña
que esta en documentacion Firebase:
paso 1. importar la dependencia en el gradel
paso 2. crear cuenta contraseña con auth , pero uso FirebaseAuth obtiene las variables e implementa .signWith....
guia cual es el codigo para hacer un SingIn o un SignUp



 */