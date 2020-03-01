package com.gahee.myprography.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gahee.myprography.CallsViewModel
import com.gahee.myprography.R


class CallsFragment : Fragment() {

    companion object {
        fun newInstance() = CallsFragment()
    }

    private lateinit var viewModel: CallsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calls_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CallsViewModel::class.java)

    }

}
