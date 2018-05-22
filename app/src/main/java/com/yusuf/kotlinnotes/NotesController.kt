package com.yusuf.kotlinnotes

import android.content.Context

object NotesController {

    fun getAllNotes(context: Context): ArrayList<Note> {
        val sharedPreferences = context.getSharedPreferences()
        val notesMap = sharedPreferences.all
        val notesList: ArrayList<Note> = arrayListOf()
        notesMap.forEach {
            notesList.add(Note(it.key, it.value as String))
        }
        return notesList
    }

    inline fun saveNote(context: Context, note: Note, noinline onCompleted: (noteTitle: String) -> Unit, onError: (noteTitle: String) -> Unit) {
        try {
            val sharedPreferencesEditor = context.getSharedPreferences("KotlinNotes", Context.MODE_PRIVATE).edit()
            sharedPreferencesEditor.putString(note.title, note.body)
            sharedPreferencesEditor.apply()
            onCompleted.invoke(note.title)
        } catch (e: Exception) {
            onError(note.title)
        }
    }


    fun deleteNote(context: Context, note: Note) {
        val sharedPreferencesEditor = context.getSharedPreferences().edit()
        sharedPreferencesEditor.remove(note.title)
        sharedPreferencesEditor.apply()
    }

    fun Context.getSharedPreferences() = getSharedPreferences("KotlinNotes", Context.MODE_PRIVATE)
}