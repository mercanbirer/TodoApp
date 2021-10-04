package com.example.todoapp.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Log.i
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.todoapp.R
import com.example.todoapp.base.BaseFragment
import com.example.todoapp.databinding.FragmentMenuBinding
import com.example.todoapp.extension.hide
import com.example.todoapp.extension.show
import com.example.todoapp.model.Todo
import com.example.todoapp.service.ForegroundService
import com.example.todoapp.ui.adapter.DoneAdapter
import com.example.todoapp.ui.adapter.TODOAdapter
import com.github.ajalt.timberkt.e
import com.github.ajalt.timberkt.i


class MenuFragment : BaseFragment<FragmentMenuBinding>(R.layout.fragment_menu) {
    private lateinit var tAdapter: TODOAdapter
    private lateinit var doneAdapter: DoneAdapter
    private val taskList = arrayListOf<Todo>()
    private val doneList = arrayListOf<Todo>()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnAddTask.setOnClickListener {
            if (binding.editTextTitle.text.toString().isEmpty()
                || binding.editTextDescription.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "Enter the Data", Toast.LENGTH_SHORT).show()
            } else {
                val title = binding.editTextTitle.text.toString()
                val desc = binding.editTextDescription.text.toString()
                taskList.add(Todo(title, desc))
                tAdapter = TODOAdapter(requireContext(), taskList)
                binding.listview.adapter = tAdapter
            }
        }
        if (this :: tAdapter.isInitialized){
            taskList.addAll(tAdapter.getDeletedList())
            doneAdapter = DoneAdapter(requireContext(), doneList)
            binding.listItem.adapter = doneAdapter
        }



        binding.swButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ForegroundService.startService(requireContext(), "Tab to add to...")
            } else {
                ForegroundService.stopService(requireContext())
            }
        }
    }

}