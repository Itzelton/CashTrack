package com.example.cashtrack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cashtrack.R
import com.example.cashtrack.adapters.MoreOptionsAdapter
import com.example.cashtrack.models.MoreOption

class MoreFragment : Fragment() {

    private lateinit var moreOptionsAdapter: MoreOptionsAdapter
    private val moreOptions = mutableListOf<MoreOption>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(view)
        loadMoreOptions()
    }

    private fun setupRecyclerView(view: View) {
        moreOptionsAdapter = MoreOptionsAdapter(moreOptions) { option ->
            handleOptionClick(option)
        }
        view.findViewById<RecyclerView>(R.id.rv_more_options).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moreOptionsAdapter
        }
    }

    private fun loadMoreOptions() {
        moreOptions.addAll(getMoreOptions())
        moreOptionsAdapter.notifyDataSetChanged()
    }

    private fun getMoreOptions(): List<MoreOption> {
        return listOf(
            MoreOption("Profile", "Manage your profile", R.drawable.ic_person),
            MoreOption("Settings", "App preferences", R.drawable.ic_settings),
            MoreOption("Security", "Password & Security", R.drawable.ic_security),
            MoreOption("Notifications", "Notification settings", R.drawable.ic_notifications),
            MoreOption("Export Data", "Export your financial data", R.drawable.ic_export),
            MoreOption("Help & Support", "Get help", R.drawable.ic_help),
            MoreOption("About", "App information", R.drawable.ic_info),
            MoreOption("Logout", "Sign out of your account", R.drawable.ic_logout)
        )
    }

    private fun handleOptionClick(option: MoreOption) {
        when (option.title) {
            "Profile" -> {
                Toast.makeText(context, "Profile clicked", Toast.LENGTH_SHORT).show()
                // Navigate to profile
            }
            "Settings" -> {
                Toast.makeText(context, "Settings clicked", Toast.LENGTH_SHORT).show()
                // Navigate to settings
            }
            "Security" -> {
                Toast.makeText(context, "Security clicked", Toast.LENGTH_SHORT).show()
                // Navigate to security settings
            }
            "Notifications" -> {
                Toast.makeText(context, "Notifications clicked", Toast.LENGTH_SHORT).show()
                // Navigate to notification settings
            }
            "Export Data" -> {
                Toast.makeText(context, "Export Data clicked", Toast.LENGTH_SHORT).show()
                // Handle data export
            }
            "Help & Support" -> {
                Toast.makeText(context, "Help & Support clicked", Toast.LENGTH_SHORT).show()
                // Navigate to help
            }
            "About" -> {
                Toast.makeText(context, "About clicked", Toast.LENGTH_SHORT).show()
                // Show about dialog
            }
            "Logout" -> {
                Toast.makeText(context, "Logout clicked", Toast.LENGTH_SHORT).show()
                // Handle logout
            }
        }
    }
}