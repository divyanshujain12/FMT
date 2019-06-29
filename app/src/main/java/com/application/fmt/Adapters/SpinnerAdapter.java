package com.application.fmt.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.application.fmt.Models.Country;
import com.application.fmt.R;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<Country> {

    private final LayoutInflater mInflater;
    private ArrayList<Country> countries = new ArrayList<>();

    public SpinnerAdapter(@NonNull Context context) {
        super(context, 0);
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent, false);
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent, true);
    }

    private View createItemView(int position, View convertView, ViewGroup parent, boolean dropdown) {
        final View view = mInflater.inflate(R.layout.custom_text_view_regular_adapter, parent, false);
        Country country = countries.get(position);
        TextView customTV = view.findViewById(R.id.customTV);
        if (!dropdown) {
            customTV.setTextColor(getContext().getResources().getColor(R.color.black));
        }
        customTV.setText(country.getCountry());

        return view;
    }

    public void addItems(ArrayList<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.countries.size();
    }
}