package com.example.miapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.miapp.EventoAdapter;
import com.example.miapp.Evento;
import com.example.miapp.Utility.General;

import java.util.ArrayList;
import java.util.List;

import android.widget.Button;
import android.widget.ImageView;

public class EventoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventoAdapter adapter;
    private List<Evento> listaEventos;
    private ImageView btn_nuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventos);

        ImageView btnRegresar = findViewById(R.id.btn_regresar);
        btnRegresar.setOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.recyclerViewEventos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaEventos = new ArrayList<>();
        cargarEventos();

        adapter = new EventoAdapter(listaEventos);
        recyclerView.setAdapter(adapter);
        btn_nuevo = findViewById(R.id.icono_perfil);

    }

    private void cargarEventos() {
        listaEventos.add(new Evento("Facultad de Ciencias de la Salud",
                "Este sabado tendremos la gran conferencia en la Biblioteca con un invitado sorpresa, no te la pierdas",
                R.drawable.perfilfacultad, R.drawable.conferencia));

        listaEventos.add(new Evento("Facultad de Comunicacion", "Espero puedas asistir",
                R.drawable.perfilfacultad, R.drawable.evento));

        listaEventos.add(new Evento("Facultad de Ciencias de la Salud",
                "Gran salida de campo apta para cualquier miembro de UEES",
                R.drawable.perfilfacultad, R.drawable.salida_campo));
    }
}
