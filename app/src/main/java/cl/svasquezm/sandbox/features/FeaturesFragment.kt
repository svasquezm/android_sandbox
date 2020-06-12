package cl.svasquezm.sandbox.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.svasquezm.sandbox.R
import cl.svasquezm.sandbox.databinding.FragmentFeaturesBinding

/**
 * This Fragment uses "View Binding" Android Jetpack component.
 * See build.gradle which contains
 * viewBinding {
 *      enabled = true
 * }
 * And fragment_data_binding.xml for more details
 */
class FeaturesFragment : Fragment() {

    // Inflates and retrieve View's root reference which contains
    // All children views
    lateinit var _binding: FragmentFeaturesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeaturesBinding.inflate(layoutInflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create Features List
        val featuresList = listOf(
            FeatureDataClass(
                R.string.feature_data_binding,
                R.string.feature_data_binding_explanation,
                FeatureType.DATA_BINDING
            ),
            FeatureDataClass(
                R.string.feature_ui,
                R.string.feature_ui_explanation,
                FeatureType.UI
            ),
            FeatureDataClass(
                R.string.feature_ui_constraint1,
                R.string.feature_ui_constraint1_explanation,
                FeatureType.UI_CONSTRAINT1
            ),
            FeatureDataClass(
                R.string.feature_ui_motion,
                R.string.feature_ui_motion_explanation,
                FeatureType.UI_MOTION
            )
        )

        // Access root's Recyclerview
        _binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        _binding.recyclerView.adapter = FeaturesAdapter(featuresList) { itemClicked ->
            findNavController().run {
                when(itemClicked){
                    FeatureType.UI_MOTION -> navigate(
                        R.id.action_featuresFragment_to_motionLayoutFeaturesFragment
                    )
                }
            }
        }
    }

    /**
     * Feature type
     */
    enum class FeatureType(id: Int){
        DATA_BINDING(0),
        UI(1),
        UI_CONSTRAINT1(2),
        UI_MOTION(3),

        MOTION_SWIPE(30),
        MOTION_FLIP(31)
    }

    /**
     * Feature data holder class
     */
    data class FeatureDataClass(@StringRes val featureNameRes: Int,
                                @StringRes val featureDescriptionRes: Int,
                                val featureType: FeatureType
    )

    /**
     * Adapter which shows
     */
    class FeaturesAdapter(private val features: List<FeatureDataClass>,
                                private val onFeatureClicked: (FeatureType) -> Unit) : RecyclerView.Adapter<FeatureViewHolder>(){

        override fun getItemCount() = features.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_feature, parent, false)
            return FeatureViewHolder(view)
        }

        override fun onBindViewHolder(holder: FeatureViewHolder, position: Int)
                = holder.bind(features[position], onFeatureClicked)
    }

    class FeatureViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val title = view.findViewById<TextView>(R.id.title)
        private val description = view.findViewById<TextView>(R.id.description)

        fun bind(item: FeatureDataClass, onFeatureClicked: (FeatureType) -> Unit){
            title.text = itemView.context.getString(item.featureNameRes)
            description.text = itemView.context.getString(item.featureDescriptionRes)

            itemView.setOnClickListener {
                onFeatureClicked(item.featureType)
            }
        }
    }
}
