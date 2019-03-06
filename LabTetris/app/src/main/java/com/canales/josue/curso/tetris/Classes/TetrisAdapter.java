package com.canales.josue.curso.tetris.Classes;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

public class TetrisAdapter extends BaseAdapter {

    Context context;
    int column, row;
    int[] images;

    public TetrisAdapter(Context context, int[] images, int column, int row){
        this.context = context;
        this.images = images;
        this.column = column;
        this.row = row;
    }


    @Override
    public int getCount() {
        return column * row;
    }

    @Override
    public Object getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView img = new ImageView(context);
        img.setImageResource(images[i]);
        img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        img.setLayoutParams(new GridView.LayoutParams(115,115));
        return img;
    }
}
