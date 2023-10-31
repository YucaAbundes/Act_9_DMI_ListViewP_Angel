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

public class DetalleCirculoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_circulo);

        // Obtén la información del intent
        String figuraSeleccionada = getIntent().getStringExtra("figuraSeleccionada");

        // Referencias a las vistas
        ImageView imageViewFigura = findViewById(R.id.imageViewFigura);
        EditText editTextRadio = findViewById(R.id.editTextRadio);
        TextView textViewPerimetro = findViewById(R.id.textViewPerimetro);
        TextView textViewArea = findViewById(R.id.textViewArea);

        // Establece la imagen según la figura seleccionada
        if (figuraSeleccionada.equals("CIRCULO")) {
            imageViewFigura.setImageResource(R.drawable.circulo);
        }

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oculta el teclado
                ocultarTeclado();

                // Verifica si los campos están vacíos
                if (editTextRadio.getText().toString().isEmpty()) {
                    mostrarAlerta("Campo Vacío", "Por favor, ingrese el radio del círculo.");
                } else {
                    try {
                        // Intenta convertir el texto a un número
                        double radio = Double.parseDouble(editTextRadio.getText().toString());

                        // Calcula el perímetro y el área del círculo
                        double perimetro = 2 * Math.PI * radio;
                        double area = Math.PI * Math.pow(radio, 2);

                        // Muestra los resultados
                        textViewPerimetro.setText("Perímetro: " + perimetro);
                        textViewArea.setText("Área: " + area);
                    } catch (NumberFormatException e) {
                        // Maneja la excepción si no se puede convertir a número
                        mostrarAlerta("Error", "Por favor, ingrese un número válido para el radio.");
                    }
                }
            }
        });

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementa la lógica para regresar a la página principal
                Intent intent = new Intent(DetalleCirculoActivity.this, MainActivity.class);
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
