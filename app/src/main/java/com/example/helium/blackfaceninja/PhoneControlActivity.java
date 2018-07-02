package com.example.helium.blackfaceninja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.PrintWriter;
import java.net.Socket;


public class PhoneControlActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_go;
    private Button btn_left;
    private Button btn_right;
    private Button btn_back;
    private Button btn_stop;
    private Button btn_connect;
    private EditText edit_ip;
    private EditText edit_port;

    private Socket client;
    private PrintWriter output;
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_control);

        thread = new Thread() {
            @Override
            public void run() {
                try {
                    //client = new Socket("192.168.31.60", 22222);
                    client = new Socket(edit_ip.getText().toString(), Integer.parseInt(edit_port.getText().toString()));
                    output = new PrintWriter(client.getOutputStream(), true);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        initView();
    }

    private void initView(){
        //
        btn_go = (Button)findViewById(R.id.btn_go);
        btn_go.setOnClickListener(this); // Need to Add "implements View.OnClickListener" on top of class
        btn_left = (Button)findViewById(R.id.btn_left);
        btn_left.setOnClickListener(this);
        btn_right = (Button)findViewById(R.id.btn_right);
        btn_right.setOnClickListener(this);
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(this);
        btn_connect = (Button)findViewById(R.id.btn_connect);
        btn_connect.setOnClickListener(this);

        edit_ip = (EditText)findViewById(R.id.edit_ip);
        edit_port = (EditText)findViewById(R.id.edit_port);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_connect:
                thread = null;
                thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            client = new Socket(edit_ip.getText().toString(), Integer.parseInt(edit_port.getText().toString()));
                            output = new PrintWriter(client.getOutputStream(), true);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                break;
            case R.id.btn_go:
                if(client != null)
                    sendData("go");
                break;
            case R.id.btn_left:
                if(client != null)
                    sendData("left");
                break;
            case R.id.btn_right:
                if(client != null)
                    sendData("right");
                break;
            case R.id.btn_back:
                if(client != null)
                    sendData("back");
                break;
            case R.id.btn_stop:
                if(client != null)
                    sendData("stop");
                break;
        }

    }

    private void sendData(String data){
        output.print(data);
        output.flush();
    }

    protected void onPause() {
        super.onPause();
//        try {
//            //client.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //thread.stop();
    }

    protected void onStop(){
        super.onStop();
        try {
            if(client != null)
                sendData("k");
            //client = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
