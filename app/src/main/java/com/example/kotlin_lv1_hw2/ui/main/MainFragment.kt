package com.example.kotlin_lv1_hw2.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_lv1_hw2.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private val gifAdapter = GifAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = gifAdapter
        }

        val stub = view.findViewById<TextView>(R.id.stub)

        viewLifecycleOwner.lifecycleScope.launch {
            Log.d("PAGING", "MF: OVG")

            stub.isVisible = true
            stub.text = "Pending"
            stub.setOnClickListener(null)

            try {

                stub.isVisible = false
                withContext(Dispatchers.IO) {
                    Log.d("PAGING", "Submitting")
                    val gifs = viewModel.getGifs()
                    viewModel.getGifs().collectLatest { data ->
                        gifAdapter.submitData(data)
                    }
                }


            } catch (error: Throwable) {
                Log.d("PAGING", "Catch: " + error.message)

                stub.isVisible = true
                stub.text = "Error: ${error.message}"
                error.printStackTrace()
                stub.setOnClickListener {
                    // retry
                }
            }
        }
    }


    companion object {
        fun newInstance() = MainFragment()
    }
}