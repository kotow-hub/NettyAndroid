package com.goav.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.goav.app.socket.ClientBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClientBus.Initialization(this, "token", "host", 8080);
    }

    public void push(Object o) {
        ClientBus.request(o);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClientBus.Recycle();
    }
}
