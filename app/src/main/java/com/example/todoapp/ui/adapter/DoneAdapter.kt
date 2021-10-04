package com.example.todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.base.BaseAdapter
import com.example.todoapp.databinding.ItemTodoBinding
import com.example.todoapp.extension.hide
import com.example.todoapp.model.Todo

class DoneAdapter(
    private val context: Context,
    private val data: List<Todo>,
) : BaseAdapter<Todo>(context, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemTodoBinding
        if (convertView == null) {
            binding = ItemTodoBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ItemTodoBinding
        }
        val todos = getItem(position)
        binding.todos = todos

        return binding.root
    }
}