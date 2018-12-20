package dsa.eetac.upc.edu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private APIRest myapirest;
    private Recycler recycler;
    private RecyclerView recyclerView;

    TextView textViewIne;
    TextView textViewNameTown;
    ImageView ivImageFromUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recycler = new Recycler(this);
        recyclerView.setAdapter(recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //TextViews where we show the Ine, the name of the local council and the image of the local council
        textViewIne = findViewById(R.id.numeroIne);
        textViewNameTown = findViewById(R.id.nomMunicipi);
        ivImageFromUrl = findViewById(R.id.escutMunicipi);

        // Get the Intent that started this activity
        Intent intent = getIntent();

        myapirest = APIRest.createAPIRest();

        getIne();
    }

    private void getIne() {
        Call<Element> elementCall = myapirest.getIne();
    }
}
