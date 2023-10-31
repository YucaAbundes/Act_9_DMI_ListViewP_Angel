package com.example.act_9_dmi_listviewp_angel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseAdapterPersonalizado extends BaseAdapter {

    private Context context;
    private String[] nombresFiguras;
    private int[] listaImagenes;

    public BaseAdapterPersonalizado(Context context, String[] nombresFiguras, int[] listaImagenes) {
        this.context = context;
        this.nombresFiguras = nombresFiguras;
        this.listaImagenes = listaImagenes;
    }

    @Override
    public int getCount() {
        return nombresFiguras.length;
    }

    @Override
    public Object getItem(int position) {
        return nombresFiguras[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_lista_personalizado, null);

            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.textViewNombre = convertView.findViewById(R.id.textViewNombre);
            holder.layoutPerimetroArea = convertView.findViewById(R.id.layoutPerimetroArea);
            holder.textViewPerimetro = convertView.findViewById(R.id.textViewPerimetro);
            holder.textViewArea = convertView.findViewById(R.id.textViewArea);
            holder.textViewVolumenCilindro = convertView.findViewById(R.id.textViewVolumenCilindro);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageResource(listaImagenes[position]);
        holder.textViewNombre.setText(nombresFiguras[position]);

        // Aquí establece las fórmulas del perímetro, área y volumen según la figura correspondiente
        String formulaPerimetro = obtenerFormulaPerimetro(nombresFiguras[position]);
        String formulaArea = obtenerFormulaArea(nombresFiguras[position]);
        String formulaVolumenCilindro = obtenerFormulaVolumenCilindro();

        holder.textViewPerimetro.setText("Perímetro: " + formulaPerimetro);
        holder.textViewArea.setText("Área: " + formulaArea);

        // Solo establece la fórmula del volumen del cilindro si es la figura "CILINDRO"
        if (nombresFiguras[position].equals("CILINDRO")) {
            holder.layoutPerimetroArea.setVisibility(View.GONE);
            holder.textViewVolumenCilindro.setVisibility(View.VISIBLE);
            holder.textViewVolumenCilindro.setText("" + nombresFiguras[position] + "\nVolumen: " + formulaVolumenCilindro);
        } else {
            // En caso contrario, oculta la vista del volumen
            holder.layoutPerimetroArea.setVisibility(View.VISIBLE);
            holder.textViewVolumenCilindro.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textViewNombre;
        TextView textViewPerimetro;
        TextView textViewArea;
        TextView textViewVolumenCilindro; // Agrega esta línea
        LinearLayout layoutPerimetroArea; // Agrega esta línea también
    }

    // Método para obtener la fórmula del perímetro según la figura
    private String obtenerFormulaPerimetro(String figura) {
        switch (figura) {
            case "HEXAGONO":
                return "6 * lado";
            case "TRIANGULO ESCALENO":
                return "lado1 + lado2 + lado3";
            case "CIRCULO":
                return "2 * π * radio";
            default:
                return "";
        }
    }

    // Método para obtener la fórmula del área según la figura
    private String obtenerFormulaArea(String figura) {
        switch (figura) {
            case "HEXAGONO":
                return "(3 * √3 * lado^2) / 2";
            case "TRIANGULO ESCALENO":
                return "(base * altura) / 2";
            case "CIRCULO":
                return "π * radio^2";
            default:
                return "";
        }
    }

    // Método para obtener la fórmula del volumen del cilindro
    private String obtenerFormulaVolumenCilindro() {
        return "π * radio^2 * altura";
    }
}

