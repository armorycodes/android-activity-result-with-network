package com.frogobox.research.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.frogobox.research.common.base.BaseBindActivity
import com.frogobox.research.databinding.ActivityMainBinding
import com.frogobox.research.ui.detail.DetailActivity
import com.frogobox.research.util.Constant
import com.frogobox.research.util.Constant.ResultCode.RESULT_CODE_FROM_DETAIL_TO_ACTIVITY
import com.frogobox.research.util.Constant.ResultCode.RESULT_CODE_FROM_DETAIL_TO_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindActivity<ActivityMainBinding>() {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    private val viewModel: MainViewModel by viewModels()

    var startActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_CODE_FROM_DETAIL_TO_ACTIVITY) {
            // There are no request codes
            val data: Intent? = result.data
            val text = data?.getStringExtra(Constant.Extra.RESULT_EXTRA_DATA)

            Log.d(TAG, "Result : ${data?.getStringExtra(Constant.Extra.EXTRA_DATA)}")
            binding.tvMain.text = text

        } else if (result.resultCode == RESULT_CODE_FROM_DETAIL_TO_FRAGMENT) {
            val data: Intent? = result.data
            val text = data?.getStringExtra(Constant.Extra.RESULT_EXTRA_DATA)

            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainerView.id, MainFragment.newInstance(text))
                .commit()
        }
    }

    override fun initBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            // Call View Model Here
            Log.d(TAG, "View Model : ${viewModel::class.java.simpleName}")
        }
        // TODO : Add your code here

    }

    override fun initView() {
        super.initView()
        binding.apply {

            supportFragmentManager.beginTransaction()
                .replace(fragmentContainerView.id, MainFragment.newInstance())
                .commit()

            btnGoToDetail.setOnClickListener {
                startActivityResult.launch(Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(Constant.Extra.EXTRA_DATA, "Hello World From Activity !!!")
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