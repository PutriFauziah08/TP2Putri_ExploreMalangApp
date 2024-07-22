package com.putri.exploremalang.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.putri.exploremalang.R;
import com.putri.exploremalang.model.Wisata;
import com.putri.exploremalang.view.DetailActivity;
import java.util.ArrayList;
import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.WisataViewHolder> {

    private List<Wisata> wisataList;
    private List<Wisata> wisataListFull;
    private String categoryFilter = "";

    public WisataAdapter(List<Wisata> wisataList) {
        this.wisataList = wisataList;
        this.wisataListFull = new ArrayList<>(wisataList);
    }

    @NonNull
    @Override
    public WisataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata, parent, false);
        return new WisataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WisataViewHolder holder, int position) {
        Wisata wisata = wisataList.get(position);
        holder.itemTitle.setText(wisata.getNama());
        Glide.with(holder.itemImage.getContext()).load(wisata.getGambarUrl()).into(holder.itemImage);

        // Set click listener for the item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("wisata", wisata);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return wisataList.size();
    }

    public void filterByCategory(String category) {
        categoryFilter = category;
        filter("");
    }

    public void filter(String query) {
        List<Wisata> filteredList = new ArrayList<>();
        for (Wisata wisata : wisataListFull) {
            boolean matchesQuery = query.isEmpty() || wisata.getNama().toLowerCase().contains(query.toLowerCase());
            boolean matchesCategory = categoryFilter.isEmpty() || wisata.getKategori().equalsIgnoreCase(categoryFilter);

            if (matchesQuery && matchesCategory) {
                filteredList.add(wisata);
            }
        }
        wisataList = filteredList;
        notifyDataSetChanged();
    }

    public static class WisataViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDescription;
        public TextView readMore;

        public WisataViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            readMore = itemView.findViewById(R.id.readMore);
        }
    }
}
