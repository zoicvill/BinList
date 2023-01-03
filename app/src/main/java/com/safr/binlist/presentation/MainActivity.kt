package com.safr.binlist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.safr.binlist.R
import com.safr.binlist.presentation.viewmodel.BinViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: BinViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ssss()
    }


   fun ssss(){
       lifecycleScope.launchWhenStarted{
           viewModel.start( 45717360)
       }

    }
}