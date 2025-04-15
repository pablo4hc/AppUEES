package com.example.miapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miapp.Utility.BaseDeDatosHelper;
import com.example.miapp.Utility.General;
import com.example.miapp.models.PublicacionCabecera;

import java.util.ArrayList;
import java.util.List;

public class PublicacionesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicacionAdapter adapter;
    private List<Publicacion> listaPublicaciones;
    private List<Publicacion> listaCompletaPublicaciones; // Guarda todas las publicaciones
    private BaseDeDatosHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicaciones);

        ImageView btnRegresar = findViewById(R.id.btn_regresar);
        btnRegresar.setOnClickListener(v -> finish());
        dbHelper = new BaseDeDatosHelper(this);
        // Inicializa RecyclerView
        recyclerView = findViewById(R.id.recyclerViewPublicaciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaCompletaPublicaciones = new ArrayList<>();
        cargarPublicaciones();

        // Inicialmente, mostramos todas las publicaciones
        listaPublicaciones = new ArrayList<>(listaCompletaPublicaciones);

        adapter = new PublicacionAdapter(listaPublicaciones, this);
        recyclerView.setAdapter(adapter);

        // Configura botones del Toolbar
        ImageView perfilIcon = findViewById(R.id.icono_perfil);
        ImageView homeIcon = findViewById(R.id.icono_home);
        ImageView miRedIcon = findViewById(R.id.icono_mi_red);

        perfilIcon.setOnClickListener(v -> mostrarMenuPerfil(v));

        homeIcon.setOnClickListener(v -> {
            // Muestra todas las publicaciones
            filtrarPublicaciones(true);
        });

        miRedIcon.setOnClickListener(v -> {
            // Muestra solo algunas publicaciones
            filtrarPublicaciones(false);
        });
        ImageView btn_publicacion = findViewById(R.id.icono_crear_publicacion);
        btn_publicacion.setOnClickListener(v -> {
            finish();
            General.navigateToActivity(this,CrearNoticiaActivity.class);
        });
    }

    private void mostrarMenuPerfil(View v) {
        PopupMenu popupMenu = new PopupMenu(PublicacionesActivity.this, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_perfil, popupMenu.getMenu());
        popupMenu.show();
    }

    private void filtrarPublicaciones(boolean mostrarTodas) {
        listaPublicaciones.clear();

        if (mostrarTodas) {
            listaPublicaciones.addAll(listaCompletaPublicaciones);
        } else {
            // Filtra solo algunas publicaciones (ejemplo: las primeras 3)
            for (int i = 0; i < Math.min(3, listaCompletaPublicaciones.size()); i++) {
                listaPublicaciones.add(listaCompletaPublicaciones.get(i));
            }
        }

        adapter.notifyDataSetChanged();
    }

    private void cargarPublicaciones() {
        List<PublicacionCabecera>cabecera_publicacion= dbHelper.obtenerPublicacionCabecera(1);
        List<PublicacionCabecera.PublicacionDetalle>detalle_publicacion= dbHelper.obtenerPublicacionDetalle(1);
        String imagen_detalle="";
        for(int i=0;i<cabecera_publicacion.size();i++){
            if(detalle_publicacion.size()>0){
                for(PublicacionCabecera.PublicacionDetalle d:detalle_publicacion){
                    if(d.getId_publicacion()==cabecera_publicacion.get(i).getId_publicacion()){
                        imagen_detalle=d.getImagen_documento();
                        break;
                    }
                }
            }
            listaCompletaPublicaciones
                    .add(new Publicacion("Facultad de Derecho, Política y Desarrollo",
                            cabecera_publicacion.get(i).getDescripcion(),
                            //"Este mes sera el inicio del curso tan esperado, puedes registrarte desde ahora. \nFecha: 16/03/2025 \nHora: 17:00h - 19:00",
                            R.drawable.perfilfacultad, imagen_detalle,1));
        }
        listaCompletaPublicaciones
                .add(new Publicacion("Facultad de Derecho, Política y Desarrollo",
                        "Este mes sera el inicio del curso tan esperado, puedes registrarte desde ahora. \nFecha: 16/03/2025 \nHora: 17:00h - 19:00",
                        R.drawable.perfilfacultad, R.drawable.publi1));

        listaCompletaPublicaciones.add(new Publicacion("Facultad de Derecho, Política y Desarrollo",
                "Se abren las inscripciones desde hoy, no lo olvides!!!  \nFecha: 01/04/2025 \nHora: 10:00h - 12:00h",
                R.drawable.perfilfacultad, R.drawable.imagen_video));

        listaCompletaPublicaciones.add(new Publicacion("Facultad de Derecho, Política y Desarrollo",
                "Desde hoy tendremos el inicio de esta colaboracion TED x UEES \n" + //
                        "Fecha: 10/04/2025 \n" + //
                        "Hora: 08:00h - 14:00h",
                R.drawable.perfilfacultad, R.drawable.publi2));

        listaCompletaPublicaciones.add(new Publicacion("Facultad de Estudios Internacionales",
                "Abrimos el curso online desde ahora para todos los emprendedores del futuro \n" + //
                        "Fecha: 01/04/2025 \n" + //
                        "Hora: 10:00h - 12:00h",
                R.drawable.perfilfacultad, R.drawable.publi3));

        listaCompletaPublicaciones.add(new Publicacion("Facultad de ingeniería",
                "Abrimos el webinar de gran interes \n" + //
                        "Fecha: 25/03/2025 \n" + //
                        "Hora: 15:00h - 17:00h",
                R.drawable.perfilfacultad, R.drawable.publi4));

        listaCompletaPublicaciones.add(new Publicacion("Facultad de Turismo y Hospitalidad ",
                "Recuerda que por aqui tambien puedes iniciar tu maestria \n" + //
                        "Fecha: 16/03/2025 \n" + //
                        "Hora: 10:00h - 12:00h",
                R.drawable.perfilfacultad, R.drawable.publi5));

        listaCompletaPublicaciones
                .add(new Publicacion("Facultad de Arquitectura y Diseño",
                        "Tenemos este curso de Ingles en el cual te puedes registrar desde ahora, para que mejores tu nivel \n"
                                + //
                                "Fecha: 20/03/2025 \n" + //
                                "Hora: 15:00h - 18:00",
                        R.drawable.perfilfacultad, R.drawable.publi6));

        listaCompletaPublicaciones.add(new Publicacion("Facultad de Ciencias de la Comunicación",
                "Aprender excel siempre es importante! registrate desde aqui.. \n" + //
                        "Fecha: 01/04/2025 \n" + //
                        "Hora: 10:00h - 12:00h",
                R.drawable.perfilfacultad, R.drawable.publi7));


    }
    // Decodificar Base64 a Bitmap

}
