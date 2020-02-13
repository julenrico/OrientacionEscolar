package com.reginald.skola.main;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.reginald.skola.R;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NumerosViewHolder> {

    private Context context;
    public List<UniversityDegree> universityDegrees;

    private listItemClick listItemOnclickListener;

    private DatabaseHelper databaseHelper;

    private boolean favMode;

    EditText dialogText;

    public RecyclerAdapter(Context context) {
        this.context = context;

        databaseHelper = new DatabaseHelper(context);
        updateList();
    }

    public interface listItemClick {
        void onListItemClick(UniversityDegree universityDegree);
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
        holder.bind(universityDegrees.get(position));
    }

    @Override
    public int getItemCount() {
        return universityDegrees.size();
    }

    class NumerosViewHolder extends RecyclerView.ViewHolder {

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
        }

        void bind(UniversityDegree universityDegree) {
            txtDegreeName.setText(universityDegree.getDegreeName());
            txtBranch.setText(universityDegree.getBranch().getBranchName());
            txtCampus.setText(universityDegree.getCampus().getCampusName());
            txtCenter.setText(universityDegree.getCenter().getCenterName());
            txtNote.setText(universityDegree.getDegreeNote());

            likeButton.setLiked(databaseHelper.getFavUniversityDegrees().contains(universityDegree));

            /*Darle al corazón para añadir o eliminar de favoritos*/
            likeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    //ADD TO FAV
                    databaseHelper.insertToFav(universityDegree);
                    Toast.makeText(context, "Añadido a Favoritos.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    //QUIT FROM FAV
                    databaseHelper.deleteFromFav(universityDegree);
                    Toast.makeText(context, "Eliminado de Favoritos.", Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnClickListener((v) -> {
                /*Para fin de curso, al clickar en un grado, mostrar info sobre ese grado*/
                if (listItemOnclickListener != null) {
                    listItemOnclickListener.onListItemClick(universityDegree);
                }
            });

            itemView.setOnLongClickListener((v) -> {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                dialogText = new EditText(context);
                alert.setMessage("Introduce la Nota que le quieras añadir:");
                alert.setTitle("NOTA");
                alert.setView(dialogText);
                alert.setPositiveButton("ACEPTAR", (dialog, which) -> {
                    String nota = dialogText.getText().toString();
                    if (databaseHelper.existsNote(universityDegree)) {
                        if (nota.equals("")) {
                            databaseHelper.deleteNotes(universityDegree);
                        } else {
                            databaseHelper.updateNotes(universityDegree, nota);
                        }
                    } else {
                        databaseHelper.insertNotes(universityDegree, nota);
                    }

                    Log.d("IBAI", universityDegree.getDegreeName());
                    updateList();
                });
                alert.show();
                return true;
            });
        }
    }

    public void updateList() {
        if (favMode) {
            setUniversityDegrees(databaseHelper.getFavUniversityDegrees());
        } else {
            setUniversityDegrees(databaseHelper.getUniversityDegrees());
        }
        notifyDataSetChanged();
    }

    public void setUniversityDegrees(List<UniversityDegree> universityDegrees) {
        this.universityDegrees = universityDegrees;
    }

    public void setListItemOnclickListener(listItemClick listItemOnclickListener) {
        this.listItemOnclickListener = listItemOnclickListener;
    }

    public void setFavMode(boolean favMode) {
        this.favMode = favMode;
    }
}
