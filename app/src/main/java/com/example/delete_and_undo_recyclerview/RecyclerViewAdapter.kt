package com.example.delete_and_undo_recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter    // creating a constructor class.
    (// creating a variable for our array list and context.
    private val courseDataArrayList: ArrayList<RecyclerData>, private val mcontext: Context
) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // Inflate Layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // Set the data to textview from our modal class.
        val recyclerData = courseDataArrayList[position]
        holder.courseNameTV.text = recyclerData.title
        holder.courseDescTV.text = recyclerData.description
    }

    override fun getItemCount(): Int {
        // this method returns
        // the size of recyclerview
        return courseDataArrayList.size
    }

    // View Holder Class to handle Recycler View.
    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating a variable for our text view.
        val courseNameTV: TextView
        val courseDescTV: TextView

        init {
            // initializing our text views.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName)
            courseDescTV = itemView.findViewById(R.id.idTVCourseDesc)
        }
    }
}
