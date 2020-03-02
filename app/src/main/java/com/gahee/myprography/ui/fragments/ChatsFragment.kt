package com.gahee.myprography.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gahee.myprography.viewmodels.ChatsViewModel
import com.gahee.myprography.adapters.FilmsAdapter
import com.gahee.myprography.R
import com.gahee.myprography.viewmodels.NetworkState
import kotlinx.android.synthetic.main.chats_fragment.*


@Suppress("DEPRECATION")
class ChatsFragment : Fragment() {

    companion object {
        private const val TAG = "ChatsFragment"
    }

    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private lateinit var viewModel: ChatsViewModel
    private val filmsAdapter: FilmsAdapter =
        FilmsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chats_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChatsViewModel::class.java)

        swipeRefreshLayout = swipe_refresh_layout

        viewModel.fetchFilmsData()
        setUpRecyclerView()
        observeViewModel()

        swipeRefreshLayout.setOnRefreshListener {
            refreshListContent()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.swipe_refresh_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_refresh){
           refreshListContent()
        }
        return true
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
                showErrorMessage(networkState)
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

    private fun showErrorMessage(networkState: NetworkState){
        if(networkState == NetworkState.ERROR){
            val errorMsg = Toast.makeText(context, getString(R.string.error_msg), Toast.LENGTH_LONG)
            errorMsg.setGravity(Gravity.CENTER, 0, 0)
            errorMsg.show()
        }
    }

    private fun refreshListContent(){
        viewModel.fetchFilmsData()
        swipeRefreshLayout.isRefreshing = false
    }

}
