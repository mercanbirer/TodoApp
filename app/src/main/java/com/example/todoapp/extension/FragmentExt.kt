package com.example.todoapp.extension

import android.app.Dialog
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.todoapp.R
import com.example.todoapp.databinding.DialogTaskBinding


fun Fragment.makeToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

data class PinDialog(val binding: DialogTaskBinding, val dialog: Dialog)

fun Fragment.taskDialog(): PinDialog {
    val dialog = Dialog(requireContext(), R.style.DialogTheme)
    val binding = DialogTaskBinding.inflate(LayoutInflater.from(requireContext()))
    dialog.window?.setWindowAnimations(R.style.DialogAnimation)
    dialog.setContentView(binding.root)
    dialog.show()
    dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    return PinDialog(binding, dialog)
}