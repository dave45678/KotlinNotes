package com.yusuf.kotlinnotes

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class NotesAdapter(val context: Context, val notes: ArrayList<Note> = arrayListOf()): BaseAdapter() {

    init {
        notes.addAll(NotesController.getAllNotes(context))
    }

    override fun getItem(position: Int) = notes[position]
    override fun getItemId(position: Int) = position.toLong()
    override fun getCount() = notes.size


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val note = getItem(position)
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_expandable_list_item_2, parent, false)
        view.findViewById<TextView>(android.R.id.text1).text = note.title
        view.findViewById<TextView>(android.R.id.text2).text = note.body

        view.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                NoteActivity().openNoteDetail(context, note)
            }

        })

        return view
    }
}