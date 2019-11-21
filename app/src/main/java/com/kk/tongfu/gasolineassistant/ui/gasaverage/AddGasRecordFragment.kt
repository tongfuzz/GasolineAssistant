package com.kk.tongfu.gasolineassistant.ui.gasaverage


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.kk.tongfu.gasolineassistant.R
import com.kk.tongfu.gasolineassistant.databinding.FragmentAddGasRecordBinding
import com.kk.tongfu.gasolineassistant.service.GasRecord
import kotlinx.android.synthetic.main.fragment_add_gas_record.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class AddGasRecordFragment : Fragment() {

    private lateinit var fragmentAddGasRecordBinding: FragmentAddGasRecordBinding
    private lateinit var addGasRecordViewModel: AddGasRecordViewModel
    private var date: Long = System.currentTimeMillis()
    val args:AddGasRecordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("onCreateView-AddGasRecordFragment")
        addGasRecordViewModel = ViewModelProviders.of(this).get(AddGasRecordViewModel::class.java)
        fragmentAddGasRecordBinding =
            FragmentAddGasRecordBinding.inflate(inflater, container, false)
        return fragmentAddGasRecordBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("onViewCreated-AddGasRecordFragment")

        btn_choose_date.setOnClickListener {
            var builder: MaterialDatePicker.Builder<Long> = MaterialDatePicker.Builder.datePicker()

            //builder.setSelection(todayInUtcMilliseconds)

            val calendarConstraints = CalendarConstraints.Builder()
            //calendarConstraints.setOpenAt(todayInUtcMilliseconds)

            context?.apply {
                val theme = resolveOrThrow(this, R.attr.materialCalendarTheme)
                builder.setTheme(theme)
            }

            builder.setCalendarConstraints(calendarConstraints.build())
            builder.setTitleText(R.string.please_choose_date)
            builder.setSelection(date)
            val datePicker = builder.build()

            longToDateString(System.currentTimeMillis())
            datePicker.addOnPositiveButtonClickListener {
                datePicker.selection?.apply {
                    btn_choose_date.text = longToDateString(this)
                    date = this
                }
            }
            fragmentManager?.apply {
                datePicker.show(this, datePicker.toString())
            }
        }

        args.gasRecord?.apply {
            btn_choose_date.text = longToDateString(this.date)
            this@AddGasRecordFragment.date=date
            et_cost.setText(this.cost.toString())
            text_input_cost.isEndIconVisible=false
            et_distance.setText(this.currentDistance.toString())
            text_input_distance.isEndIconVisible=false
            et_price.setText(this.price.toString())
            text_input_price.isEndIconVisible=false
            et_rest_amount.setText(this.restAmount.toString())
            text_input_rest.isEndIconVisible=false
        }

        btn_commit.setOnClickListener {
            val editTexts = listOf(et_price, et_cost, et_rest_amount, et_distance)

            editTexts.forEach {
                it.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        when (it.id) {
                            R.id.et_price -> {
                                text_input_price.error = null
                            }
                            R.id.et_cost -> {
                                text_input_cost.error = null
                            }
                            R.id.et_distance -> {
                                text_input_distance.error = null
                            }
                            R.id.et_rest_amount -> {
                                text_input_rest.error = null
                            }
                        }
                        println("onTextChanged")
                    }

                })
            }

            editTexts.forEach {
                if (it.text.isNullOrBlank()) {
                    when (it.id) {
                        R.id.et_price -> {
                            text_input_price.error =
                                resources.getString(R.string.please_input_price)
                        }
                        R.id.et_cost -> {
                            text_input_cost.error = resources.getString(R.string.please_input_cost)
                        }
                        R.id.et_distance -> {
                            text_input_distance.error =
                                resources.getString(R.string.please_input_distance)
                        }
                        R.id.et_rest_amount -> {
                            text_input_rest.error =
                                resources.getString(R.string.please_input_rest_amount)
                        }
                    }
                    it.requestFocus()
                    return@setOnClickListener
                }

                if (it.text.toString().toFloat() <= 0) {
                    when (it.id) {
                        R.id.et_price -> {
                            text_input_price.error =
                                resources.getString(R.string.please_input_correct_price)
                        }
                        R.id.et_cost -> {
                            it.error =
                                resources.getString(R.string.please_input_correct_cost)
                        }
                        R.id.et_rest_amount -> {
                            it.error =
                                resources.getString(R.string.please_input_correct_rest_amount)
                        }
                        R.id.et_distance -> {
                            it.error =
                                resources.getString(R.string.please_input_correct_distance)
                        }
                    }
                    it.requestFocus()
                    it.setSelection(it.text.toString().length)
                    return@setOnClickListener
                }
            }
            val isAllInformation = editTexts.all {
                !(it.text.isNullOrBlank() || it.text.toString().toFloat()<= 0)
            }

            if (isAllInformation) {
                addGasRecordViewModel.addGasRecord(args.gasRecord?.id?:0,
                    date,
                    et_price.text.toString().toDouble(),
                    et_cost.text.toString().toDouble(),
                    et_rest_amount.text.toString().toInt(),
                    et_distance.text.toString().toInt()
                )

            }
        }

        addGasRecordViewModel.navigationToRecordAction.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.action_nav_add_to_nav_record)
            }
        })
    }

    private fun resolveOrThrow(context: Context, @AttrRes attributeResId: Int): Int {
        val typedValue = TypedValue()
        if (context.theme.resolveAttribute(attributeResId, typedValue, true)) {
            return typedValue.data
        }
        throw IllegalArgumentException(context.resources.getResourceName(attributeResId))
    }

    private fun longToDateString(timeMilliseconds: Long): String {
        return SimpleDateFormat("yyyy/MM/dd").format(timeMilliseconds)
    }


}
