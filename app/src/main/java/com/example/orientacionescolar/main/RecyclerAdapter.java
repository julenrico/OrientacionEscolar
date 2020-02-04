package com.example.orientacionescolar.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orientacionescolar.R;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NumerosViewHolder> {

    private Context context;
    public List<UniversityDegree> universityDegrees;

    final private listItemClick listItemOnclickListener;

    private DatabaseHelper databaseHelper;

    private ArrayList<UniversityDegree> universityDegreesFav;

    public RecyclerAdapter(List<UniversityDegree> universityDegrees, listItemClick listener, Context context) {

        this.universityDegrees = universityDegrees;
        this.context = context;

        listItemOnclickListener = listener;

        databaseHelper = new DatabaseHelper(context);

        universityDegreesFav = databaseHelper.getFavUniversityDegrees();
    }

    public interface listItemClick {

        void onListItemClick(int clickedItem);
    }

    @NonNull
    @Override
    public NumerosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        int layoutIdListItem = R.layout.lista_view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(layoutIdListItem, parent, false);
        return new NumerosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumerosViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return universityDegrees.size();
    }

    class NumerosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtDegreeName;
        TextView txtBranch;
        TextView txtCampus;
        TextView txtCenter;
        LikeButton likeButton;

        public NumerosViewHolder(View itemView) {
            super(itemView);

            txtBranch = itemView.findViewById(R.id.txtBranch);
            txtDegreeName = itemView.findViewById(R.id.txtDegreeName);
            txtCampus = itemView.findViewById(R.id.txtCampus);
            txtCenter = itemView.findViewById(R.id.txtCenter);
            likeButton = itemView.findViewById(R.id.heart_button);

            itemView.setOnClickListener(this);
        }

        void bind(int listaIndex) {

            UniversityDegree degree = universityDegrees.get(listaIndex);

            txtDegreeName.setText(degree.getDegreeName());
            txtBranch.setText(degree.getBranch().getBranchName());
            txtCampus.setText(degree.getCampus().getCampusName());
            txtCenter.setText(degree.getCenter().getCenterName());

            likeButton.setLiked(universityDegreesFav.contains(degree));

            likeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    //ADD TO FAV
                    databaseHelper.insertToFav(universityDegrees.get(listaIndex));
                    universityDegreesFav = databaseHelper.getFavUniversityDegrees();
                    Toast.makeText(context, "Grado guardado en Favoritos.", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    //QUIT FROM FAV
                    databaseHelper.deleteFromFav(universityDegrees.get(listaIndex));
                    universityDegreesFav = databaseHelper.getFavUniversityDegrees();
                    Toast.makeText(context, "Grado eliminado de Favoritos.", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();

                }
            });
        }

        @Override
        public void onClick(View v) {

            int clickedItem = getAdapterPosition();

            listItemOnclickListener.onListItemClick(clickedItem);
        }
    }
}
