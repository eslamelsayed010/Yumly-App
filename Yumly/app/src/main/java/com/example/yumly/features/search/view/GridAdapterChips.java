package com.example.yumly.features.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.yumly.R;
import com.example.yumly.core.models.CatModel;
import com.example.yumly.core.models.CountryModel;
import java.util.ArrayList;

public class GridAdapterChips extends RecyclerView.Adapter<GridAdapterChips.ViewHolder> {

    private Context context;
    private ArrayList<CountryModel> countries;
    private ArrayList<CatModel> cats;
    OnItemClickListener listener;

    public GridAdapterChips(Context context, ArrayList<CountryModel> countries,OnItemClickListener listener)
    {
        this.listener = listener;
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CountryModel countryModel = countries.get(position);
        holder.textView.setText(countryModel.getName());
        holder.imageView.setImageResource(countryModel.getImage());
        holder.linearLayout.setOnClickListener(v-> listener.onCountryClick(countryModel));

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ships_image_id);
            textView = itemView.findViewById(R.id.ships_text_id);
            linearLayout =itemView.findViewById(R.id.item_click_id);
        }
    }
}