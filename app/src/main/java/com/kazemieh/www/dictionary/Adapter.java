package com.kazemieh.www.dictionary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {

    Context context;
    List<DataModel> dataModels;

    Adapter(Context context, List<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.tv_en.setText(dataModels.get(position).getEn());
        holder.tv_fa.setText(dataModels.get(position).getFa());
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_en, tv_fa;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_en = itemView.findViewById(R.id.tv_Adapter_en);
            tv_fa = itemView.findViewById(R.id.tv_Adapter_fa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DataModel dataModel=dataModels.get(getAdapterPosition());
                    Intent intent=new Intent(context,MainActivity2.class);
                    intent.putExtra("id",dataModel.getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
