package com.example.cashtrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cashtrack.R
import com.example.cashtrack.utils.CurrencyUtils
import com.example.cashtrack.models.SavingsGoal
import java.text.NumberFormat


class SavingsGoalAdapter(private val goals: List<SavingsGoal>) :
    RecyclerView.Adapter<SavingsGoalAdapter.SavingsGoalViewHolder>() {

    class SavingsGoalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_goal_name)
        val progress: ProgressBar = view.findViewById(R.id.progress_bar)
        val amount: TextView = view.findViewById(R.id.tv_amount)
        val percentage: TextView = view.findViewById(R.id.tv_percentage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavingsGoalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_savings_goal, parent, false)
        return SavingsGoalViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavingsGoalViewHolder, position: Int) {
        val goal = goals[position]
        holder.name.text = goal.name
        holder.progress.progress = goal.progressPercentage
        holder.amount.text = "GH₵${String.format("%.2f", goal.currentAmount)} / GH₵${String.format("%.2f", goal.targetAmount)}"
        holder.percentage.text = "${goal.progressPercentage}%"
    }

    override fun getItemCount() = goals.size
}