package com.example.wikitest.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wikitest.R
import com.example.wikitest.databinding.FragementDetailedCaseBinding
import com.example.wikitest.databinding.FragmentMainCaseBinding
import com.example.wikitest.view.adapter.EntryAdapter
import com.example.wikitest.view.adapter.EntryClickLister
import com.example.wikitest.view.service.response.Entry
import com.example.wikitest.view.viewModel.MainViewModel
import com.example.wikitest.view.viewModel.MainViewModelFactory

class MainCaseFragement : Fragment(), EntryClickLister {


    private lateinit var binding: FragmentMainCaseBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var jobsAdapter: EntryAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bindingN = FragmentMainCaseBinding.bind(view)
        binding = bindingN
        initViewModel()
        mainViewModel.getEntry(null)
        observeData()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainCaseBinding.inflate(inflater, container, false)
        return binding.parentRoot
    }

    private fun observeData() {
        mainViewModel.mainEntryLiveData.observe(viewLifecycleOwner, Observer {
            Thread {
                activity?.runOnUiThread {
                    if (view != null) {
//                                view.visibility = View.GONE
                        initRecyclerView(it)
                    }
                }
            }.start()
        })
    }

    private fun initRecyclerView(list: List<Entry>) {
        jobsAdapter = context?.let {
            EntryAdapter(list, this, it)
        }!!
        binding.caseList.layoutManager = LinearLayoutManager(activity)
        binding.caseList.adapter = jobsAdapter
    }
    private fun initViewModel() {
        val viewModelFactory = MainViewModelFactory()
        mainViewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel::class.java)
    }

    override fun onClicked(entry: Entry) {
        val action = bundleOf("case" to entry.caseid)
        binding.caseList.findNavController().navigate(R.id.action_mainCaseFragement_to_caseDetailFragment2, action)
    }

}