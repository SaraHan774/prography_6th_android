package com.gahee.myprography.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gahee.myprography.ChatsViewModel
import com.gahee.myprography.FilmsAdapter
import com.gahee.myprography.R
import kotlinx.android.synthetic.main.chats_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChatsFragment : Fragment() {

    companion object {
        private const val TAG = "ChatsFragment"
        fun newInstance() = ChatsFragment()
    }

    private lateinit var viewModel: ChatsViewModel
    private val filmsAdapter: FilmsAdapter =
        FilmsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chats_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChatsViewModel::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.fetchFilmsData()
        }

        setUpRecyclerView()

        viewModel.filmsLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Observed : $it")
            filmsAdapter.films = it
        })


    }

    private fun setUpRecyclerView(){
        recycler_view_films.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

}
