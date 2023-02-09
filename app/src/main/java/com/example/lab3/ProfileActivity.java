package com.example.lab3;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class ProfileActivity extends AppCompatActivity {
    private ImageView imgView;
    public static final String TAG = "PROFILE_ACTIVITY";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.e(TAG, "In function: onCreate");

        Intent intent = getIntent();

        EditText input_email = findViewById(R.id.editTextTextEmailAddress2);
        input_email.setText(intent.getStringExtra("EMAIL"));
        imgView = findViewById(R.id.imageView);

        Button takePictureButton = findViewById(R.id.button2);

        ActivityResultLauncher<Intent> myPictureTakerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
                            imgView.setImageBitmap(imgBitmap);
                        } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                            Log.i(TAG, "User refused to capture a picture.");
                        }
                    }
                });
        
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                dispatchTakePictureIntent();
            }

            private void dispatchTakePictureIntent() {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    myPictureTakerLauncher.launch(takePictureIntent);
                }
            }
        });


    

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "In function: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "In function: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "In function: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "In function: onStop");
    }


    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "In function: onDestroy");
    }






}