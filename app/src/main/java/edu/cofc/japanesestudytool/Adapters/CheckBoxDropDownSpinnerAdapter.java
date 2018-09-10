package edu.cofc.japanesestudytool.Adapters;

import android.widget.CheckBox;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import edu.cofc.japanesestudytool.R;

public class CheckBoxDropDownSpinnerAdapter extends ArrayAdapter<String>
{
    CheckBox checkbox;
    Context mContext;
    String[] values;
    public CheckBoxDropDownSpinnerAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        this.mContext = context;
        values=objects;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.lesson_spinner_list_item, null);
            checkbox = convertView.findViewById(R.id.spinnerCheckBox);
            convertView.setTag(checkbox);
        } else {
            checkbox = (CheckBox)convertView.getTag();
        }

        checkbox.setText(values[position]);

        return convertView;
    }


}
