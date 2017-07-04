package com.example.inovao.selfie;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button chocolateBT;
    Button morangoBT;
    Button acaiBT;
    Button amendoimBT;
    Button tapiocaBT;
    Button cocoBT;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.primeiratela);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        chocolateBT = (Button) findViewById(R.id.chocolateBTid);
        morangoBT = (Button) findViewById(R.id.morangoBTid);
        acaiBT = (Button) findViewById(R.id.acaiBTid);
        amendoimBT = (Button) findViewById(R.id.amendoimBTid);
        tapiocaBT = (Button) findViewById(R.id.tapiocaBTid);
        cocoBT = (Button) findViewById(R.id.cocoBTid);

        chocolateBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent irTela = new Intent(MainActivity.this, segundaTela.class);
                startActivity(irTela);
            }
        });
        morangoBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent irTela = new Intent(MainActivity.this, segundaTela.class);
                startActivity(irTela);
            }
        });
        acaiBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent irTela = new Intent(MainActivity.this, segundaTela.class);
                startActivity(irTela);
            }
        });
        amendoimBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent irTela = new Intent(MainActivity.this, segundaTela.class);
                startActivity(irTela);
            }
        });
        tapiocaBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent irTela = new Intent(MainActivity.this, segundaTela.class);
                startActivity(irTela);
            }
        });
        cocoBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent irTela = new Intent(MainActivity.this, segundaTela.class);
                startActivity(irTela);
            }
        });

    }
    public void onBackPressed()  {}
}
