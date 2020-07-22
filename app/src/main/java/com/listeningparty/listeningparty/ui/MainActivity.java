package com.listeningparty.listeningparty.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.listeningparty.listeningparty.R;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MusicPlayerFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener, ChatFragment.FromChatFragmentToMainActivityInteractionListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.humberger_icon)
    ImageView humbergerIcon;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        humbergerIcon.setOnClickListener(v -> {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getTitle().equals("Chat")) {
                Navigation.findNavController(MainActivity.this, R.id.my_nav_host_fragment).navigate(R.id.action_feedFragment_to_chatsFragment);
            } else if (item.getTitle().equals("For You")) {
                Navigation.findNavController(MainActivity.this, R.id.my_nav_host_fragment).navigate(R.id.action_chatsFragment_to_feedFragment);
            }
            return true;
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Navigation.findNavController(this, R.id.my_nav_host_fragment).navigate(R.id.action_feedFragment_to_profileFragment);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMusicPlayerOpened(boolean isOpened) {
        bottomNavigationView.setVisibility(isOpened ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void onProfileFragmentOpened(boolean shouldHideBottomNavigation) {
        bottomNavigationView.setVisibility(shouldHideBottomNavigation ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void onChatOpened(boolean isOpened) {
        bottomNavigationView.setVisibility(isOpened ? View.INVISIBLE : View.VISIBLE);
        humbergerIcon.setVisibility(isOpened ? View.INVISIBLE : View.VISIBLE);
    }
}
