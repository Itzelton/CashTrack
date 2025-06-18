package com.example.cashtrack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cashtrack.R
import com.example.cashtrack.adapters.TransactionAdapter
import com.example.cashtrack.models.Transaction
import com.example.cashtrack.utils.CurrencyUtils
class DashboardFragment : Fragment() {

    private lateinit var transactionAdapter: TransactionAdapter
    private val transactions = mutableListOf<Transaction>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
        loadDashboardData(view)

        // Quick action buttons
        view.findViewById<Button>(R.id.btn_deposit).setOnClickListener {
            // Navigate to add expense
        }

        view.findViewById<Button>(R.id.btn_transfer).setOnClickListener {
            // Navigate to transfer money
        }

        view.findViewById<Button>(R.id.btn_pay_bill).setOnClickListener {
            // Navigate to bill payment
        }
    }

    private fun setupRecyclerView(view: View) {
        transactionAdapter = TransactionAdapter(transactions)
        view.findViewById<RecyclerView>(R.id.rv_recent_transactions).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }
    }

    private fun loadDashboardData(view: View) {
        // Load account balances
        view.findViewById<TextView>(R.id.tv_total_balance).text = "GH₵12,450.67"



        // Load recent transactions
        transactions.addAll(getSampleTransactions())
        transactionAdapter.notifyDataSetChanged()
    }

    private fun getSampleTransactions(): List<Transaction> {
        return listOf(
            Transaction("Grocery Store", -89.34, "Food", System.currentTimeMillis()),
            Transaction("Salary Deposit", 3500.00, "Income", System.currentTimeMillis() - 86400000),
            Transaction("Electric Bill", -125.67, "Utilities", System.currentTimeMillis() - 172800000)
        )
    }
}
