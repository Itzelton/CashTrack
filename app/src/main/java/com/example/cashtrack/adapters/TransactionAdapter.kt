package com.example.cashtrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cashtrack.R
import com.example.cashtrack.models.Transaction
import java.text.NumberFormat

class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.tv_description)
        val amount: TextView = view.findViewById(R.id.tv_amount)
        val category: TextView = view.findViewById(R.id.tv_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.description.text = transaction.description
        holder.amount.text = "GH₵${String.format("%.2f", transaction.amount)}"
        holder.category.text = transaction.category

        // Set color based on amount (green for positive, red for negative)
        val color = if (transaction.amount >= 0) {
            android.graphics.Color.GREEN
        } else {
            android.graphics.Color.RED
        }
        holder.amount.setTextColor(color)
    }

    override fun getItemCount() = transactions.size
}
