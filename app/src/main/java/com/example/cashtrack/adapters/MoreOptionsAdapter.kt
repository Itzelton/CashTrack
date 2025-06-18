package com.example.cashtrack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cashtrack.R
import com.example.cashtrack.models.MoreOption

class MoreOptionsAdapter(
    private val options: List<MoreOption>,
    private val onItemClick: (MoreOption) -> Unit
) : RecyclerView.Adapter<MoreOptionsAdapter.MoreOptionViewHolder>() {

    class MoreOptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.iv_option_icon)
        val title: TextView = view.findViewById(R.id.tv_option_title)
        val description: TextView = view.findViewById(R.id.tv_option_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreOptionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_more_option, parent, false)
        return MoreOptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoreOptionViewHolder, position: Int) {
        val option = options[position]
        holder.icon.setImageResource(option.iconResId)
        holder.title.text = option.title
        holder.description.text = option.description

        holder.itemView.setOnClickListener {
            onItemClick(option)
        }
    }

    override fun getItemCount() = options.size
}