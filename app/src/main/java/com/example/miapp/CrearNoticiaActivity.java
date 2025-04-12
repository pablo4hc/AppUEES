package com.example.miapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miapp.Adapter.AdapterFotoPublicacion;
import com.example.miapp.Utility.BaseDeDatosHelper;
import com.example.miapp.models.PublicacionCabecera;

import java.util.ArrayList;
import java.util.List;

public class CrearNoticiaActivity extends AppCompatActivity {

    private ImageView img_foto;
    private ImageView img_documento;
    private static final int MAX_SELECTION = 5;
    private RecyclerView recyclerView;
    private AdapterFotoPublicacion myAdapter;
    private List<Uri> dataList;
    private ImageView imageFoto_1;
    private Button btn_publicar;
    private TextView txt_publicacion;
    private ArrayList<Uri> imagenes_list = new ArrayList<>();
    private BaseDeDatosHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_crear_noticia);
        imageFoto_1 = findViewById(R.id.imagePublicacion);
        img_foto = (ImageView) findViewById(R.id.img_foto);
        img_documento = (ImageView) findViewById(R.id.img_documento);
        img_foto.setOnClickListener(v -> openGallery());
        dataList=new ArrayList<>();
        recyclerView = findViewById(R.id.rc_fotos);
        btn_publicar = findViewById(R.id.btn_publicar);
        txt_publicacion = findViewById(R.id.txt_publicacion);
        btn_publicar.setOnClickListener(view -> {
            String descripcion=txt_publicacion.getText().toString();
            if(descripcion.equals("")){
                Toast.makeText(this, "Debe ingresar una idea", Toast.LENGTH_LONG).show();
                return;
            }
            dbHelper.insertarPublicacion(new PublicacionCabecera(1,1,1,"",0,"","","","",""));
            dbHelper.insertarPublicacionDetalle(1,imagenes_list);

        });
        dbHelper = new BaseDeDatosHelper(this);

    }
    private static final int PICK_IMAGE_REQUEST = 1;  // Código para la selección de la imagen
    private static final int REQUEST_PERMISSION = 100;

    public void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");  // Especificamos que solo queremos imágenes
 //       startActivityForResult(intent, PICK_IMAGE_REQUEST);  // Iniciamos la actividad y esperamos la respuesta
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // Permitir selección múltiple
        intent.setData(Uri.parse("content://media/external/images/media"));

        // Lanzar la actividad para seleccionar imágenes
        galleryResultLauncher.launch(intent);
    }
    // ActivityResultLauncher para manejar la respuesta de la selección de imágenes
    @SuppressLint("SuspiciousIndentation")
    private final ActivityResultLauncher<Intent> galleryResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    // Verifica si se seleccionaron múltiples imágenes
                    if (result.getData().getClipData() != null) {
                        int count = result.getData().getClipData().getItemCount();
                        imagenes_list = new ArrayList<>();
                        if (count > MAX_SELECTION) {
                            Toast.makeText(this, "Puedes seleccionar hasta " + MAX_SELECTION + " imágenes.", Toast.LENGTH_SHORT).show();
                        }// else {
                            if(count<MAX_SELECTION){
                                for (int i = 0; i < count; i++) {
                                    imagenes_list.add(result.getData().getClipData().getItemAt(i).getUri());
                                }
                            }else{
                                for (int i = 0; i < MAX_SELECTION; i++) {
                                    imagenes_list.add(result.getData().getClipData().getItemAt(i).getUri());
                                }
                            }
                            if(imagenes_list.size()==1){
                                recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
                                //imageFoto_1.setVisibility(View.VISIBLE);
                                //recyclerView.setVisibility(View.GONE);
                              //  imageFoto_1.setImageURI(imagenes_list.get(0));
                            }else{
                                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                             //   imageFoto_1.setVisibility(View.GONE);
                              //  recyclerView.setVisibility(View.VISIBLE);

                            }
                            myAdapter = new AdapterFotoPublicacion(this,imagenes_list);
                            recyclerView.setAdapter(myAdapter);
                        // Hacer algo con las imágenes seleccionadas
                            //Toast.makeText(this, "Imágenes seleccionadas: " + count, Toast.LENGTH_SHORT).show();
                        //}
                    } else if (result.getData().getData() != null) {
                        Log.i("occc","pruebas ");
                        // Si solo se seleccionó una imagen
                        Uri selectedImage = result.getData().getData();
                        Toast.makeText(this, "Imagen seleccionada: " + selectedImage.toString(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(this, "No se seleccionaron imágenes", Toast.LENGTH_SHORT).show();
                }
            });
    //@Override
   /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                // Aquí se obtiene la URI de la imagen seleccionada y puedes mostrarla en un ImageView
               ImageView imageView = findViewById(R.id.imagePublicacion);
                imageView.setVisibility(View.VISIBLE);
               imageView.setImageURI(selectedImageUri);
                Log.i("occc","pruebas "+selectedImageUri);
            }
        }
    }*/
}