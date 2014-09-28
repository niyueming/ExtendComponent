package net.nym.extendcomponent.common;

import android.content.Context;

import net.nym.extendcomponent.entity.Entities;
import net.nym.extendcomponent.entity.Entity;

/**
 * @author nym
 * @date 2014/9/28 0028.
 */
public abstract class BaseAdapter<T extends Entity> extends android.widget.BaseAdapter {
    public final static String TAG = BaseAdapter.class.getSimpleName();
    protected Entities<T> mData;
    protected Context mContext;

    public BaseAdapter(Context context,Entities<T> data)
    {
        this.mContext = context;
        this.mData = data;
    }


    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }


}
