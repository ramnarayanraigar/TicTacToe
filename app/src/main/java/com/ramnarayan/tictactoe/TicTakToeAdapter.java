package com.ramnarayan.tictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class TicTakToeAdapter extends ArrayAdapter {

    ArrayList<Integer> birdList;
    int[] arr;
    Context context;

    public TicTakToeAdapter(Context context, int textViewResourceId, ArrayList<Integer> objects, int[] arr) {
        super(context, textViewResourceId, objects);
        birdList = objects;
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.item_tic_tac_toe, null);
        ImageView imageView = v.findViewById(R.id.imagePlayerInput);
        changeImage(imageView, arr[position]);

        return v;

    }

    public void updateData(int[] arr) {
        this.arr = arr;
        notifyDataSetChanged();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void changeImage(ImageView imageView, int value) {
        if (value == 10) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_player));
        } else if (value == 1) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_playera));
        }  else if (value == 2) {
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_playerb));
        }
    }
}