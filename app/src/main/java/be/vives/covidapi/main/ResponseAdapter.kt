package be.vives.covidapi.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import be.vives.covidapi.databinding.DetailCovidInListBinding
import be.vives.covidapi.model.Response

class ResponseAdapter(val clickListener: ResponseClickListener): ListAdapter<Response, ResponseAdapter.ViewHolder>(ToDoDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: DetailCovidInListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(
            response: Response,
            clickListener: ResponseClickListener
        ) {
            binding.response = response
            binding.clickListener = clickListener

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DetailCovidInListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
class ToDoDiffCallback : DiffUtil.ItemCallback<Response>() {

    override fun areItemsTheSame(oldItem: Response, newItem: Response): Boolean {
        return oldItem.day == newItem.day
    }

    override fun areContentsTheSame(oldItem: Response, newItem: Response): Boolean {
        return oldItem == newItem
    }
}

class ResponseClickListener(val clickListener: (response: Response) -> Unit) {
    fun onClick(response: Response) = clickListener(response)
}