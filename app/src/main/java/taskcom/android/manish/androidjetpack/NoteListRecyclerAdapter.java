package taskcom.android.manish.androidjetpack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Manish Kumar on 11/6/2018
 */
public class NoteListRecyclerAdapter extends RecyclerView.Adapter<NoteListRecyclerAdapter.NoteListViewHolder> {

    private Context context;
    private List<Note> noteList;

    public NoteListRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setNotes(List<Note> notes){
        noteList = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new NoteListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListViewHolder noteListViewHolder, int i) {
        if (noteList != null) {
            Note note = noteList.get(i);
            noteListViewHolder.setData(note.getNote(), i);
        } else {
            noteListViewHolder.textNote.setText("No Notes");
        }
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteListViewHolder extends RecyclerView.ViewHolder {
        private TextView textNote;
        private int position;

        public NoteListViewHolder(@NonNull View itemView) {
            super(itemView);
            textNote =itemView.findViewById(R.id.tvItem);
        }

        public void setData(String note, int position) {
            textNote.setText(note);
            this.position = position;
        }
    }
}
