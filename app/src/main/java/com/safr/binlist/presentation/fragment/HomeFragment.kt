package com.safr.binlist.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.safr.binlist.core.hide
import com.safr.binlist.core.hideKeyboard
import com.safr.binlist.core.show
import com.safr.binlist.data.network.model.ResponseDataBank
import com.safr.binlist.databinding.FragmentHomeBinding

import com.safr.binlist.domain.model.HistoryItem
import com.safr.binlist.presentation.adapter.HistoryNumberClickListener
import com.safr.binlist.presentation.adapter.HistoryAdapter
import com.safr.binlist.presentation.viewmodel.BinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val mBinding get() = _binding!!

    private val viewModel: BinViewModel by viewModels()

    private val histAdapter by lazy {
        HistoryAdapter(object : HistoryNumberClickListener {
            override fun onItemClick(item: HistoryItem) {
                mBinding.include.editTextNumber.setText(item.bin)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = mBinding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start(45717360)
        mBinding.recyclerView2.adapter = histAdapter
        loader()
        searchEdit()
        error()
        startObserveHistory()
    }

    private fun error() = mBinding.run {
        lifecycleScope.launchWhenStarted {
            viewModel.isError.collect {
                if (it){
                    Log.d("lol", " viewModel.isError. ${it} ")
                    include.scheme.hide()
                    include.type.hide()
                    include.bankName.hide()
                    include.notFound.show()
                    include2.root.hide()
                }
            }
        }
    }

    private fun loader() {

        lifecycleScope.launchWhenStarted {
            viewModel.isDataLoading.collect {
                mBinding.progressBar.isVisible = it
            }
        }
    }
    private fun startObserveHistory(){
        lifecycleScope.launchWhenStarted {
            viewModel.readAllBin.collect {
                setResultHistory(it)
            }
        }
    }

    private fun setResultHistory(result: List<HistoryItem>) {
        histAdapter.submitList(result.asReversed())
    }




    private fun start(d: Int) = mBinding.run {
        lifecycleScope.launchWhenStarted {
            viewModel.start(d)
            viewModel.detailListDrink.collect { it ->
                include.scheme.show()
                include.type.show()
                include.bankName.show()
                include.notFound.hide()
                if (it?.bank?.name != null) {
                    include.scheme.text = it.scheme?.uppercase()
                    include.type.text = it.type?.uppercase()
                    include.bankName.text = it.bank.name
                }
                else{
                    include.bankName.apply {
                        text ="Bank not Found"
                    }

                }

                includer2(it)

            }
        }
    }

    private fun FragmentHomeBinding.includer2(resp: ResponseDataBank?) {
        if (resp?.bank?.name != null) {
            include2.root.show()
            include2.country.text = resp.country?.name
            include2.city.text = resp.bank.city
            include2.bankName.text = resp.bank.name
            include2.phone.text = resp.bank.phone

            include2.textView11.setOnClickListener {
                resp.country?.let { letter ->
                    letter.latitude?.let { it1 -> letter.longitude?.let { it2 -> openMap(latitude = it1, longitude = it2) } }
                }

            }
            include2.urlSite.text = resp.bank.url
        } else {
            include2.root.hide()
        }
    }

    private fun searchEdit() {

        mBinding.include.editTextNumber.setOnKeyListener { view, keyCode, pKeyEvent
            ->
            if (pKeyEvent.action == KeyEvent.ACTION_DOWN &&
                keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                mBinding.include.editTextNumber.clearFocus()
                mBinding.include.editTextNumber.isCursorVisible = false
                this@HomeFragment.hideKeyboard()
                val d = mBinding.include.editTextNumber.text.trim().toString().toInt()
                start(d)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }


        mBinding.include.imageView.setOnClickListener {
           if (mBinding.include.editTextNumber.text.trim().toString() != "") {
               it.isClickable = true
               val d = mBinding.include.editTextNumber.text.trim().toString().toInt()
               Log.d("lol", " imageView.setOnClickListener ${d} ")
               start(d)
               this@HomeFragment.hideKeyboard()
           }

        }
    }

    private fun openMap(latitude : String, longitude : String ) {
        val uri = "geo:$latitude,$longitude"
        val mapIntent =  Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(mapIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}