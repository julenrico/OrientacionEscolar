package com.example.orientacionescolar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NumerosViewHolder>{

    Context context;
    private List<UniversityDegree> universityDegrees;

    final private listItemClick listItemOnclickListener;

    LikeButton likeButton;

    public RecyclerAdapter(List<UniversityDegree> universityDegrees, listItemClick listener){

        this.universityDegrees=universityDegrees;

        listItemOnclickListener = listener;
    }

    public interface listItemClick{

        void onListItemClick(int clickedItem);
    }

    @NonNull
    @Override
    public NumerosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        int layoutIdListItem = R.layout.lista_view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(layoutIdListItem,parent, false);
        likeButton =view.findViewById(R.id.heart_button);
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

    class NumerosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtDegreeName;
        TextView txtBranch;
        TextView txtCampus;
        TextView txtCenter;

        public NumerosViewHolder(View itemView){
            super(itemView);

            txtBranch = itemView.findViewById(R.id.txtBranch);
            txtDegreeName = itemView.findViewById(R.id.txtDegreeName);
            txtCampus = itemView.findViewById(R.id.txtCampus);
            txtCenter = itemView.findViewById(R.id.txtCenter);

            itemView.setOnClickListener(this);
        }

        void bind(int listaIndex){

            txtDegreeName.setText(universityDegrees.get(listaIndex).getDegreeName());
            txtBranch.setText(universityDegrees.get(listaIndex).getBranch().getBranchName());
            txtCampus.setText(universityDegrees.get(listaIndex).getCampus().getCampusName());
            txtCenter.setText(universityDegrees.get(listaIndex).getCenter().getCenterName());
            likeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    //ADD TO FAV
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    //QUIT FROM FAV
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
