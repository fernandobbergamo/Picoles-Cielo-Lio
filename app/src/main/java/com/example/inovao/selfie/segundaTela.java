package com.example.inovao.selfie;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class segundaTela extends AppCompatActivity {

    Button xBT;
    Button creditoBT;
    Button debitoBT;
    Button selfieBT;
    Bitmap thumbnail;
    File pic;
    protected static final int CAMERA_PIC_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.segundatela);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        xBT = (Button) findViewById(R.id.xBTid);
        creditoBT = (Button) findViewById(R.id.creditoBTid);
        debitoBT = (Button) findViewById(R.id.debitoBTid);
        selfieBT = (Button) findViewById(R.id.selfieBTid);


        xBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent irTela = new Intent(segundaTela.this, MainActivity.class);
                startActivity(irTela);
            }
        });
        creditoBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        debitoBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        selfieBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            if (resultCode != RESULT_CANCELED) {

                thumbnail = (Bitmap) data.getExtras().get("data");
                ImageView image = (ImageView) findViewById(R.id.a1);
                image.setImageBitmap(thumbnail);
                Random generator = new Random();
                int n = 10000;
                n = generator.nextInt(n);
                final String name = "Image-" + n + ".png";

                try {
                    File root = Environment.getExternalStorageDirectory();
                    if (root.canWrite()){
                        pic = new File(root, name);
                        FileOutputStream out = new FileOutputStream(pic);
                        thumbnail.compress(Bitmap.CompressFormat.PNG, 100, out);
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    Log.e("BROKEN", "Could not write file " + e.getMessage());
                }
                //ImageView image = (ImageView) findViewById(R.id.a1);
                //image.setImageBitmap(thumbnail);
/*
                final File myDir = new File(root + "/sorvetes");
                myDir.mkdirs();

                Random generator = new Random();
                int n = 10000;
                n = generator.nextInt(n);
                final String fname = "Image-" + n + ".jpg";

                File file = new File(myDir, fname);

                if (file.exists())
                    file.delete();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
*/
            new Thread(new Runnable() {
                public void run() {
                   try {
                       GMailSender sender = new GMailSender("cielolabsbr@gmail.com", "Inovacao2015");
                       sender.addAttachment("/storage/sdcard0/DCIM/Camera/IMG_20170327_141500.jpg");
                       sender.sendMail("Cielo Garage - Convenção 2017",
                                    "Aqui está sua selfie da Convenção Cielo 2017",
                                    "cielolabsbr@gmail.com",
                                    "cielolabsbr@gmail.com, lio@hubbrasil.com.br");

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }).start();

                Intent irTela = new Intent(segundaTela.this, Camera.class);
                startActivity(irTela);
            }
            else{
                Intent irTela = new Intent(segundaTela.this, segundaTela.class);
                startActivity(irTela);
            }
        }
    }
}

