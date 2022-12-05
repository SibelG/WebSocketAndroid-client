package com.autumnsun.websocket

import android.os.Bundle
import android.system.Os.close
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.autumnsun.websocket.databinding.FragmentLoginBinding
import com.autumnsun.websocket.utils.Constants
import okhttp3.OkHttpClient
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI


class LoginFragment : Fragment(){

    lateinit var navController: NavController
    lateinit var binding: FragmentLoginBinding
    private lateinit var webSocketClient: WebSocketClient


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.loginButton.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            navController.navigate(action)
            SocketManager.sendMessage(subscribe())
            //Toast.makeText(requireContext(),"tıkla",Toast.LENGTH_LONG).show()

        }
    }


    private fun subscribe() : String {
        return (
            "{\"is_request\":true,\"id\":8,\"params\"" +
                    ":[{\"username\":\"demo\",\"password\":\"123456\"}],\"method\":\"Authenticate\"}")

    }



    override fun onResume() {
        super.onResume()
        SocketManager.initWebSocket(WEB_SOCKET_URL)
    }

    override fun onPause() {
        super.onPause()
        SocketManager.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        SocketManager.close()
        //_binding = null
    }

    companion object {
        //ip4 connect
        const val WEB_SOCKET_URL = "ws://${Constants.IP_ADDRESS}:${Constants.PORT}/successPage"
        const val TAG = "WebSocketTag"
    }
}