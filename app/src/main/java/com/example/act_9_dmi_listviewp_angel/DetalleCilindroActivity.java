package com.example.act_9_dmi_listviewp_angel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleCilindroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cilindro);

        // Obtén la información del intent
        String figuraSeleccionada = getIntent().getStringExtra("figuraSeleccionada");

        // Referencias a las vistas
        ImageView imageViewFigura = findViewById(R.id.imageViewFigura);
        EditText editTextRadio = findViewById(R.id.editTextRadio);
        EditText editTextAltura = findViewById(R.id.editTextAltura);
        TextView textViewVolumen = findViewById(R.id.textViewVolumen);

        // Establece la imagen según la figura seleccionada
        if (figuraSeleccionada.equals("CILINDRO")) {
            imageViewFigura.setImageResource(R.drawable.cilindro);
        }

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oculta el teclado
                ocultarTeclado();

                // Verifica si los campos están vacíos
                if (editTextRadio.getText().toString().isEmpty() || editTextAltura.getText().toString().isEmpty()) {
                    mostrarAlerta("Campos Vacíos", "Por favor, ingrese el radio y altura del cilindro.");
                } else {
                    try {
                        // Intenta convertir el texto a números
                        double radio = Double.parseDouble(editTextRadio.getText().toString());
                        double altura = Double.parseDouble(editTextAltura.getText().toString());

                        // Calcula el volumen del cilindro
                        double volumen = Math.PI * Math.pow(radio, 2) * altura;

                        // Muestra el resultado
                        textViewVolumen.setText("Volumen: " + volumen);
                    } catch (NumberFormatException e) {
                        // Maneja la excepción si no se puede convertir a números
                        mostrarAlerta("Error", "Por favor, ingrese números válidos para el radio y altura.");
                    }
                }
            }
        });

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementa la lógica para regresar a la página principal

                Intent intent = new Intent(DetalleCilindroActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Cierra la actividad actual si no quieres que permanezca en el historial
            }
        });
    }

    // Método para ocultar el teclado
    private void ocultarTeclado() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    // Método para mostrar una alerta
    private void mostrarAlerta(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
