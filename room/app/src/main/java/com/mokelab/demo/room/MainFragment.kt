package com.mokelab.demo.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.mokelab.demo.room.databinding.MainFragmentBinding
import com.mokelab.demo.room.model.User
import com.mokelab.demo.room.model.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment: Fragment() {
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = MainFragmentBinding.inflate(inflater, container, false)
        this.binding.addButton.setOnClickListener {
            addItem()
        }
        return this.binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val db = UserDatabase.getInstance(requireContext().applicationContext)
        db.userDAO().getAll().observe(this, Observer {
            it.forEach { user ->
                println("name=${user.name}")
            }
        })
    }

    private fun addItem() {
        val name = this.binding.usernameEdit.text.toString()

        viewLifecycleOwner.lifecycleScope.launch {
            val db = UserDatabase.getInstance(requireContext().applicationContext)
            withContext(Dispatchers.IO) {
                db.userDAO().create(User(name = name))
            }
            binding.usernameEdit.setText("")
        }
    }
}