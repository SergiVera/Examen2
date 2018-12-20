package dsa.eetac.upc.edu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRest {
    //We specify the url
    String BASE_URL = "https://do.diba.cat/api/dataset/municipis/format/json/";

    //We get the Data from the API
    @GET("pag-ini/{numinici}/pag-fi/{numfinal}")
    Call<Cities> getData(@Path("numinici") int pagini, @Path("numfinal") int pagfi);

    static APIRest createAPIRest() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(APIRest.class);
    }
}
