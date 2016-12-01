package com.desmond.squarecamera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import me.nereo.multi_image_selector.MultiImageSelectorFragment;


public class CameraActivity extends AppCompatActivity implements MultiImageSelectorFragment.Callback {

    public static final String TAG = CameraActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.squarecamera__CameraFullScreenTheme);
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.squarecamera__activity_camera);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, CameraFragment.newInstance(), CameraFragment.TAG)
                    .commit();
        }
    }

    public void returnPhotoUri(Uri uri) {
        Intent data = new Intent();
        data.setData(uri);

        if (getParent() == null) {
            setResult(RESULT_OK, data);
        } else {
            getParent().setResult(RESULT_OK, data);
        }

        finish();
    }

    public void onCancel(View view) {
        getSupportFragmentManager().popBackStack();
    }

    public void onCloseActivity(View view) {
        finish();
    }

    @Override
    public void onSingleImageSelected(String s) {
        returnPhotoUri(Uri.fromFile(new File(s)));
    }

    @Override
    public void onImageSelected(String s) {
    }

    @Override
    public void onImageUnselected(String s) {

    }

    @Override
    public void onCameraShot(File file) {

    }
}
