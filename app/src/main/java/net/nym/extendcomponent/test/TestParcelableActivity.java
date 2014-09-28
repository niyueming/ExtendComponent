package net.nym.extendcomponent.test;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import net.nym.extendcomponent.R;
import net.nym.extendcomponent.common.BaseAdapter;
import net.nym.extendcomponent.entity.Entities;
import net.nym.extendcomponent.entity.Entity;
import net.nym.extendcomponent.entity.ProductInfo;

public class TestParcelableActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_parcelable);
        ListView listView = (ListView) findViewById(R.id.listView);
        Entities<ProductInfo> data = new Entities<ProductInfo>();
        for (int i = 0 ; i < 10;i ++)
        {
            ProductInfo info = new ProductInfo();
            info.setName("name" + i);
            data.add(info);
        }
        listView.setAdapter(new MBaseAdapter(this,data));


    }

    private class MBaseAdapter extends BaseAdapter<ProductInfo> {
        public MBaseAdapter(Context context, Entities<ProductInfo> data) {
            super(context, data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = new TextView(mContext);
            TextView text = (TextView) convertView;
            text.setPadding(10,10,10,10);
            text.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            text.setText(mData.get(position).toString());
            return convertView;
        }
    };
}
