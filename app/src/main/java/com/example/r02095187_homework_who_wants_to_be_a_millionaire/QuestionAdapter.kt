package com.example.r02095187_homework_who_wants_to_be_a_millionaire

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter(
    private val context: Context,
    private val question: QuestionModel,
    private val listener: OnOptionSelectedListener
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    private var selectedAnswerIndex: Int = -1 // Default value indicating no answer selected

    // Interface to handle option selection events
    interface OnOptionSelectedListener {
        fun onOptionSelected(optionIndex: Int)
    }

    // ViewHolder class representing each item in the RecyclerView
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val optionButton: Button = itemView.findViewById(R.id.optionButton)

        init {
            optionButton.setOnClickListener {
                selectedAnswerIndex = adapterPosition
                notifyDataSetChanged() // Refresh the adapter to update the UI
            }
        }
    }


    // Create ViewHolder instances for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the layout for each item
        val view = LayoutInflater.from(context).inflate(R.layout.item_question_option, parent, false)
        return ViewHolder(view)
    }

    // Bind data to the ViewHolder and set click listeners
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val option = question.options[position]
        holder.optionButton.text = option

        // Highlight the selected answer
        holder.optionButton.isSelected = (selectedAnswerIndex == position)

        // Set a click listener for the answer option button (if needed)
        holder.itemView.setOnClickListener {
            // Notify the listener when an option is selected (if needed)
            listener.onOptionSelected(holder.adapterPosition)
        }
    }

    // Return the total number of answer options
    override fun getItemCount(): Int {
        return question.options.size
    }
}
