package com.akadatsky.gif4work.presentation

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.akadatsky.gif4work.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by inject()
    private lateinit var adapter: GifAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(searchView.text.toString())
            }
            true
        }

        val layoutManager = LinearLayoutManager(this)
        gifList.layoutManager = layoutManager
        adapter = GifAdapter(this)
        gifList.adapter = adapter
        gifList.setRecyclerListener { holder ->
            val gifViewHolder: GifViewHolder = holder as GifViewHolder
            Glide.with(this@MainActivity).clear(gifViewHolder.gifView)
        }
    }

    private fun performSearch(searchText: String) {
        if (searchText.isBlank()) {
            toast(R.string.search_text_empty)
            return
        }
        viewModel.performSearch(searchText).observe(this, Observer {
            adapter.results = it
            adapter.notifyDataSetChanged()
        })
    }

}