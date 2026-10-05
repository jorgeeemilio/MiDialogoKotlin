package es.studium.midialogokotlin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnMostrarDialogo: Button
    private lateinit var imgEstacion: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnMostrarDialogo = findViewById(R.id.btnMostrarDialogo)
        imgEstacion = findViewById(R.id.imgEstacion)

        btnMostrarDialogo.setOnClickListener {
            mostrarDialogoEstacion()
        }
    }

    private fun mostrarDialogoEstacion() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_estacion, null)
        val spinnerEstaciones = dialogView.findViewById<Spinner>(R.id.spinnerEstaciones)
        val btnElegir = dialogView.findViewById<Button>(R.id.btnElegir)
        val btnCancelar = dialogView.findViewById<Button>(R.id.btnCancelar)

        // Configurar el adapter del spinner con el array de estaciones en res/values/strings.xml
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.estaciones_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstaciones.adapter = adapter

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)

        val dialog = dialogBuilder.create()

        btnElegir.setOnClickListener {
            when (spinnerEstaciones.selectedItemPosition) {
                1 -> imgEstacion.setImageResource(R.drawable.ic_primavera)
                2 -> imgEstacion.setImageResource(R.drawable.ic_verano)
                3 -> imgEstacion.setImageResource(R.drawable.ic_otono)
                4 -> imgEstacion.setImageResource(R.drawable.ic_invierno)
            }
            dialog.dismiss()
        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}