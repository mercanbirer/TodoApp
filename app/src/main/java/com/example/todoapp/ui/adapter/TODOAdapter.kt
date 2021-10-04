package com.example.todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.base.BaseAdapter
import com.example.todoapp.databinding.ItemTodoBinding
import com.example.todoapp.extension.hide
import com.example.todoapp.model.Todo
import com.github.ajalt.timberkt.i

class TODOAdapter(
    private val context: Context,
    private var data: List<Todo>,
) : BaseAdapter<Todo>(context, data) {
    val newList = arrayListOf<Todo>()
    val list: MutableList<Todo> = arrayListOf()
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

        binding.delete.setOnClickListener {
            newList.add(data[position])
            list.addAll(data)
            list.removeAt(position)
            data = list
            notifyDataSetChanged()
        }

        return binding.root
    }

    fun getDeletedList() = newList
}