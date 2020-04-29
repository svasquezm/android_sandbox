package cl.svasquezm.sandbox.features.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import cl.svasquezm.sandbox.R

/**
 * A simple [Fragment] subclass.
 */
class MotionLayoutFragment2 : Fragment() {

    private val recyclerView by lazy { view!!.findViewById<RecyclerView>(R.id.recyclerView) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_motion_layout2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val elements = (1 until 25).map { "Element $it" }
        recyclerView.adapter = DummyAdapter(elements)
    }

    inner class DummyAdapter(private val stringList: List<String>) : RecyclerView.Adapter<DummyViewHolder>() {
        override fun getItemCount() = stringList.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DummyViewHolder(parent)

        override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
            (holder.itemView as TextView).text = stringList[position]
        }

    }

    inner class DummyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(TextView(parent.context))
}
