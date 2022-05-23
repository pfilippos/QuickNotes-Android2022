package com.example.quickNotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterNoteClass extends RecyclerView.Adapter<AdapterNoteClass.ViewHolder> {
    LayoutInflater inflater;
    List<Note> noteList;

    AdapterNoteClass(Context context, List<Note> noteList){
        this.inflater = LayoutInflater.from(context);
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public AdapterNoteClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.my_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNoteClass.ViewHolder holder, int position) {
        String title = noteList.get(position).getTitle();
        String date = noteList.get(position).getDate();
        String time = noteList.get(position).getTime();
        String text = noteList.get(position).getText();

        holder.notetitle.setText(title);
        holder.notedate.setText(date);
        holder.notetime.setText(time);
        holder.notetext.setText(text);


    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView notetitle,notetext,notedate,notetime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notetitle = itemView.findViewById(R.id.thenotetitle);
            notetext = itemView.findViewById(R.id.thenotetext);
            notedate = itemView.findViewById(R.id.thenotedate);
            notetime = itemView.findViewById(R.id.thenotetime);

            //!!!!FROM HERE YOU GET WHICH NOTE HAS BEEN PRESSED
            //Single Tapup
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Single Tap Position is " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });

            //Long Press
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(v.getContext(), "Long Tap and Position is " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });


        }
    }


}
