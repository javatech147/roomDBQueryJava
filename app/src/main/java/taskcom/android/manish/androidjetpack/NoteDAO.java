package taskcom.android.manish.androidjetpack;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Manish Kumar on 11/6/2018
 */
@Dao
public interface NoteDAO {
    @Insert
    void insertNote(Note note);

    //Compile time check. If query will be incorrect then error will be shown here.
    @Query("select * from notes")
    LiveData<List<Note>> getAllNotes();
}

