package com.frogobox.research.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.frogobox.research.common.base.BaseBindFragment
import com.frogobox.research.databinding.FragmentMainBinding
import com.frogobox.research.ui.detail.DetailActivity
import com.frogobox.research.util.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseBindFragment<FragmentMainBinding>() {

    companion object {

        val TAG: String = MainFragment::class.java.simpleName

        const val EXTRA_FRAGMENT = "extra_fragment"

        fun newInstance(data: String? = null) = MainFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_FRAGMENT, data)
            }
        }
    }

    private val viewModel: MainViewModel by viewModels()


    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            // Call View Model Here
            viewModel.getData()
            Log.d(TAG, "View Model : ${viewModel::class.java.simpleName}")
        }
        // TODO : Add your code here

    }

    override fun initView() {
        super.initView()
        binding.apply {
            if (arguments != null) {
                tvMain2.text = arguments?.getString(EXTRA_FRAGMENT)
            }

            btnGoToDetail.setOnClickListener {
                (activity as MainActivity).startActivityResult.launch(Intent(requireActivity(), DetailActivity::class.java).apply {
                    putExtra(Constant.Extra.EXTRA_DATA, "Hello World From Fragment!!!")
                    putExtra(Constant.Extra.EXTRA_KEY_FRAGMENT, true)
                })
            }
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.apply {

        }
    }

}