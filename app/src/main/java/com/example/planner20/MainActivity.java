package com.example.planner20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    FloatingActionButton fabBtn, fabBtnNewNote, fabBtnNewCheckNote;
    boolean buttonPressed;
    ListView list;
    String title;
    TextView textView;
    ArrayAdapter<String> NotesAdapter;
    ArrayList<String> Notes;
    private static final int REQUEST_CODE = 1;

    int[] icons = {R.drawable.ic_newnote, R.drawable.ic_check,};

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingInflatedId") //kazkoks pataisymas
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home: {
                        Toast.makeText(MainActivity.this, "Home is selected", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.nav_share: {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        startActivity(Intent.createChooser(intent, "share this app"));
                        break;
                    }
                    case R.id.nav_aboutMe: {
                        Intent intent = new Intent(MainActivity.this, about_me.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.nav_aboutApp: {
                        openDialog();
                        break;
                    }
                    case R.id.nav_exit: {
                        // Finish the current activity and remove it from the back stack
                        finish();
                        // Remove all activities from the back stack and exit this app
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        System.exit(0);

                        break;
                    }

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        fabBtn = findViewById(R.id.fabBtn);
        fabBtnNewNote = findViewById(R.id.fabBtnNewNote);
        fabBtnNewCheckNote = findViewById(R.id.fabBtnNewCheckNote);

        //lists section

        list = (ListView) findViewById(R.id.list);
        Notes = new ArrayList<>();
        NotesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Notes);
        list.setAdapter(NotesAdapter);

//        int imageResourceId = getIntent().getIntExtra("imageNote", 0);
//        ImageView imageView = findViewById(R.id.noteIcon);
//        if (imageResourceId != 0) {
//            imageView.setImageResource(imageResourceId);}

        Notes.add(String.valueOf(new NotesID("title")));

//        startActivityForResult(new Intent(MainActivity.this, newNote.class), 1);
        updateListView();


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(MainActivity.this, "" + title, Toast.LENGTH_SHORT).show();
                }
            });
        fabBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (buttonPressed) {
                        fabBtn.setImageResource(R.drawable.ic_close);
                        //set animation to show up options
                        Animation animation = new AlphaAnimation(0.0f, 1.0f);
                        animation.setDuration(500);
                        fabBtnNewNote.startAnimation(animation);
                        fabBtnNewCheckNote.startAnimation(animation);
                        fabBtnNewCheckNote.setVisibility(View.VISIBLE);
                        fabBtnNewNote.setVisibility(View.VISIBLE);

                    } else {
                        Animation animation = new AlphaAnimation(1.0f, 0.0f);
                        animation.setDuration(100);
                        fabBtnNewNote.startAnimation(animation);
                        fabBtnNewCheckNote.startAnimation(animation);
                        fabBtnNewNote.setVisibility(View.GONE);
                        fabBtnNewCheckNote.setVisibility(View.GONE);
                        fabBtn.setImageResource(R.drawable.ic_add);
                    }
                    buttonPressed = !buttonPressed;
                }
            });

            fabBtnNewNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, newNote.class);
                    startActivity(intent);
                }
            });

            fabBtnNewCheckNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, new_check_note.class);
                    startActivity(intent);
                }
            });
        }

public void updateListView() {
    Bundle bundle = getIntent().getExtras();
    Intent intent = getIntent();
    if(bundle != null)
    {
        NotesID a = new NotesID(intent.getStringExtra("title"));
        NotesAdapter.add(String.valueOf(a.getTitle()));
        NotesAdapter.notifyDataSetChanged();
    }
}
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);

    if(requestCode == 1 && resultCode == RESULT_OK)
    {
        NotesID a = new NotesID(intent.getStringExtra("title"));
        NotesAdapter.add(String.valueOf(a.getTitle()));
        NotesAdapter.notifyDataSetChanged();
    }
}
public void openDialog(){
    dialog dialog = new dialog();
    dialog.show(getSupportFragmentManager(), "dialog");
}
    }


