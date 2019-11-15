package com.kk.tongfu.gasolineassistant.ui.gasaverage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kk.tongfu.gasolineassistant.R
import com.kk.tongfu.gasolineassistant.databinding.FragmentGasAverageBinding

class GasAverageFragment : Fragment() {

    private lateinit var homeViewModel: GasAverageViewModel
    private lateinit var gasAverageBinding: FragmentGasAverageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(GasAverageViewModel::class.java)
        gasAverageBinding = FragmentGasAverageBinding.inflate(inflater, container, false)
        return gasAverageBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}