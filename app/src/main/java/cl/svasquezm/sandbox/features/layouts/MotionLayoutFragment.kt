package cl.svasquezm.sandbox.features.layouts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.svasquezm.sandbox.R

/**
 * A simple [Fragment] subclass.
 */
class MotionLayoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_motion_layout, container, false)
    }

}
