package com.example.psit_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ParentRow> parentRowList;
    private ArrayList<ParentRow> originalList;

    public MyListAdapter(Context context, ArrayList<ParentRow> parentRowList) {
        this.context = context;
        this.parentRowList = new ArrayList<ParentRow>();
        this.parentRowList.addAll(parentRowList);
        this.originalList = new ArrayList<ParentRow>();
        this.originalList.addAll(parentRowList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ChildRow> childRowList = parentRowList.get(groupPosition).getChildList();
        return childRowList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

        ChildRow childRow = (ChildRow) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_row, null);
        }

        TextView code = (TextView) view.findViewById(R.id.code);
        TextView ch_id = (TextView) view.findViewById(R.id.ch_id);
        TextView grp_id = (TextView) view.findViewById(R.id.grp_id);
        TextView hidden_id=(TextView) view.findViewById(R.id.hidden_id);

        hidden_id.setText(childRow.getFid().trim());
        code.setText(childRow.getCode().trim());
        ch_id.setText(String.valueOf(childRow.getCh_id()));
        grp_id.setText(String.valueOf(childRow.getGrp_id()));
        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<ChildRow> childRowList = parentRowList.get(groupPosition).getChildList();
        return childRowList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentRowList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return parentRowList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view, ViewGroup parent) {

        ParentRow parentRow = (ParentRow) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.group_row, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.code);
        heading.setText(parentRow.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public ArrayList<ParentRow> filterData(String query) {

        query = query.toLowerCase();
        parentRowList.clear();

        if (query.isEmpty()) {
            parentRowList.addAll(originalList);
        } else {

            for (ParentRow parentRow : originalList) {

                ArrayList<ChildRow> childRowList = parentRow.getChildList();
                ArrayList<ChildRow> newList = new ArrayList<ChildRow>();
                for (ChildRow childRow : childRowList) {
                    if (childRow.getCode().toLowerCase().contains(query) ||
                            childRow.getCode().toLowerCase().contains(query)) {
                        newList.add(childRow);
                    }
                }
                if (newList.size() > 0) {
                    ParentRow nParentRow = new ParentRow(parentRow.getName(), newList);
                    parentRowList.add(nParentRow);
                }
            }
        }

        notifyDataSetChanged();
        return parentRowList;
    }

}
