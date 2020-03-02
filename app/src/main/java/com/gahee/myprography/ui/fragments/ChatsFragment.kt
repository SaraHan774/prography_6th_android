package com.gahee.myprography.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gahee.myprography.viewmodels.ChatsViewModel
import com.gahee.myprography.FilmsAdapter
import com.gahee.myprography.R
import com.gahee.myprography.viewmodels.NetworkState
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

        viewModel.fetchFilmsData()

        setUpRecyclerView()

        observeViewModel()
    }


    private fun observeViewModel(){

        viewModel.filmsLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Observed : $it")
            //Using List Adapter's submitList method.
            filmsAdapter.submitList(it)
        })

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            handleNetworkState(it)
        })
    }

    private fun handleNetworkState(networkState: NetworkState){
        when (networkState) {
            NetworkState.ERROR -> {
                Toast.makeText(context, "Network Error. Please try again.", Toast.LENGTH_LONG).show()
                main_progress_bar.visibility = View.GONE

            }
            NetworkState.LOADING -> {
                main_progress_bar.visibility = View.VISIBLE

            }
            NetworkState.DONE -> {
                main_progress_bar.visibility = View.GONE
            }
        }
    }

    private fun setUpRecyclerView(){
        recycler_view_films.apply {
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

}
