package it.androidavanzato.mvp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private List<Note> notes = new ArrayList<>();

    private Context context;

    public NoteAdapter(Context context) {
        this.context = context;
    }

    @Override public int getCount() {
        return notes.size();
    }

    @Override public Note getItem(int position) {
        return notes.get(position);
    }

    @Override public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        }
        Note note = getItem(position);
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(note.getTitle());
        ((TextView) convertView.findViewById(android.R.id.text2)).setText(note.getDescription());
        return convertView;
    }

    public void replaceData(Collection<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        notifyDataSetChanged();
    }

    @Override public boolean hasStableIds() {
        return true;
    }

    public List<Note> getNotes() {
        return notes;
    }
}
