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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleHexagonoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_hexagono);

        // Obtén la información del intent
        String figuraSeleccionada = getIntent().getStringExtra("figuraSeleccionada");

        // Referencias a las vistas
        ImageView imageViewFigura = findViewById(R.id.imageViewFigura);
        EditText editTextLadoHexagono = findViewById(R.id.editTextLadoHexagono);
        TextView textViewPerimetro = findViewById(R.id.textViewPerimetro);
        TextView textViewArea = findViewById(R.id.textViewArea);

        // Establece la imagen según la figura seleccionada
        if (figuraSeleccionada.equals("HEXAGONO")) {
            imageViewFigura.setImageResource(R.drawable.hexagono);
        }

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oculta el teclado
                ocultarTeclado();

                // Verifica si los campos están vacíos
                if (editTextLadoHexagono.getText().toString().isEmpty()) {
                    mostrarAlerta("Campos Vacíos", "Por favor, ingrese el lado del hexágono.");
                } else {
                    try {
                        // Intenta convertir el texto a un número
                        double lado = Double.parseDouble(editTextLadoHexagono.getText().toString());

                        // Calcula el perímetro y el área del hexágono
                        double perimetro = 6 * lado;
                        double area = (3 * Math.sqrt(3) * Math.pow(lado, 2)) / 2;

                        // Muestra los resultados
                        textViewPerimetro.setText("Perímetro: " + perimetro);
                        textViewArea.setText("Área: " + area);
                    } catch (NumberFormatException e) {
                        // Maneja la excepción si no se puede convertir a número
                        mostrarAlerta("Error", "Por favor, ingrese un número válido para el lado.");
                    }
                }
            }
        });


        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementa la lógica para regresar a la página principal
                Intent intent = new Intent(DetalleHexagonoActivity.this, MainActivity.class);
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


