package com.example.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private ArrayList<Data> localDataList;

    MainAdapter(ArrayList<Data> dataList){
        localDataList = dataList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            textView = itemView.findViewById(R.id.genreText);
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }
        public TextView getTextView() { return textView; }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 一列ごとにビューを配置
        View view = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.recycle_row, parent, false);
        return new ViewHolder(view);
    }

    // viewHolderへpositionの位置のアイテムのデータを送信
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int wkPositon = position;

        String genreStr = localDataList.get(position).getGenre();
        holder.getTextView().setText(genreStr);

        String textMemo = localDataList.get(position).getTextMemo();
        holder.getCheckBox().setText(textMemo);
        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                localDataList.get(wkPositon).setDelflg(b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataList.size();    // データの個数
    }

    public ArrayList<Data> getDataList(){
        return localDataList;
    }
}
