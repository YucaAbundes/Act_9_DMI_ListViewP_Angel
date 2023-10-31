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

public class DetalleTrianguloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_triangulo);

        // Obtén la información del intent
        String figuraSeleccionada = getIntent().getStringExtra("figuraSeleccionada");

        // Referencias a las vistas
        ImageView imageViewFigura = findViewById(R.id.imageViewFigura);
        EditText editTextBase = findViewById(R.id.editTextBase);
        EditText editTextAltura = findViewById(R.id.editTextAltura);
        TextView textViewPerimetro = findViewById(R.id.textViewPerimetro);
        TextView textViewArea = findViewById(R.id.textViewArea);

        // Establece la imagen según la figura seleccionada
        if (figuraSeleccionada.equals("TRIANGULO ESCALENO")) {
            imageViewFigura.setImageResource(R.drawable.triangulo);
        }

        Button btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Oculta el teclado
                ocultarTeclado();

                // Verifica si los campos están vacíos
                if (editTextBase.getText().toString().isEmpty() || editTextAltura.getText().toString().isEmpty()) {
                    mostrarAlerta("Campos Vacíos", "Por favor, ingrese la base y altura del triángulo.");
                } else {
                    try {
                        // Intenta convertir el texto a números
                        double base = Double.parseDouble(editTextBase.getText().toString());
                        double altura = Double.parseDouble(editTextAltura.getText().toString());

                        // Calcula el perímetro y el área del triángulo
                        double perimetro = base + altura + obtenerLadoFaltante(base, altura);
                        double area = (base * altura) / 2;

                        // Muestra los resultados
                        textViewPerimetro.setText("Perímetro: " + perimetro);
                        textViewArea.setText("Área: " + area);
                    } catch (NumberFormatException e) {
                        // Maneja la excepción si no se pueden convertir a números
                        mostrarAlerta("Error", "Por favor, ingrese números válidos para la base y altura.");
                    }
                }
            }
        });

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementa la lógica para regresar a la página principal
                Intent intent = new Intent(DetalleTrianguloActivity.this, MainActivity.class);
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

    // Método para obtener el lado faltante en un triángulo escaleno
    private double obtenerLadoFaltante(double base, double altura) {
        // Implementa la lógica para calcular el lado faltante en un triángulo escaleno
        // Puedes personalizar esto según tus necesidades
        return Math.sqrt(base * base + altura * altura);
    }
}
