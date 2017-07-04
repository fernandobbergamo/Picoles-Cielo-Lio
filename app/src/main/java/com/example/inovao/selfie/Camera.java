package com.example.inovao.selfie;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Camera extends AppCompatActivity {

    Button finalizar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.camera);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        finalizar = (Button) findViewById(R.id.finalizar);

        finalizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent irTela = new Intent(Camera.this, MainActivity.class);
                startActivity(irTela);
            }
        });
    }

    public void onBackPressed()  { }
}
