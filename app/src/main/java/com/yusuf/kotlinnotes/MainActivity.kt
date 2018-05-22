package com.yusuf.kotlinnotes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            if (it == fab) {
                NoteActivity().newNote(this@MainActivity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        list.adapter = NotesAdapter(this)
    }
}
