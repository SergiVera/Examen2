package dsa.eetac.upc.edu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean registred = sharedPref.getBoolean("registered", false);

        Log.d("MIN", "registered: "+registred);

        Class dest;
        if(!registred){
            dest = LoginActivity.class;
        } else{
            dest = MainActivity.class;
        }

        Intent intent = new Intent(this, dest);
        startActivity(intent);
        finish();
    }
}
