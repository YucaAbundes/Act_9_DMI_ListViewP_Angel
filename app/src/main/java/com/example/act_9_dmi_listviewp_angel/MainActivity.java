package com.example.act_9_dmi_listviewp_angel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] nombresFiguras = {"HEXAGONO", "TRIANGULO ESCALENO", "CILINDRO", "CIRCULO"};

    int[] listaImagenes = {R.drawable.hexagono, R.drawable.triangulo, R.drawable.cilindro, R.drawable.circulo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.lvPersonalizado);

        BaseAdapterPersonalizado adapterPersonalizado = new BaseAdapterPersonalizado(getApplicationContext(), nombresFiguras, listaImagenes);

        listView.setAdapter(adapterPersonalizado);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String figuraSeleccionada = nombresFiguras[position];

                Intent intent = null;

                try {
                    switch (figuraSeleccionada) {
                        case "HEXAGONO":
                            intent = new Intent(MainActivity.this, DetalleHexagonoActivity.class);
                            break;
                        case "TRIANGULO ESCALENO":
                            intent = new Intent(MainActivity.this, DetalleTrianguloActivity.class);
                            break;
                        case "CILINDRO":
                            intent = new Intent(MainActivity.this, DetalleCilindroActivity.class);
                            break;
                        case "CIRCULO":
                            intent = new Intent(MainActivity.this, DetalleCirculoActivity.class);
                            break;
                        default:
                            break;
                    }

                    if (intent != null) {
                        intent.putExtra("figuraSeleccionada", figuraSeleccionada);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "No se encontró la actividad de detalle para " + figuraSeleccionada, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
