package cl.svasquezm.sandbox.ui.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.svasquezm.sandbox.databinding.FragmentConstraintLayout1Binding

class CoordinatorLayout1Fragment : Fragment() {
    private lateinit var viewBinding: FragmentConstraintLayout1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentConstraintLayout1Binding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }
}
