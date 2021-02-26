package com.hj.study.utils;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;

import java.net.URI;


/**
 * @author hutao
 * @className MyWebSocketClient
 * @description TODO
 * @date 2021/2/25 11:06 上午
 */
@Slf4j
public class MyWebSocketClient extends WebSocketClient {

    public MyWebSocketClient(URI serverUri){
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("------ MyWebSocket onOpen ------");
    }

    @Override
    public void onMessage(String s) {
        log.info("------ MyWebSocket onMessage ------", s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {

        log.info("------ MyWebSocket onClose ------");
    }

    @Override
    public void onError(Exception e) {

        log.info("------ MyWebSocket onError ------");
    }


}
