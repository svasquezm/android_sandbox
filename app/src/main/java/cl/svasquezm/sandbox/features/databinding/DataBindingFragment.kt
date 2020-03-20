package cl.svasquezm.sandbox.features.databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.svasquezm.sandbox.databinding.FragmentDataBindingBinding

/**
 * Enable this feature by adding
 * dataBinding {
 *   enabled = true
 * }
 */
class DataBindingFragment : Fragment() {
    private val dataUser = DataClassBinding("Sebastián", "Vásquez")
    lateinit var viewBinding: FragmentDataBindingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDataBindingBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.user = dataUser
    }

    /**
     * Data class binding
     */
    data class DataClassBinding(val name: String, val lastName: String)
}