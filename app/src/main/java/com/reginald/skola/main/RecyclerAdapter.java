package com.reginald.skola.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reginald.skola.R;
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

    AlertDialog.Builder alert;
    EditText dialogText;


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

    class NumerosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView txtDegreeName;
        TextView txtBranch;
        TextView txtCampus;
        TextView txtCenter;
        TextView txtNote;
        LikeButton likeButton;

        public NumerosViewHolder(View itemView) {
            super(itemView);

            txtBranch = itemView.findViewById(R.id.txtBranch);
            txtDegreeName = itemView.findViewById(R.id.txtDegreeName);
            txtCampus = itemView.findViewById(R.id.txtCampus);
            txtCenter = itemView.findViewById(R.id.txtCenter);
            txtNote = itemView.findViewById(R.id.txtNote);
            likeButton = itemView.findViewById(R.id.heart_button);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        void bind(int listaIndex) {



            UniversityDegree degree = universityDegrees.get(listaIndex);

            txtDegreeName.setText(degree.getDegreeName());
            txtBranch.setText(degree.getBranch().getBranchName());
            txtCampus.setText(degree.getCampus().getCampusName());
            txtCenter.setText(degree.getCenter().getCenterName());
            txtNote.setText(degree.getDegreeNote());

            likeButton.setLiked(universityDegreesFav.contains(degree));

            /*Darle al corazón para añadir o eliminar de favoritos*/
            likeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    //ADD TO FAV
                    databaseHelper.insertToFav(universityDegrees.get(listaIndex));
                    universityDegreesFav = databaseHelper.getFavUniversityDegrees();
                    Toast.makeText(context, "Añadido a Favoritos.", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    //QUIT FROM FAV
                    databaseHelper.deleteFromFav(universityDegrees.get(listaIndex));
                    universityDegreesFav = databaseHelper.getFavUniversityDegrees();
                    Toast.makeText(context, "Eliminado de Favoritos.", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();

                }
            });

            alert = new AlertDialog.Builder(context);
            dialogText = new EditText(context);
            alert.setMessage("Introduce la Nota que le quieras añadir:");
            alert.setTitle("NOTA");
            alert.setView(dialogText);
            alert.setPositiveButton("ACEPTAR", (dialog, which) -> {
                String nota = dialogText.getText().toString();
                databaseHelper.updateNotes(databaseHelper.getUniversityDegrees().get(getAdapterPosition()).getDegreeId(),nota);
                notifyDataSetChanged();
            });
        }

        @Override
        public void onClick(View v) {

            /*Para fin de curso, al clickar en un grado, mostrar info sobre ese grado*/
            int clickedItem = getAdapterPosition();

            listItemOnclickListener.onListItemClick(clickedItem);
        }

        @Override
        public boolean onLongClick(View v) {
            alert.show();
            return true;
        }
    }
}
