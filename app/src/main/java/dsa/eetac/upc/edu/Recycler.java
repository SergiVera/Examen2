package dsa.eetac.upc.edu;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder> {

    //public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private Context context;
    private List<Element> data;

    public void addElements(List<Element> elementList) {
        data.addAll(elementList);
        notifyDataSetChanged();
    }

    //Asign the text TextView to the text1 in the layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout constraintLayout;
        private TextView nomMunicipiView;
        private TextView ineMunicipiView;
        private ImageView escutMuncipi;

        public ViewHolder(View v) {
            super(v);
            constraintLayout = v.findViewById(R.id.constraintLayout);
            nomMunicipiView = v.findViewById(R.id.nomMunicipi);
            ineMunicipiView = v.findViewById(R.id.numeroIne);
            escutMuncipi = v.findViewById(R.id.escutMunicipi);
        }
    }

    //Constructor
    public Recycler(Context context) {
        this.data = new ArrayList<>();
        this.context = context;
    }

    @Override
    public Recycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Recycler.ViewHolder holder, int position) {
        Element element = data.get(position);
        holder.ineMunicipiView.setText(element.getIne());
        holder.nomMunicipiView.setText(element.getMunicipiNom());

        Picasso.with(context).load(element.getMunicipiEscut()).into(holder.escutMuncipi);
    }

    @Override
    public  int getItemCount() {
        return data.size();
    }
}

