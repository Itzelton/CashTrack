package com.example.cashtrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cashtrack.databinding.DialogAddGoalBinding
import com.example.cashtrack.databinding.FragmentSavingsBinding
import com.example.cashtrack.models.SavingsGoal
import com.example.cashtrack.adapters.SavingsGoalAdapter
class SavingsFragment : Fragment() {

    private var _binding: FragmentSavingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var savingsAdapter: SavingsGoalAdapter
    private val savingsGoals = mutableListOf<SavingsGoal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Setup RecyclerView
        savingsAdapter = SavingsGoalAdapter(savingsGoals)
        binding.rvSavingsGoals.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSavingsGoals.adapter = savingsAdapter

        // Add sample data (optional)
        seedSampleGoals()

        // FAB click to add new goal
        binding.fabAddGoal.setOnClickListener {
            showAddGoalDialog()
        }
    }

    private fun seedSampleGoals() {
        savingsGoals.add(SavingsGoal("New Phone", 800f, 400f))
        savingsGoals.add(SavingsGoal("Emergency Fund", 2000f, 1100f))
        savingsAdapter.notifyDataSetChanged()
    }

    private fun showAddGoalDialog() {
        val dialogBinding = DialogAddGoalBinding.inflate(LayoutInflater.from(requireContext()))
        AlertDialog.Builder(requireContext())
            .setTitle("New Savings Goal")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                val title = dialogBinding.etGoalTitle.text.toString().trim()
                val targetStr = dialogBinding.etGoalTarget.text.toString().trim()

                if (title.isNotEmpty() && targetStr.isNotEmpty()) {
                    val target = targetStr.toFloatOrNull()
                    if (target != null) {
                        val newGoal = SavingsGoal(title, target, 0f)
                        savingsGoals.add(newGoal)
                        savingsAdapter.notifyItemInserted(savingsGoals.size - 1)
                    } else {
                        Toast.makeText(requireContext(), "Enter a valid target amount", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Fill all fields", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
