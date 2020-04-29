package cl.svasquezm.sandbox.features.layouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.svasquezm.sandbox.R
import cl.svasquezm.sandbox.databinding.FragmentConstraintLayout2Binding

/**
 * A simple [Fragment] subclass.
 */
class ConstraintLayoutAnimation1Fragment : Fragment(),
    ItemSelectableLayout {
    private lateinit var viewBinding: FragmentConstraintLayout2Binding
    private var lastPositionSelected: Int = 0
    private var lastScrollY: Int = 0
    private val adapterList = mutableListOf<SimpleItem>()
    private val originalList = (0 until 100).map {
        SimpleItem(
            "Item $it",
            "description here"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentConstraintLayout2Binding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterList.addAll(originalList)
        viewBinding.recyclerView.adapter = ItemAdapter(adapterList) {
            onItemSelect(it)
        }

        viewBinding.backButton.setOnClickListener {
            onRestoreItemSelect(lastPositionSelected)
        }
    }

    override fun onItemSelect(position: Int) {
        lastPositionSelected = position

        // Retrieve last size
        val lastSize = adapterList.size - 1

        lastScrollY = viewBinding.recyclerView.scrollY

        // Remove all items except in position
        (lastSize downTo 0).forEach {
            if(it != position){
                adapterList.removeAt(it)
                viewBinding.recyclerView.adapter?.notifyItemRemoved(it)
            }
        }
    }

    override fun onRestoreItemSelect(position: Int) {
        adapterList.clear()
        adapterList.addAll(originalList.filterIndexed { index, simpleItem -> index != position })
        viewBinding.recyclerView.adapter?.notifyItemMoved(0, lastPositionSelected)
    }

    data class SimpleItem(val topText: String, val middleText: String)
    inner class ItemAdapter(private val list: List<SimpleItem>, private val onItemSelected: (Int) -> Unit) : RecyclerView.Adapter<ItemViewHolder>() {

        override fun getItemCount() = list.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cell_constraintlayout1, parent, false)
            return ItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                onItemSelected(position)
            }

            holder.top.text = list[position].topText
            holder.middle.text = list[position].middleText
        }
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val top = view.findViewById<TextView>(R.id.topText)
        val middle = view.findViewById<TextView>(R.id.middleText)
    }
}

interface ItemSelectableLayout {
    fun onItemSelect(position: Int)
    fun onRestoreItemSelect(position: Int)
}
