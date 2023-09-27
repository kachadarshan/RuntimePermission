package com.demo_runtimepermission;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_IMAGES;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_request = findViewById(R.id.btn_request);

        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(MainActivity.this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED  && ContextCompat.checkSelfPermission(MainActivity.this, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED && ContextCompat.checkSelfPermission(MainActivity.this, READ_SMS) == PackageManager.PERMISSION_DENIED) {
                    // if permission is not granted then we are requesting for the permissions on below line.
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE,READ_SMS}, 100);
                } else {
                    // if permission is already granted then we are displaying a toast message as permission granted.
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // displaying a toast message when permission is granted.
//                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                Toast.makeText(MainActivity.this, "Read SMS Permission Granted", Toast.LENGTH_SHORT).show();

            } else {
                // displaying a toast message when permission is denied.
                Toast.makeText(MainActivity.this, "Read SMS Permission Denied", Toast.LENGTH_SHORT).show();
            }

        }
    }
}