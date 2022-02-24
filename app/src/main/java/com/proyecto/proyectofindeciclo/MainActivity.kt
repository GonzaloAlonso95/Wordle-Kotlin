package com.proyecto.proyectofindeciclo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.proyecto.proyectofindeciclo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val array_palabras = arrayOf("REINO", "SEGAR", "VISTA", "ZORRO", "CALOR", "CESTA", "POSTE", "PISTA", "RESTA", "TRETA")
    private var n_fila = 0
    private lateinit var adivinar: String
    private var posicionLetra = 0
    private var letrasCorrectas = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adivinar = array_palabras[(0..9).random()]
        Toast.makeText(this, adivinar, Toast.LENGTH_SHORT).show()
        binding.comprobar.setOnClickListener {
            comprobarLetras()
        }
    }

    fun comprobarLetras() {

        if (!(binding.palabra.text.any { it in "0123456789!@#$%^&*()_+-=[]{};':/?.>,<|" })) {

            if (binding.palabra.text.toString().length == 5){

                posicionLetra = 0
                letrasCorrectas = 0

                val palabraComprobar = binding.palabra.text.toString().toUpperCase().toCharArray(0,5)
                val fila: TableRow = binding.tabla.getChildAt(n_fila) as TableRow

                for (i in 0 until fila.childCount){

                    when(pintarCeldas(palabraComprobar[i].toString())) {

                        0 -> {

                            fila.getChildAt(i).background = null
                            fila.getChildAt(i).setBackgroundColor(resources.getColor(R.color.gris))

                        }

                        1 -> {

                            fila.getChildAt(i).background = null
                            fila.getChildAt(i).setBackgroundColor(resources.getColor(R.color.amarillo))

                        }

                        2 -> {

                            fila.getChildAt(i).background = null
                            fila.getChildAt(i).setBackgroundColor(resources.getColor(R.color.verde))
                            letrasCorrectas++

                        }

                    }
                    val letra: TextView = fila.getChildAt(i) as TextView
                    letra.text = palabraComprobar[i].toString()
                }

                if (letrasCorrectas == 5) {

                    val dialogC = AlertDialog.Builder(this)
                        .setTitle("Has ganado")
                        .setMessage("¡Enhorabuena, has ganado! Puedes volver a jugar, hay otras palabras por adivinar")
                        .setPositiveButton("Aceptar") { view, _ ->
                            view.dismiss()
                            startActivity(Intent.makeRestartActivityTask(this.intent?.component))
                        }
                        .setCancelable(false)
                        .create()

                    dialogC.show()

                }

                n_fila++
                binding.palabra.text.clear()

                if (n_fila == 6) {

                    val dialogF = AlertDialog.Builder(this)
                        .setTitle("Has fallado")
                        .setMessage("La palabra era:$adivinar. No pasa nada, puedes volver a intentarlo otra vez.")
                        .setPositiveButton("Aceptar") { view, _ ->
                            view.dismiss()
                            startActivity(Intent.makeRestartActivityTask(this.intent?.component))
                        }
                        .setCancelable(false)
                        .create()

                    dialogF.show()

                }

            } else {

                Toast.makeText(this, "La palabra tiene que ser de 5 letras.", Toast.LENGTH_SHORT).show()
            }
        } else {

            Toast.makeText(this, "La palabra solo puede contener letras.", Toast.LENGTH_SHORT).show()

        }

    }

    fun pintarCeldas(letra: String): Int {

        var resultado = 0
        val adivinarP = adivinar.toUpperCase().toCharArray(0,5)

        for (i in adivinarP.indices) {

            if (letra == adivinarP[i].toString()) {

                resultado = 1

            }

        }

        if (letra == adivinarP[posicionLetra].toString()) {

            resultado = 2

        }

        posicionLetra++

        return resultado

    }
}