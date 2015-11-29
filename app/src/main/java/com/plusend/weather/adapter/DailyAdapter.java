package com.plusend.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.plusend.weather.R;
import com.plusend.weather.bean.City;
import com.plusend.weather.bean.Weather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {
    private static final String TAG = "DailyAdapter";

    private List<Weather.HeWeather.DailyForecastEntity> mDataSet = new ArrayList<>();

    private Context context;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_day;
        private final ImageView iv_day;
        private final TextView tv_day_scope;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getLayoutPosition() + " clicked.");
                }
            });
            tv_day = (TextView) v.findViewById(R.id.tv_day);
            iv_day = (ImageView) v.findViewById(R.id.iv_day);
            tv_day_scope = (TextView) v.findViewById(R.id.tv_day_scope);
        }

        public TextView getTv_day() {
            return tv_day;
        }

        public ImageView getIv_day() {
            return iv_day;
        }

        public TextView getTv_day_scope() {
            return tv_day_scope;
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
     * @param dailyList String[] containing the data to populate views to be used by RecyclerView.
     */
    public DailyAdapter(Context context, List<Weather.HeWeather.DailyForecastEntity> dailyList) {
        this.context = context;
        this.mDataSet = dailyList;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.daily_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // 转换日期为星期
        //把string转化为date
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(mDataSet.get(position).getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);

        viewHolder.getTv_day().setText(week);
        String icon = "icon" + mDataSet.get(position).getCond().getCodeD();
        int ivWeatherId = context.getResources().getIdentifier(icon, "mipmap", "com.plusend.weather");
        viewHolder.getIv_day().setImageResource(ivWeatherId);
        viewHolder.getTv_day_scope().setText(mDataSet.get(position).getTmp().getMax() + "~"
                + mDataSet.get(position).getTmp().getMin() + "°");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void update(List<Weather.HeWeather.DailyForecastEntity> dailyList) {
        this.mDataSet = dailyList;
        notifyDataSetChanged();
    }
}
