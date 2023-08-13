package com.walle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App { public static void main(String[] args) {
    // Start the chat server
    new Thread(() -> {
        try {
            new ChatServer(8007).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();

    // Start the chat client
    new Thread(() -> {
        try {
            new ChatClient("localhost", 8007).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();
}
}