package com.roctik.bloodpressuremonitor.ui.track

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.roctik.bloodpressuremonitor.databinding.ItemTrackBinding
import com.roctik.bloodpressuremonitor.domain.model.ItemAction
import com.roctik.bloodpressuremonitor.domain.model.Track
import com.roctik.bloodpressuremonitor.ui.listener.ItemClickListener
import com.roctik.bloodpressuremonitor.util.dateTimeFromString

class TrackAdapter(private var trackListener: ItemClickListener<Track>) :
    RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrackViewHolder {
        return TrackViewHolder(
            ItemTrackBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    inner class TrackViewHolder(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Track) {
            binding.itemDate.text = dateTimeFromString(item.date)
            binding.itemTitle.text = item.title

            binding.txtSystolicValue.text = item.systolic.toString()
            binding.txtDiastolicValue.text = item.diastolic.toString()
            binding.txtPulseValue.text = item.pulse.toString()

            binding.cardTrack.setOnClickListener {
                trackListener.onItemClick(item, ItemAction.SELECT)
            }
        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(
            oldItem: Track,
            newItem: Track
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Track,
            newItem: Track
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}