package dsa.eetac.upc.edu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIRest myapirest;
    private Recycler recycler;
    private RecyclerView recyclerView;

    TextView textViewIne;
    TextView textViewNameTown;
    ImageView ivImageFromUrl;

    private String token;

    ProgressDialog progressDialog;

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

        //Progress loading
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Waiting for the server");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        myapirest = APIRest.createAPIRest();

        getData();
    }

    private void getData() {
        Call<Cities> elementCall = myapirest.getData();

        elementCall.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                if(response.isSuccessful()){
                    Cities cities = response.body();

                    List<Element> elementList = cities.getElements();

                    for(int i = 0; i<elementList.size(); i++){
                        Log.i("Nom municipi: " + elementList.get(i).getMunicipiNom(), response.message());
                    }

                    if(elementList.size() != 0){
                        recycler.addElements(elementList);
                    }

                    progressDialog.hide();
                } else {
                    Log.e("Response failure", String.valueOf(response.errorBody()));

                    progressDialog.hide();

                    //Show the alert dialog
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                    alertDialogBuilder
                            .setTitle("Error")
                            .setMessage(response.message())
                            .setCancelable(false)
                            .setPositiveButton("OK", (dialog, which) -> finish());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                Log.e("No api connection", t.getMessage());

                progressDialog.hide();

                //Show the alert dialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder
                        .setTitle("Error")
                        .setMessage(t.getMessage())
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog, which) -> finish());

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (token != null) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            token = data.getStringExtra("token");
        }
    }

}
