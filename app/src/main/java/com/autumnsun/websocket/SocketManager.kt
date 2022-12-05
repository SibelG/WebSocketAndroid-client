package com.autumnsun.websocket

import android.util.Log
import okio.ByteString
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

object SocketManager {

    private lateinit var webSocketClient: WebSocketClient
    private val TAG = SocketManager::class.java.simpleName
    private var isConnect = false

    fun initWebSocket(uri:String) {
        val webSocketUrl: URI? = URI(uri)
        createWebSocketClient(webSocketUrl)
        //Eğer SSL sertifikalı bir websocket dinliyorsak
        //SSl ayarlamasını yapıyoruz.
        //val socketFactory: SSLSocketFactory = SSLSocketFactory.getDefault() as SSLSocketFactory
        //webSocketClient.setSocketFactory(socketFactory)
        webSocketClient.connect()
        isConnect=true
    }

    fun createWebSocketClient(webSocketUrl: URI?) {
        webSocketClient = object : WebSocketClient(webSocketUrl) {
            override fun onOpen(handshakedata: ServerHandshake?) {
                Log.d(TAG, "onOpen")
            }

            override fun onMessage(message: String?) {
                Log.d(TAG, "onMessage: $message")
                //setUpMessage(message)
            }
            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                Log.d(TAG, "onClose")
                isConnect=false
                //unsubscribe()
            }

            override fun onError(ex: Exception?) {
                Log.e(TAG, "onError: ${ex?.message}")
                isConnect=false
            }

        }
    }

    fun isConnect(): Boolean {
        return isConnect
    }


    fun sendMessage(text: String){
       if (isConnect())
            webSocketClient.send(text)
    }



    fun close() {
        if (isConnect()) {
            webSocketClient.close(1001, "onClosing")
            isConnect=false
        }
    }


}