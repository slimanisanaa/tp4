package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.tp4.adapter.StarAdapter;
import com.example.tp4.beans.Star;
import com.example.tp4.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service =null;
    private static final String TAG = "StarAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        //insérer le code
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void init(){
        service.create(new Star("Bella Hadid", "https://www.google.com/url?sa=i&url=https%3A%2F%2Ffr.freepik.com%2Fvecteurs-premium%2Ffemme-fille-dessin-anime-avatar-icone_2566561.htm&psig=AOvVaw2UyaF1BhlmpEe0tLmhX_zL&ust=1647815874032000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLDEjrOe0_YCFQAAAAAdAAAAABAD", 3.5f));
        service.create(new Star("jacquemus", "https://www.google.com/url?sa=i&url=https%3A%2F%2Ffr.freepik.com%2Fvecteurs-premium%2Ffemme-fille-dessin-anime-avatar-icone_2566561.htm&psig=AOvVaw2UyaF1BhlmpEe0tLmhX_zL&ust=1647815874032000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLDEjrOe0_YCFQAAAAAdAAAAABAD", 3));
        service.create(new Star("Gigi Hadid",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Ffr.freepik.com%2Fvecteurs-premium%2Ffemme-fille-dessin-anime-avatar-icone_2566561.htm&psig=AOvVaw2UyaF1BhlmpEe0tLmhX_zL&ust=1647815874032000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLDEjrOe0_YCFQAAAAAdAAAAABAD", 5));
        service.create(new Star("Hailey Baldwin", "https://www.google.com/url?sa=i&url=https%3A%2F%2Ffr.freepik.com%2Fvecteurs-premium%2Ffemme-fille-dessin-anime-avatar-icone_2566561.htm&psig=AOvVaw2UyaF1BhlmpEe0tLmhX_zL&ust=1647815874032000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLDEjrOe0_YCFQAAAAAdAAAAABAD", 1));
        service.create(new Star("kendall jenner", "https://www.google.com/url?sa=i&url=https%3A%2F%2Ffr.freepik.com%2Fvecteurs-premium%2Ffemme-fille-dessin-anime-avatar-icone_2566561.htm&psig=AOvVaw2UyaF1BhlmpEe0tLmhX_zL&ust=1647815874032000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLDEjrOe0_YCFQAAAAAdAAAAABAD", 5));
        service.create(new Star("Justin Timberlake", "https://www.google.com/url?sa=i&url=https%3A%2F%2Ffr.freepik.com%2Fvecteurs-premium%2Ffemme-fille-dessin-anime-avatar-icone_2566561.htm&psig=AOvVaw2UyaF1BhlmpEe0tLmhX_zL&ust=1647815874032000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCLDEjrOe0_YCFQAAAAAdAAAAABAD", 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new
                                                  SearchView.OnQueryTextListener() {
                                                      @Override
                                                      public boolean onQueryTextSubmit(String query) {
                                                          return true;
                                                      }
                                                      @Override
                                                      public boolean onQueryTextChange(String newText) {
                                                          if (starAdapter != null){
                                                              starAdapter.getFilter().filter(newText);
                                                          }
                                                          return true;
                                                      }
                                                  });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }


}