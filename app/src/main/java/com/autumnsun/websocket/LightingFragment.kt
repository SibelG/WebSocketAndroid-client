package com.autumnsun.websocket

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.autumnsun.websocket.databinding.FragmentLightingBinding
import com.autumnsun.websocket.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.WebSocket
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI


class LightingFragment : Fragment() {

    private lateinit var webSocketClient: WebSocketClient
    private var mClient: OkHttpClient? = null
    lateinit var binding: FragmentLightingBinding
    lateinit var navController: NavController



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLightingBinding.inflate(inflater)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.blank.setOnClickListener{
            //Toast.makeText(requireContext(),"tıkla",Toast.LENGTH_LONG).show()
            SocketManager.sendMessage(subscribe())
        }
    }


    private fun subscribe() :String{
        return (
            "{\"is_request\":true,\"id\":84,\"params\":[{\"id\":\"a2830d60-ddff-4dad-8f3d-dfca0ded2462\",\"value\":1}\r\n]," +
                    "\"method\":\"UpdateControlValue\"}")

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
        //_binding = null
    }

    companion object {
        //ip4 connect
        const val WEB_SOCKET_URL = "ws://${Constants.IP_ADDRESS}:${Constants.PORT}/ligthingPage"
        const val TAG = "WebSocketTag"
    }
}