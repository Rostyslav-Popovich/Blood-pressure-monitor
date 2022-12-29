package com.roctik.bloodpressuremonitor.ui.track

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.roctik.bloodpressuremonitor.databinding.FragmentTrackEditBinding
import com.roctik.bloodpressuremonitor.domain.model.Track
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TrackEditFragment : Fragment() {

    private var _binding: FragmentTrackEditBinding? = null
    private val binding get() = _binding!!

    private val trackViewModel: TrackViewModel by viewModel()

    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    private val hour = calendar.get(Calendar.HOUR_OF_DAY)
    private val minute = calendar.get(Calendar.MINUTE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTrackEditBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMeasurementPicker()
        initCurrentDate()

        binding.btnSave.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val description = binding.edtDescription.text.toString()
            val date = calendar.time.time
            val systolic = binding.systolicPicker.value
            val diastolic = binding.diastolicPicker.value
            val pulse = binding.pulsePicker.value

            val track = Track(
                title = title,
                description = description,
                date = date,
                systolic = systolic,
                diastolic = diastolic,
                pulse = pulse
            )

            trackViewModel.addTrack(track)
            findNavController().navigateUp()
        }
    }

    private fun initCurrentDate() {
        binding.txtDate.text =
            String.format("%td.%tm.%tY %tH:%tM", calendar, calendar, calendar, calendar, calendar)

        val timePicker = TimePickerDialog(context, { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            binding.txtDate.text =
                String.format(
                    "%td.%tm.%tY %tH:%tM",
                    calendar,
                    calendar,
                    calendar,
                    calendar,
                    calendar
                )
        }, hour, minute, true)

        val datePickerDialog = context?.let { context ->
            DatePickerDialog(context, { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                timePicker.show()
            }, year, month, day)
        }

        datePickerDialog?.datePicker?.maxDate = Calendar.getInstance().time.time

        binding.txtDate.setOnClickListener {
            datePickerDialog?.show()
        }

    }

    private fun initMeasurementPicker() {
        binding.systolicPicker.minValue = 0
        binding.systolicPicker.maxValue = 250
        binding.systolicPicker.value = 120

        binding.diastolicPicker.minValue = 0
        binding.diastolicPicker.maxValue = 150
        binding.diastolicPicker.value = 80

        binding.pulsePicker.minValue = 0
        binding.pulsePicker.maxValue = 200
        binding.pulsePicker.value = 80
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}