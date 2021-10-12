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
    private var data: ArrayList<Todo>,
    private val listener: DataListener
) : BaseAdapter<Todo>(context, data) {
    private var dataListener: DataListener = listener
    private val list = arrayListOf<Todo>()
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
            list.add(getItem(position))
            data.removeAt(position)
            dataListener.dataListener(list)
            notifyDataSetChanged()
        }

        return binding.root
    }

    fun getDeletedList() = data

    interface DataListener {
        fun dataListener(data: ArrayList<Todo>)
    }
}