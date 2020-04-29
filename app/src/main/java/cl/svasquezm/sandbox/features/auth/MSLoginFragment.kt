package cl.svasquezm.sandbox.features.auth

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import cl.svasquezm.sandbox.R
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException

/**
 * A simple [Fragment] subclass.
 */
class MSLoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return Button(activity).apply {
            setText(R.string.hello_blank_fragment)
            setOnClickListener {
                authenticate()
            }
        }
    }

    private fun authenticate() {
        object: AsyncTask<Void, Void, Void>(){
            override fun doInBackground(vararg params: Void?): Void? {
                val client = PublicClientApplication.createSingleAccountPublicClientApplication(
                    context!!,
                    R.raw.auth_config_single_account
                )
                val scopes = arrayOf("https://frogmiusers.onmicrosoft.com/frogmi_android/read")
                client.signIn(activity!!, "Test", scopes, object : AuthenticationCallback {
                    override fun onSuccess(authenticationResult: IAuthenticationResult) {
                        Toast.makeText(activity!!, "Success", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(exception: MsalException?) {
                        Toast.makeText(activity!!, "Error", Toast.LENGTH_SHORT).show()
                        exception?.printStackTrace()
                    }

                    override fun onCancel() {
                        Toast.makeText(activity!!, "Cancel", Toast.LENGTH_SHORT).show()
                    }
                })

                return null
            }
        }.execute()
    }
}
