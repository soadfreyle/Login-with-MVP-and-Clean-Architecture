# Login-with-MVP-and-Clean-Architecture
Este es un Login con patrón de diseño MVP y Clean Architecture
1-Crear un proyecto en FireBase
2-Habilita Google Analytics
3-Selecciona tu cuenta, crear proyecto.
4-Ya creado el proyecto selecciona, android.
5-en la seccion Añadir Firebase a una aplicacion de Android, registra 
la aplicacion con el nombre que se encuentra en el archivo manifests
com.example.ciclodevidaactivity2
6-Coloca apodo al proyecto.
7-Registra la aplicacion.
8-Da click en siguiente por que ya se descargaron los archivos de configuracion en el 
Gradlle.
9.Ir a consola.
10-Explora autenticacion, activa proveedor de sesion de correo.
11-En User puedes crear un usuario.
12-conecta Firebase con Android Studio, Tool/Firebase/Autenticacion por SignIn.
13.Refresca Firebase y Prueba el Aplicativo.

MAS SOBRE EL APLICATIVO
En el desarrollo de este se utiliza nuevos procesos y actualizaciones de Android estudio,
por ejemplo utilice View Binding para acceder a las vista, se construyó en la versión SDK 30 
y para simplicidad en el desarrollo se usó Callback en vez de Coroutines que administra los 
procesos asíncronos del aplicativo, en la inyección de dependencia se hacen en View para no 
usar Dagger.
En el desarrollo de Android ya la activity main carga la vista con dependencias propias del
Frameword con AppCompany a esta no deberíamos sumarle más como la lógica del negocio, implementación 
de una pasarela de pago, conexión a base de datos y métodos de clases intermediaria que se llama en el
primer ciclo de la actividad de Android, es decir no cargar a la main activity más cosa que no sea las
vistas y métodos respetando el patrón de única responsabilidad no creamos métodos para decirle voy a 
caminar, voy a descansar y continuo por favor que sean separados.

