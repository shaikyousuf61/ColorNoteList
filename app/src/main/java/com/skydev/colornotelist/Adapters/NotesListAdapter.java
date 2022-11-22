package com.skydev.colornotelist.Adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.skydev.colornotelist.Models.Notes;
import com.skydev.colornotelist.NoteClickListener;
import com.skydev.colornotelist.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends  RecyclerView.Adapter<NotesViewHolder>{

    Context context;
    List<Notes> list;
    NoteClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NoteClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textView_Title.setText(list.get(position).getTitle());
        holder.textView_Title.setSelected(true);

        holder.textView_Notes.setText(list.get(position).getNotes());

        holder.textView_Date.setText(list.get(position).getDate());
        holder.textView_Date.setSelected(true);

        if (list.get(position).isPinned()){
            holder.imageView_pin.setImageResource(R.drawable.ic_pin);
        }
        else
            holder.imageView_pin.setImageResource(0);

        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);
                return true;
            }
        });

    }

    private  int getRandomColor(){
        List<Integer> ColorCode = new ArrayList<>();

        ColorCode.add(R.color.color1);
        ColorCode.add(R.color.color3);
        ColorCode.add(R.color.color2);
        ColorCode.add(R.color.color4);
        ColorCode.add(R.color.color5);

        Random random = new Random();
        int random_color = random.nextInt(ColorCode.size());

        return ColorCode.get(random_color);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Notes> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }
}


class NotesViewHolder extends RecyclerView.ViewHolder {
    CardView notes_container;
    TextView textView_Title,textView_Notes,textView_Date;
    ImageView imageView_pin;


    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        notes_container = itemView.findViewById(R.id.notes_container);
        textView_Title = itemView.findViewById(R.id.textView_Title);
        textView_Notes = itemView.findViewById(R.id.textView_Notes);
        textView_Date =  itemView.findViewById(R.id.textView_Date);
        imageView_pin = itemView.findViewById(R.id.imageView_pin);


    }
}
