package com.example.farahasmaabobakermohamed.activite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.farahasmaabobakermohamed.R;
import com.example.farahasmaabobakermohamed.fragment.HomeFragment;
import com.example.farahasmaabobakermohamed.fragment.PanierFragment;
import com.example.farahasmaabobakermohamed.fragment.ProfileFragment;
import com.example.farahasmaabobakermohamed.fragment.RechercheFragment;
import com.example.farahasmaabobakermohamed.fragment.SeanceFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    PanierFragment panierFragment = new PanierFragment();
    HomeFragment homeFragment=new HomeFragment();
    RechercheFragment rechercheFragment = new RechercheFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    SeanceFragment seanceFragment = new SeanceFragment();
    //Toolbar toolbar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      auth=FirebaseAuth.getInstance();
        //toolbar=findViewById(R.id.home_toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        bottomNavigationView  = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,homeFragment).commit();
        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.navigation_profile);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_acheter:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,homeFragment).commit();
                        return true;
                    case R.id.navigation_seance:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,seanceFragment).commit();
                        return true;
                    case R.id.navigation_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,profileFragment).commit();
                        return true;
                    case R.id.navigation_recherche:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,rechercheFragment).commit();
                        return true;
                    case R.id.navigation_panier:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_container,panierFragment).commit();
                        return true;
                }

                return false;
            }
        });


    }


    private void loadFragment(Fragment homefragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container,homefragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.menu_logout){
            auth.signOut();
            startActivity(new Intent(MainActivity.this,login_activity.class));
            finish();
        }else if(id==R.id.menu_my_cart){
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        }
        return true;
    }




}