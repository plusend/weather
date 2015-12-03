package com.plusend.weather.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plusend.weather.R;
import com.plusend.weather.bean.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private static final String TAG = "SearchAdapter";

    private List<City> mDataSet = new ArrayList<>();

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getLayoutPosition() + " clicked.");
                }
            });
            textView = (TextView) v.findViewById(R.id.tv_search_item);
        }

        public TextView getTextView() {
            return textView;
        }


    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    private View.OnClickListener mOnClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param cityList String[] containing the data to populate views to be used by RecyclerView.
     */
    public SearchAdapter(List<City> cityList) {
        this.mDataSet = cityList;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextView().setText(mDataSet.get(position).getName());
        viewHolder.getTextView().setTag(mDataSet.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnClickListener != null) {
            viewHolder.getTextView().setOnClickListener(mOnClickListener);
        }
        // END_INCLUDE(recyclerViewOnBindViewHolder)
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void update(List<City> cityList) {
        this.mDataSet = cityList;
        notifyDataSetChanged();
    }
}
