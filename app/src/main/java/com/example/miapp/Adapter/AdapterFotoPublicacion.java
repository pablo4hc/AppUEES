package com.example.miapp.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.miapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterFotoPublicacion extends RecyclerView.Adapter<AdapterFotoPublicacion.MyViewHolder> {
    private List<String> imageUrls;
    private List<Uri> dataList;
    private Context context;
    // Constructor
    public AdapterFotoPublicacion(Context context,List<Uri> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el layout de cada item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_publicacion, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Asignar los datos al item
        holder.img_foto_grid.setImageURI(dataList.get(position));
        ////holder.img_menu.setImageDrawable(dataList.get(position).getNombre_fotos());
        holder.btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                dataList.remove(pos);
                notifyItemRemoved(pos);
                //Toast.makeText(context, "Foto eliminada", Toast.LENGTH_SHORT).show();
            }
        });
        holder.img_foto_grid.setOnClickListener(v -> mostrarImagenEnDialog(dataList.get(position)));
    }
    private void mostrarImagenEnDialog(Uri imagenResId) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_imagen);

        ImageView imgDialog = dialog.findViewById(R.id.imgDialog);
        imgDialog.setImageURI(imagenResId);

        // Cierra el diálogo al tocar la imagen
        imgDialog.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // Clase ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_foto_grid;
        ImageView btnCerrar;

        ImageView img_menu;
        public MyViewHolder(View itemView) {
            super(itemView);
            img_foto_grid = itemView.findViewById(R.id.img_foto_grid);
            btnCerrar=itemView.findViewById(R.id.btnCerrar);

        }
    }
}
