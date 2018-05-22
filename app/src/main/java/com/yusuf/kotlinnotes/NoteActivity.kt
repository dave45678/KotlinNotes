package com.yusuf.kotlinnotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val note = intent.extras?.getSerializable("note") as Note?

        if (note != null) {
            noteTitle.setText(note.title)
            noteBody.setText(note.body)
        }

        btnSave.setOnClickListener {

            if (note != null && (note.title != noteTitle.text.toString() ||
                            note.body != noteBody.text.toString())) {
                NotesController.deleteNote(this, note)
            }

            NotesController.saveNote(this, Note(noteTitle.text.toString(), noteBody.text.toString()),
                    onCompleted = {
                        Toast.makeText(this, "Saved note $it", Toast.LENGTH_SHORT).show()
                    },
                    onError = {
                        Toast.makeText(this, "Error saving $it", Toast.LENGTH_SHORT).show()
                    })
        }
    }


    fun openNoteDetail(context: Context, note: Note) {
        val intent = Intent(context, NoteActivity::class.java)
        intent.putExtra("note", note)
        context.startActivity(intent)
    }

    fun newNote(context: Context) {
        val intent = Intent(context, NoteActivity::class.java)
        context.startActivity(intent)
    }
}
