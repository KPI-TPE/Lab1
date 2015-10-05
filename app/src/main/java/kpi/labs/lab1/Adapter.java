package kpi.labs.lab1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Beohrn on 13.09.2015.
 */
public class Adapter extends ArrayAdapter<String> {

    private class ViewHolder {
        TextView textView;
    }

    public static String[] strings;
    private Context context;

    public Adapter(Context context, int resource) {
        super(context, resource, strings);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(context);
            textView = (TextView) convertView;
        }

        textView.setText(strings[position]);
        return  convertView;
    }
}
