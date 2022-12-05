package com.autumnsun.websocket


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.autumnsun.websocket.databinding.FragmentHomeBinding
import com.autumnsun.websocket.utils.Constants
import okhttp3.OkHttpClient
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var webSocketClient: WebSocketClient
    private var mClient: OkHttpClient? = null
    lateinit var binding: FragmentHomeBinding
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SocketManager.sendMessage(subscribe())
        navController = Navigation.findNavController(view)
        binding.lightIcon.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToLightingFragment()
            navController.navigate(action)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun subscribe():String {
        return (
            "{\"is_request\":true,\"id\":5,\"params\":[{}],\"method\":\"GetControlList\"}")

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
        const val WEB_SOCKET_URL = "ws://${Constants.IP_ADDRESS}:${Constants.PORT}/homePage"
        const val TAG = "WebSocketTag"
    }
}