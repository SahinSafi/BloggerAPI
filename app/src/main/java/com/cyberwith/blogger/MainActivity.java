package com.cyberwith.blogger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.cyberwith.blogger.models.PostList;
import com.google.android.material.navigation.NavigationView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigationID);
        recyclerView = findViewById(R.id.recyclerID);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.homeID:
                        Toast.makeText(getApplicationContext(), " Home ", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.androidID:
                        Toast.makeText(getApplicationContext(), " Android ", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.iosID:
                        Toast.makeText(getApplicationContext(), " IOS ", Toast.LENGTH_LONG).show();
                        break;
                }

                return false;
            }
        });

        setUpToolBar();
        getData();
    }

    private void setUpToolBar(){
        drawerLayout = findViewById(R.id.drawerID);
        toolbar = findViewById(R.id.toolBarID);
        setSupportActionBar(toolbar);

        //create hamburger icon
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void getData(){
        final Call<PostList> postList = BloggerApi.getService().getPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                postAdapter = new PostAdapter(MainActivity.this, list.getItems());
                recyclerView.setAdapter(postAdapter);
                Toast.makeText(getApplicationContext(), " Successful ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), " Error "+t, Toast.LENGTH_LONG).show();
            }
        });
    }
}
