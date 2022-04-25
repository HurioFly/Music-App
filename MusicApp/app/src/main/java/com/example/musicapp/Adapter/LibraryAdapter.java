package com.example.musicapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.R;

import java.util.ArrayList;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {
    private final ArrayList<String> titles = new ArrayList<>();
    private final ArrayList<Integer> icons = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.row_library, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgImageLibrary.setImageResource(icons.get(position));
        holder.txtTitleLibrary.setText(titles.get(position));
    }

    public String getTitle(int position) {
        return titles.get(position);
    }

    public int getIcon(int position) {
        return icons.get(position);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public void addLibrary(String title, int icon){
        titles.add(title);
        icons.add(icon);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgImageLibrary;
        TextView txtTitleLibrary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgImageLibrary = itemView.findViewById(R.id.imageViewLibrary);
            txtTitleLibrary = itemView.findViewById(R.id.textViewTitleLibrary);
        }
    }
}
