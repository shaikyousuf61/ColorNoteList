package com.skydev.colornotelist;

import androidx.cardview.widget.CardView;

import com.skydev.colornotelist.Models.Notes;

public interface NoteClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}
