package com.conwayGame.conwaygame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by korolkov on 10/9/13.
 */
public class ImageAdapter extends BaseAdapter implements Serializable {


    private Context mContext;
    private Grid mGrid;



    public ImageAdapter(Context c,Grid grid) {
        mContext = c;
        mGrid    = grid;
    }

    public int getCount() {
        return mGrid.getElementsCount();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(40, 40));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        int imageRes = (mGrid.getElementState(position) == Grid.STATE_ALIVE)?
                R.drawable.ic_launcher:R.drawable.abc_ab_bottom_solid_dark_holo;

        imageView.setImageResource(imageRes);
        return imageView;
    }

}
