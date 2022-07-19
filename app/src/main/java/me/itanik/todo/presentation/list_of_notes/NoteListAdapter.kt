package me.itanik.todo.presentation.list_of_notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.itanik.todo.R
import me.itanik.todo.data.model.Note

class NoteListAdapter(private val onItemClick: (Note) -> Unit) :
    ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteListDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
        holder.itemView.setOnClickListener { onItemClick(note) }
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.note_item_title)
        private val detailsTextView: TextView = itemView.findViewById(R.id.note_item_details)

        fun bind(note: Note) {
            titleTextView.text = note.title
            detailsTextView.text = note.details
        }
    }
}

object NoteListDiffCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem == newItem

}
