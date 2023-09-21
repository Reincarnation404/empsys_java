package com.example.sqlitepractice.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitepractice.R;
import com.example.sqlitepractice.model.Emp;

import java.util.ArrayList;
import java.util.Objects;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    ArrayList<Emp> emp;
    LayoutInflater context;


    public DataAdapter(ArrayList<Emp> emp, Context context) {
        this.emp = emp;
        this.context = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDataName;
        TextView tvDataExNumber;
        ConstraintLayout DataList;

        ViewHolder(View itemView) {
            super(itemView);
            tvDataName = itemView.findViewById(R.id.tvDataName);
            tvDataExNumber = itemView.findViewById(R.id.tvDataExNumber);
            DataList = itemView.findViewById(R.id.DataList);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        String name = emp.get(position).getName();
        String num = emp.get(position).getExNum();
        holder.tvDataName.setText(name);
        holder.tvDataExNumber.setText(num);

//        String title = Emp.getJobTitle();
//
//
//        if (title.matches("部長")){
//            holder.DataList.setBackgroundColor(Objects.requireNonNull(getClass().getResource()).getColor());
//        }else {
//            holder.DataList.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
//        }
    }

    @Override
    public int getItemCount() {
        return emp.size();
    }

    public void setFilter(ArrayList<Emp> newEmp){
        emp = new ArrayList<>();
        emp.addAll(newEmp);
        notifyDataSetChanged();

    }
}
