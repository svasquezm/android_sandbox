package cl.svasquezm.sandbox.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import cl.svasquezm.sandbox.R
import cl.svasquezm.sandbox.databinding.FragmentFeaturesBinding
import cl.svasquezm.sandbox.features.FeaturesFragment.FeatureType
import cl.svasquezm.sandbox.features.FeaturesFragment.FeatureDataClass
import cl.svasquezm.sandbox.features.FeaturesFragment.FeaturesAdapter

/**
 * This Fragment uses "View Binding" Android Jetpack component.
 * See build.gradle which contains
 * viewBinding {
 *      enabled = true
 * }
 * And fragment_data_binding.xml for more details
 */
class MotionLayoutFeaturesFragment : Fragment() {

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
                R.string.feature_motion_swipe_on_click,
                R.string.feature_motion_swipe_on_click_explanation,
                FeatureType.MOTION_SWIPE
            ),
            FeatureDataClass(
                R.string.feature_motion_flip_on_click_item,
                R.string.feature_motion_flip_on_click_item_explanation,
                FeatureType.MOTION_FLIP
            )
        )

        // Access root's Recyclerview
        _binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        _binding.recyclerView.adapter = FeaturesAdapter(featuresList) { itemClicked ->
            findNavController().run {
                when(itemClicked){
                    FeatureType.MOTION_SWIPE -> navigate(
                        R.id.action_motionLayoutFeaturesFragment_to_motionLayoutFragment2
                    )
                    FeatureType.MOTION_FLIP -> navigate(
                        R.id.action_motionLayoutFeaturesFragment_to_motionLayoutFragment2
                    )
                }
            }
        }
    }
}
