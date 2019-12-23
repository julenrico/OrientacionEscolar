package com.example.orientacionescolar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NumerosViewHolder>{

    Context context;
    private int numerosItems;

    final private listItemClick listItemOnclickListener;

    public RecyclerAdapter(int numeroDeItems, listItemClick listener){

        numerosItems=numeroDeItems;

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
        boolean attachToParentFast = false;

        View view = layoutInflater.inflate(layoutIdListItem,parent,attachToParentFast);

        return new NumerosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumerosViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numerosItems;
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

            DatabaseHelper databaseHelper = new DatabaseHelper(context,"dataBase",null,1);

            //txtDegreeName.setText();
            txtBranch.setText(databaseHelper.getUniversityDegreeBranches().get(listaIndex).getBranchName());
            txtCampus.setText(databaseHelper.getUniversityDegreeCampus().get(listaIndex).getCampusName());
            //txtCenter.setText();
        }

        @Override
        public void onClick(View v) {

            int clickedItem = getAdapterPosition();

            listItemOnclickListener.onListItemClick(clickedItem);
        }
    }
}
