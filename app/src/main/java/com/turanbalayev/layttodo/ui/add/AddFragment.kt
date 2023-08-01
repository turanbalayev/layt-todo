package com.turanbalayev.layttodo.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.turanbalayev.layttodo.R
import com.turanbalayev.layttodo.data.models.Priority
import com.turanbalayev.layttodo.data.models.TodoData
import com.turanbalayev.layttodo.data.viewmodel.TodoViewModel
import com.turanbalayev.layttodo.databinding.FragmentAddBinding


class AddFragment : Fragment() {
    private val todoViewModel: TodoViewModel by viewModels()
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDb()
        }


        return super.onOptionsItemSelected(item)

    }

    private fun insertDataToDb() {
        val title = binding.titleEt.text.toString()
        val priority = binding.prioritiesSpinner.selectedItem.toString()
        val description = binding.descriptionEt.text.toString()

        val validation = verifyDataFromUser(title, description)
        if (validation) {
            val newTodoData = TodoData(0,title,parsePriority(priority),description)
            todoViewModel.insertData(newTodoData)
            Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
            val action = AddFragmentDirections.actionAddFragmentToListFragment()
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(),"Please fill out all fields!",Toast.LENGTH_LONG).show()
        }
    }


    private fun verifyDataFromUser(title: String, description: String): Boolean {
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
        } else {
            !(title.isEmpty() || description.isEmpty())
        }
    }

    private fun parsePriority (priority: String) : Priority {
        return when(priority){
            "High Priority" -> Priority.HIGH
            "Medium Priority" -> Priority.MEDIUM
            "Low Priority" -> Priority.LOW
            else -> Priority.LOW

        }
    }











}