package com.kk.tongfu.gasolineassistant.ui.gasrecord

import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kk.tongfu.gasolineassistant.R
import com.kk.tongfu.gasolineassistant.databinding.FragmentGasRecordBinding
import com.kk.tongfu.gasolineassistant.listener.ItemClickListener
import com.kk.tongfu.gasolineassistant.service.GasRecord
import com.kk.tongfu.gasolineassistant.ui.gasrecord.adapter.GasRecordAdapter
import kotlinx.android.synthetic.main.fragment_gas_record.*

class GasRecordFragment : Fragment(), ItemClickListener<GasRecord> {

    private lateinit var gasRecordViewModel: GasRecordViewModel
    private lateinit var gasRecordBinding: FragmentGasRecordBinding
    private var adapter = GasRecordAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("onCreateView-GasRecordFragment")
        gasRecordViewModel =
            ViewModelProviders.of(this).get(GasRecordViewModel::class.java)
        gasRecordBinding = FragmentGasRecordBinding.inflate(inflater, container, false)
        return gasRecordBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("onViewCreated-GasRecordFragment")
        gasRecordBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = gasRecordViewModel
            recycler_view.adapter = adapter
        }

        gasRecordViewModel.gasInfos?.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onClick(view: View, item: GasRecord) {
        context?.let {
            val popupMenu = PopupMenu(it, view)
            popupMenu.menuInflater.inflate(R.menu.menu_edit, popupMenu.menu)
            if (popupMenu.menu is MenuBuilder) {
                val menuBuilder = popupMenu.menu as MenuBuilder
                menuBuilder.setOptionalIconsVisible(true)
                for (item in menuBuilder.visibleItems) {
                    val iconMargin = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        8.toFloat(),
                        it?.resources?.displayMetrics
                    ).toInt()

                    if (item.icon != null) {
                        item.icon = InsetDrawable(item.icon, iconMargin, 0, iconMargin, 0)
                    }
                }
            }

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_edit -> {
                        val actionNavRecordToNavAdd =
                            GasRecordFragmentDirections.actionNavRecordToNavAdd(item)
                        findNavController().navigate(actionNavRecordToNavAdd)
                    }
                    R.id.action_delete -> {
                        gasRecordViewModel.delete(item)
                    }
                }
                true
            }
            popupMenu.show()
        }

    }
}