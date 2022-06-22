package com.manya.tech.ecomapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.manya.tech.ecomapp.R;
import com.manya.tech.ecomapp.databinding.ActivityMainBinding;
import com.manya.tech.ecomapp.ui.home.FragHome;
import com.manya.tech.ecomapp.utils.Utils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Utils.changeActionBarColor(this);

        hideDefaultSupportBar();

        loadHomeFrag();

    }


    private void hideDefaultSupportBar() {
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();
    }

    private void loadHomeFrag() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragContainer, FragHome.newInstance());
        transaction.commit();
    }
}