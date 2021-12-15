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
import com.example.wikitest.view.adapter.EntryAdapter
import com.example.wikitest.view.adapter.EntryClickLister
import com.example.wikitest.view.service.response.Entry
import com.example.wikitest.view.viewModel.MainViewModel
import com.example.wikitest.view.viewModel.MainViewModelFactory

class CaseDetailFragment : Fragment() , EntryClickLister {

    private lateinit var binding: FragementDetailedCaseBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var jobsAdapter: EntryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragementDetailedCaseBinding.inflate(inflater, container, false)
        return binding.parentRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var entryId = arguments?.get("case").toString()
        val bindingN = FragementDetailedCaseBinding.bind(view)
        binding = bindingN
        initViewModel()
        mainViewModel.getDetails(entryId, null)
        observeData()
    }

    private fun observeData() {
        mainViewModel.mainEntryDetailLiveData.observe(viewLifecycleOwner, Observer {
            Thread {
                activity?.runOnUiThread {
                    if (view != null) {
//                                view.visibility = View.GONE
                        it[0].answers?.let { it1 -> initRecyclerView(it1) }
                        binding.textView.text = it[0].text

                        if (it.isNotEmpty() && it[0].image != null) {
                            Glide.with(binding.imageView)
                                .load(it[0].image)
                                .into(binding.imageView)
                        }
                    }
                }
            }.start()

        })
    }

    private fun initRecyclerView(list: List<Entry>) {
        jobsAdapter = context?.let {
            EntryAdapter(list, this, it)
        }!!
        binding.ansList.layoutManager = LinearLayoutManager(activity)
        binding.ansList.adapter = jobsAdapter
    }
    private fun initViewModel() {
        val viewModelFactory = MainViewModelFactory()
        mainViewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel::class.java)
    }

    override fun onClicked(entry: Entry) {
        val action = bundleOf("case" to entry.caseid)
        binding.ansList.findNavController().navigate(R.id.action_caseDetailFragment2_self, action)
    }
}