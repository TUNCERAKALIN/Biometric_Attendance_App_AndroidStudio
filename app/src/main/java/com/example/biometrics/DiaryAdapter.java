package com.example.biometrics;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder>{

    private List<Entry> mEntries;

    public DiaryAdapter(List<Entry> mEntries) {
        this.mEntries = mEntries;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View entryView = inflater.inflate(R.layout.entry, parent, false);
        ViewHolder holder = new ViewHolder(entryView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entry diaryEntry = mEntries.get(position);
        TextView date = holder.date;
        date.setText(diaryEntry.get_date());
        TextView title = holder.title;
        title.setText(diaryEntry.get_title());
        title.setPaintFlags(title.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView textView = holder.textView;
        textView.setText(diaryEntry.get_text());

    }

    @Override
    public int getItemCount() {
        return mEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView title;
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.diaryDate);
            title = itemView.findViewById(R.id.diaryTitle);
            textView = itemView.findViewById(R.id.diaryEntry);
        }
        public TextView getTextView(){
            return textView;
        }
    }
}
