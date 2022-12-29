package com.roctik.bloodpressuremonitor.ui.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.roctik.bloodpressuremonitor.R
import com.roctik.bloodpressuremonitor.databinding.FragmentTrackBinding
import com.roctik.bloodpressuremonitor.domain.model.ItemAction
import com.roctik.bloodpressuremonitor.domain.model.Track
import com.roctik.bloodpressuremonitor.ui.listener.ItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class TrackFragment : Fragment(), ItemClickListener<Track> {

    private var _binding: FragmentTrackBinding? = null
    private val binding get() = _binding!!

    private val trackViewModel: TrackViewModel by viewModel()
    private lateinit var adapter: TrackAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTrackBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        initTrackRecycler()
        loadBarChart()

        binding.btnAdd.setOnClickListener {
            navigateToEditTrackFragment()
        }

        binding.fab.setOnClickListener {
            navigateToEditTrackFragment()
        }

    }

    private fun setupObserver() {
        trackViewModel.trackListLiveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.containerNoData.visibility = View.VISIBLE
                binding.dataContainer.visibility = View.GONE
            } else {
                binding.containerNoData.visibility = View.GONE
                binding.dataContainer.visibility = View.VISIBLE
                adapter.differ.submitList(it)
                initAverageData(it)
            }
        }
    }

    private fun initTrackRecycler() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = TrackAdapter(this)
        binding.rvTrack.layoutManager = layoutManager
        binding.rvTrack.setHasFixedSize(true)
        binding.rvTrack.adapter = adapter
    }

    private fun initAverageData(trackList: List<Track>) {

        val systolicAvg = trackList.map { it.systolic }.average()
        val diastolicAvg = trackList.map { it.diastolic }.average()
        val pulseAvg = trackList.map { it.pulse }.average()
        binding.txtSystolicValue.text = String.format("%.2f", systolicAvg)
        binding.txtDiastolicValue.text = String.format("%.2f", diastolicAvg)
        binding.txtPulseValue.text = String.format("%.2f", pulseAvg)
    }

    private fun navigateToEditTrackFragment() {
        val addTrackAction = TrackFragmentDirections.actionTrackFragmentToTrackEditFragment()
        findNavController().navigate(addTrackAction)
    }

    //demo data for chart
    private val titleList = arrayOf("time 1", "time 2", "time 3")
    private val values1 = arrayOf(50f, 15f, 25f, 36f)
    private val values2 = arrayOf(70f, 35f, 15f, 10f)
    private fun loadBarChart() {
        val barEntries1: MutableList<BarEntry> = ArrayList()
        for (i in titleList.indices) {
            barEntries1.add(BarEntry(i.toFloat(), values1[i]))
        }
        val barEntries2: MutableList<BarEntry> = ArrayList()
        for (i in titleList.indices) {
            barEntries2.add(BarEntry(i.toFloat(), values2[i]))
        }
        val dataSet1 = BarDataSet(barEntries1, "val 1")
        dataSet1.setColors(R.color.blue)
        dataSet1.setDrawValues(false)
        val dataSet2 = BarDataSet(barEntries2, "val 2")
        dataSet2.setColors(R.color.green)
        dataSet2.setDrawValues(false)
        val groupSpace = 0.15f
        val barSpace = 0.01f
        val barWidth = 0.1f
        val data = BarData(dataSet1, dataSet2)
        val vf: ValueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "" + value.toInt()
            }
        }
        data.setValueFormatter(vf)
        data.setValueTextSize(12f)
        data.isHighlightEnabled = false
        data.barWidth = barWidth

        val xAxis: XAxis = binding.chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.labelCount = titleList.size
        xAxis.axisMinimum = 0f
        xAxis.granularity = 1f
        xAxis.setCenterAxisLabels(true)
        xAxis.axisMaximum = titleList.size.toFloat()

        val legend: Legend = binding.chart.legend
        legend.isEnabled = false

        binding.chart.data = data
        binding.chart.groupBars(0f, groupSpace, barSpace)
        binding.chart.xAxis.valueFormatter = IndexAxisValueFormatter(titleList)
        binding.chart.description.isEnabled = false
        binding.chart.setDrawValueAboveBar(false)
        binding.chart.setTouchEnabled(false)
        binding.chart.animateY(1000)
        binding.chart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(item: Track, action: ItemAction) {

    }
}