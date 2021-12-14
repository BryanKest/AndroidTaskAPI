package be.vives.covidapi.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import be.vives.covidapi.R
import be.vives.covidapi.databinding.ResponseDetailFragmentBinding

class ResponseDetailFragment : Fragment() {

    private lateinit var viewModel: ResponseDetailViewModel
    private lateinit var binding: ResponseDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.response_detail_fragment, container, false)

        val response = ResponseDetailFragmentArgs.fromBundle(requireArguments()).response
        val fact = ResponseDetailViewModelFactory(response)
        viewModel = ViewModelProvider(this, fact).get(ResponseDetailViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

}