package com.example.parcial_1_am_acn4bv_pieiro_linco.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial_1_am_acn4bv_pieiro_linco.R;

import java.util.List;

public class PartidaAdapter extends RecyclerView.Adapter<PartidaAdapter.PartidaViewHolder> {

    private List<Partida> listaPartidas;

    public PartidaAdapter(List<Partida> listaPartidas) {
        this.listaPartidas = listaPartidas;
    }

    @NonNull
    @Override
    public PartidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partida, parent, false);
        return new PartidaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidaViewHolder holder, int position) {
        Partida partida = listaPartidas.get(position);
        holder.bind(partida);
    }

    @Override
    public int getItemCount() {
        return listaPartidas.size();
    }

    public class PartidaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvApodo;
        private TextView tvFecha;
        private TextView tvCorrectas;

        public PartidaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvApodo = itemView.findViewById(R.id.tvApodo);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvCorrectas = itemView.findViewById(R.id.tvCorrectas);
        }

        public void bind(Partida partida) {
            tvApodo.setText(partida.getApodo());
            tvFecha.setText(partida.getFecha());
            tvCorrectas.setText(String.valueOf(partida.getCorrectas()));
        }
    }
}
