package com.kk.tongfu.gasolineassistant.ui.gasaverage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kk.tongfu.gasolineassistant.R
import com.kk.tongfu.gasolineassistant.databinding.FragmentGasAverageBinding
import kotlinx.android.synthetic.main.fragment_gas_average.*

class GasAverageFragment : Fragment() {

    private lateinit var homeViewModel: GasAverageViewModel
    private lateinit var gasAverageBinding: FragmentGasAverageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("onCreateView-GasAverageFragment")
        homeViewModel =
            ViewModelProviders.of(this).get(GasAverageViewModel::class.java)
        gasAverageBinding = FragmentGasAverageBinding.inflate(inflater, container, false)
        return gasAverageBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated-GasAverageFragment")
        gasAverageBinding.apply {
            viewModel = homeViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        homeViewModel.gasInfos?.observe(this, Observer {
            homeViewModel.calculate()
        })

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_nav_average_to_nav_add)
        }
    }
}