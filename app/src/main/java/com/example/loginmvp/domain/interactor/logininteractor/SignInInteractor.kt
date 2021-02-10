package com.example.loginmvp.domain.interactor.logininteractor

interface SignInInteractor {

    interface SigninCallback{
        fun onSignInSuccess()
        //este msm error lo ejecuta el loguin de firebase
        fun onSingInFailure(errorMsg:String)

    }

    //este metodo nos loguea
    fun signInWithEmailAndPassword(email:String,password:String,listener:SigninCallback)
}
/*
tiene un callback pide algo a una clase , la clase lo ejecuta en sierto tiempo asincrona
si yo logueo un usuario! cuanto va a tardar el servidor en responderme, para yo poder hacer una accion
despues que me respondas.no puedo ir a la pantalla principal ante q me diga q se pudo loguear, por q entonces romperiamos el esquema
minuto 16:00
una interfaz q escuche
quien avisa q esta interfaz termino es el mismo metodo de firebase q es el
onsucces, cuando pasa este onsucess el tag para loguear le habisamos a esta interfaz
q termino. el presenter llama al interactor se da cuenta por los metodos q debe implementar
q ya termino.FIREBASE LE HABISA A ESTA INTERFAZ Y ESTA AL PRESENTER ESTAMOS HACIENDO UN CALLBACK
Y NOS ESTAMOS SUSCRIBIENDO DESDE EL PRESENTE AL CALLBACK Q LE ESTAMOS CREANDO. USAR RXJAVA USA PATRON OBSERVER
DONDE EL PRESENTER VA OBTENER UN OBSERVER A UN OBSERVABLE Q VA TENER EL INTERACTOR
Q ESE OBSERVABLE VA A CAMBIAR CUANDO SE LOGUE UN USUARIO, EL PRESENTER SIEMPRE VA
ESTAR ESCUCHANDO ESO

 */