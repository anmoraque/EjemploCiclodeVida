package com.anmoraque.ejemplociclodevida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
/*
En esta actividad hemos hablado de:
Ciclos de vida de una aplicacion
Funcion onCreate y como guardar valores mediante
onSaveInstanceState y onRestoreInstanceState
Como funcionan onStop(), onResume() y onPause()

 */
class MainActivity : AppCompatActivity() {
    //Creamos una variable nombre
    var nombre = "Alberto"
    //Creamos un valor con una clave para identificar el valor a guardar
    val NOMBRE = "nombre"
    //Todo empieza en la funcion onCreate
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Inicializamos el boton
        val boton = findViewById<Button>(R.id.boton)
        //Creamos un toast con el nombre
        //Toast.makeText(this, nombre, Toast.LENGTH_LONG).show()
        //Comento el Toast para usarlo en el metodo onRestoreInstanceState
        //Listener al boton
        boton.setOnClickListener {
            //Cambiamos el nombre de la variable
            nombre = "Sergio"
            //Creamos de nuevo el toast
            Toast.makeText(this, nombre, Toast.LENGTH_LONG).show()
        }
        /*
        Si probamos cambia el nombre perfecto pero si giramos el telefono
        Android detecta que es un cambio de estado y destruye la actividad
        Es decir onCreate se destruye y se vuelve a recargar
        Esto se puede resolver usando dos metodos a continuacion
         */
    }
    //En esta funcion se llama el valor de las variables guardado
    //Esta funcion Android la llama cuando va a reiniciar la actividad (onCreate)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //Mi variable será igual al savedInstanceState con la clave NOMBRE
        nombre = savedInstanceState.getString(NOMBRE)!!
    }
    //En esta funcion se guarda el valor de las variables
    //Esta funcion Android la llama cuando va a destruir la actividad
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Guardo en un paquete (Bundle) Un String Clave, Valor
        outState?.putString(NOMBRE, nombre)
        //Creamos de nuevo el toast para probar
        Toast.makeText(this, nombre, Toast.LENGTH_LONG).show()
    }


    /*
    Estas tres funciones son tres estados que actuan de forma diferente
    nos permiten manipular las transciciones del uso de la aplicacion
    para tener mayor control pausar, detener y administrar al regreso
     acciones, servicios y variables para no perder datos de la aplicacion
    */
    //Aqui pausamos lo que estamos haciendo por ej. si reproduzaco
    //una cancion la pauso hasta que vuelva a la actividad
    override fun onPause() {
        super.onPause()
        //Creamos un Toast para ver cuando se usa
        Toast.makeText(this, "En trancisión", Toast.LENGTH_LONG).show()
    }
    //Aqui paramos la actividad por ej. si reproduzco una cancion la paro
    // para que no siga descargandose y gastando recursos
    //Ocultamos la actividad
    override fun onStop() {
        super.onStop()
        //Creamos un Toast para ver cuando se usa
        Toast.makeText(this, "Aplicativo oculto", Toast.LENGTH_LONG).show()
    }
    //Aqui lo usamos cuando volvemos a la actividad por ej. volvemos
    // a reproducir la cancion por donde la dejamos
    //Mostramos la actividad
    override fun onResume() {
        super.onResume()
        //Creamos un Toast para ver cuando se usa
        Toast.makeText(this, "Aplicativo visible", Toast.LENGTH_LONG).show()
    }

    //Esta funcion se usa cada vez que Android tiene que destruir
    //la aplicacion por alguna circunstancia por ej. mandamos la app a onStop
    //o esta en reposo y Android tiene que liberar memoria y la destruye
    //Para poder apreciar el Toast giramos el terminal porque al cambiar la
    // orientacion Android destruye y reinicia la actividad
    override fun onDestroy() {
        super.onDestroy()
        //Creamos un Toast para ver cuando se usa
        Toast.makeText(this, "Aplicacion destruida", Toast.LENGTH_LONG).show()
    }

}