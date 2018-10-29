package edu.cofc.japanesestudytool.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.R;


public class CheckBoxDropDownSpinnerAdapter extends BaseAdapter
{
    private ArrayList<Boolean> checkboxes;
    private Context context;
    private LayoutInflater mInflater;
    private final String[] values = new String[]{"blank","extra","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
            "20","21","22","23"};
    public CheckBoxDropDownSpinnerAdapter(Context context)
    {
        this.context=context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        checkboxes = getInitialList();
    }
    @Override
    public int getCount() {
        return checkboxes.size();
    }

    @Override
    public Object getItem(int position) {
        return checkboxes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        View rowView = mInflater.inflate(R.layout.lesson_spinner_list_item, parent,false);
        final CheckBox checkBox = rowView.findViewById(R.id.spinnerCheckBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked())
                {
                    checkboxes.set(position,true);
                }
                else
                {
                    checkboxes.set(position,false);
                }
            }
        });
        checkBox.setText(values[position]);
        checkBox.setChecked(checkboxes.get(position));
        if(position==0)
        {
            checkBox.setVisibility(View.INVISIBLE);
        }

        return rowView;
    }

    private ArrayList<Boolean> getInitialList()
    {
        ArrayList<Boolean> list = new ArrayList<>();
        for(int i=0;i<values.length;i++)
        {
            Boolean bool = false;
            list.add(bool);
        }
        return list;
    }

    public void refreshList()
    {
        checkboxes= getInitialList();
    }

    public ArrayList<Integer> getLessonsArrayList()
    {
        ArrayList<Integer> rtnval = new ArrayList<>();
        for(int i=1; i<values.length;i++)
        {
            if(checkboxes.get(i))
            {
                rtnval.add(i-1);
            }
        }
        return rtnval;
    }

    public int[] getLessonArray()
    {
        int lessonCounter=0;

        for(int i=1; i<values.length;i++)
        {
            if(checkboxes.get(i))
            {
                lessonCounter++;
            }
        }

        int[] rtnval = new int[lessonCounter];
        int index=0;
        for(int i=1; i<values.length;i++)
        {
            if(checkboxes.get(i))
            {
                rtnval[index] = i-1;
                index++;
            }
        }

        return rtnval;
    }

    public void setCheckedFromArrayList(ArrayList<Integer> lessons)
    {
        refreshList();
        for(int i =0; i<lessons.size(); i++)
        {
            checkboxes.set(lessons.get(i)+1,true);
        }
    }
}
