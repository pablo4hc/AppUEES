package com.example.miapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miapp.Utility.General;

import java.util.List;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.ViewHolder> {
    private List<Publicacion> listaPublicaciones;
    private Context context;

    public PublicacionAdapter(List<Publicacion> listaPublicaciones, Context context) {
        this.listaPublicaciones = listaPublicaciones;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publicacion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Publicacion publicacion = listaPublicaciones.get(position);
        holder.txtNombre.setText(publicacion.getNombre());
        holder.txtPublicacion.setText(publicacion.getTextoPublicacion());
        holder.imgPerfil.setImageResource(publicacion.getImagenPerfil());
        if(publicacion.getEs_sqlite()==1){
            Bitmap bitmap = General.base64ToBitmap(publicacion.getImagenPublicacion_base64().replaceAll("^\\[|\\]$", ""));
            // Mostrar imagen
            holder.imgPublicacion.setImageBitmap(bitmap);
        }else{
            holder.imgPublicacion.setImageResource(publicacion.getImagenPublicacion());
        }

        // Opciones "Comentar" y "Agendar"
        holder.txtAgendar.setText("Agendar");
        holder.txtPagar.setText("Pagar");

        // Evento de clic en la imagen de perfil para mostrar el menú flotante
        holder.imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPopupMenu(v);
            }
        });

        // Evento de clic en la imagen de la publicación para abrir el Dialog
        holder.imgPublicacion.setOnClickListener(v -> mostrarImagenEnDialog(publicacion.getImagenPublicacion()));
    }

    private void mostrarPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_popup_perfil, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.opcion_seguir) {
                    Toast.makeText(context, "Ahora sigues a este usuario", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        popup.show();
    }

    private void mostrarImagenEnDialog(int imagenResId) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_imagen);

        ImageView imgDialog = dialog.findViewById(R.id.imgDialog);
        imgDialog.setImageResource(imagenResId);

        // Cierra el diálogo al tocar la imagen
        imgDialog.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listaPublicaciones.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtPublicacion, txtAgendar, txtPagar;
        ImageView imgPerfil, imgPublicacion, iconoAgendar, iconoPagar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtPublicacion = itemView.findViewById(R.id.txtPublicacion);
            imgPerfil = itemView.findViewById(R.id.imgPerfil);
            imgPublicacion = itemView.findViewById(R.id.imgPublicacion);
            txtAgendar = itemView.findViewById(R.id.txtAgendar);
            iconoAgendar = itemView.findViewById(R.id.iconoAgendar);
            txtPagar = itemView.findViewById(R.id.txtPagar);
            iconoPagar = itemView.findViewById(R.id.iconoPagar);
        }
    }
}
