package taskcom.android.manish.androidjetpack;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

/**
 * Created by Manish Kumar on 11/6/2018
 */
public class NoteViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private NoteDAO noteDAO;
    private NoteRoomDatabase noteRoomDatabase;
    private LiveData<List<Note>> allNoteList;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRoomDatabase = NoteRoomDatabase.getDatabase(application);
        noteDAO = noteRoomDatabase.noteDAO();

        allNoteList = noteDAO.getAllNotes();    // Due to live data, this operation will automatically performed on Background thread.
    }

    public void inset(Note note) {
        new InsertAsyncTask(noteDAO).execute(note);
    }

    LiveData<List<Note>> getAllNotes() {
        return allNoteList;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    private class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
        NoteDAO noteDAO;

        public InsertAsyncTask(NoteDAO noteDAO) {
            this.noteDAO = noteDAO;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insertNote(notes[0]);
            return null;
        }
    }
}
