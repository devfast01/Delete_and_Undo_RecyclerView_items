package com.example.delete_and_undo_recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    // creating a variable for recycler view,
    // array list and adapter class.
    private var courseRV: RecyclerView? = null
    private var recyclerDataArrayList: ArrayList<RecyclerData>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourse)

        // creating new array list.
        recyclerDataArrayList = ArrayList()

        // in below line we are adding data to our array list.
        recyclerDataArrayList!!.add(RecyclerData("DSA Course", "DSA Self Paced Course"))
        recyclerDataArrayList!!.add(RecyclerData("C++ Course", "C++ Self Paced Course"))
        recyclerDataArrayList!!.add(RecyclerData("Java Course", "Java Self Paced Course"))
        recyclerDataArrayList!!.add(RecyclerData("Python Course", "Python Self Paced Course"))
        recyclerDataArrayList!!.add(RecyclerData("Fork CPP", "Fork CPP Self Paced Course"))
        recyclerDataArrayList!!.add(RecyclerData("Amazon SDE", "Amazon SDE Test Questions"))

        // initializing our adapter class with our array list and context.
        recyclerViewAdapter = RecyclerViewAdapter(recyclerDataArrayList!!, this)

        // below line is to set layout manager for our recycler view.
        val manager = LinearLayoutManager(this)

        // setting layout manager for our recycler view.
        courseRV!!.setLayoutManager(manager)

        // below line is to set adapter
        // to our recycler view.
        courseRV!!.setAdapter(recyclerViewAdapter)

        // on below line we are creating a method to create item touch helper
        // method for adding swipe to delete functionality.
        // in this we are specifying drag direction and position to right
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                val deletedCourse = recyclerDataArrayList!![viewHolder.adapterPosition]

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                recyclerDataArrayList!!.removeAt(viewHolder.adapterPosition)

                // below line is to notify our item is removed from adapter.
                recyclerViewAdapter!!.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                Snackbar.make(courseRV!!, deletedCourse.title, Snackbar.LENGTH_LONG).setAction(
                    "Undo"
                ) { // adding on click listener to our action of snack bar.
                    // below line is to add our item to array list with a position.
                    recyclerDataArrayList!!.add(position, deletedCourse)

                    // below line is to notify item is
                    // added to our adapter class.
                    recyclerViewAdapter!!.notifyItemInserted(position)
                }.show()
            } // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(courseRV)
    }
}
