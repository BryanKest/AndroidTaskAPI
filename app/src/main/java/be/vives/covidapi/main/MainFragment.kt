package be.vives.covidapi.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import be.vives.covidapi.R
import be.vives.covidapi.databinding.MainFragmentBinding
import be.vives.covidapi.model.Response

class MainFragment : Fragment() {


    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        val adapter = ResponseAdapter(ResponseClickListener {
            viewModel.onResponseClicked(it)
        })
        binding.rvResponses.adapter = adapter

        val manager = LinearLayoutManager(activity)
        binding.rvResponses.layoutManager = manager

        viewModel.covidBase.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.response!!)
            }
        })

        viewModel.response.observe(viewLifecycleOwner, Observer {
            it?.let {
                navigateToResponseDetail(it!!)
                viewModel.navigateToDetailFinished()
            }

        })

        binding.setLifecycleOwner(this)
        return binding.root
    }

    fun navigateToResponseDetail(response: Response) {
        requireView().findNavController().navigate(MainFragmentDirections.actionMainFragmentToResponseDetailFragment(response))
    }

}