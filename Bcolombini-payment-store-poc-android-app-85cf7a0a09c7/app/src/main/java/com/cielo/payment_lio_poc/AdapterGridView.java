package com.cielo.payment_lio_poc;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class AdapterGridView extends BaseAdapter {
    private Context mContext;
    private List<Product> itemList = new ArrayList<>();
    private MainActivity mainActivity = null;

    public AdapterGridView(Context c, List<Product> itemList) {
        mContext = c;
        this.itemList = itemList;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        grid = inflater.inflate(R.layout.adapter_card, null);

        ImageView appIcon = (ImageView) grid.findViewById(R.id.product_image);
        TextView productName = (TextView) grid.findViewById(R.id.product_name);
        TextView productPrice = (TextView) grid.findViewById(R.id.product_price);
        Button button = (Button) grid.findViewById(R.id.buy_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.orderRequest();
            }
        });
        Product product = itemList.get(position);

        appIcon.setImageResource(product.getImage());
        productName.setText(product.getName());
        productPrice.setText("");

        return grid;
    }
}
