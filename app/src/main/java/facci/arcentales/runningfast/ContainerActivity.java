package facci.arcentales.runningfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import facci.arcentales.runningfast.Fragments.HistorialFragment;
import facci.arcentales.runningfast.Fragments.PerfilFragment;
import facci.arcentales.runningfast.Fragments.RunFragment;

public class ContainerActivity extends AppCompatActivity {

    private boolean viewIsAtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomNavigationView bottom = (BottomNavigationView) findViewById(R.id.bottombar);

        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.historial:
                        addFragment(new HistorialFragment());
                        viewIsAtHome = false;
                        break;
                    case R.id.run:
                        addFragment(new RunFragment());
                        viewIsAtHome = true;
                        break;
                    case R.id.perfil:
                        addFragment(new PerfilFragment());
                        viewIsAtHome = false;
                        break;
                }
                return true;
            }
        });
        bottom.setSelectedItemId(R.id.run);
    }
    private void addFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }
    public void onBackPressed() {
        if (!viewIsAtHome) {
            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottombar);
            bottomNavigationView.setSelectedItemId(R.id.run);
        } else {
            moveTaskToBack(true);
        }
    }
}

