package my.example.myexpandablelistview;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by korolkov on 10/9/13.
 */
public class ListViewAdapter extends BaseExpandableListAdapter {

    private ArrayList<ArrayList<String>> mLessons;
    private Context mContext;


    ListViewAdapter(Context context,ArrayList<ArrayList<String>> lessons){
        mLessons = lessons;
        mContext = context;

    }


    @Override
    public int getGroupCount() {
        return mLessons.size();
    }

    @Override
    public int getChildrenCount(int lessonPosition) {
        return mLessons.get(lessonPosition).size();
    }

    @Override
    public Object getGroup(int lessonPosition) {
        return mLessons.get(lessonPosition);
    }

    @Override
    public Object getChild(int lessonPosition, int activityPosition) {
        return mLessons.get(lessonPosition).get(activityPosition);
    }

    @Override
    public long getGroupId(int lessonPosition) {
        return lessonPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int lessonsPosition, boolean isExpanded, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lessons_view, null);
        }
        String title = mLessons.get(lessonsPosition).get(0);
        TextView textGroup = (TextView) view.findViewById(R.id.textGroup);
        textGroup.setText(title.substring(title.indexOf("lesson"),title.lastIndexOf(".")));

        return view;

    }

    @Override
    public View getChildView(int lessonPosition, int activityPosition, boolean isLastActivity,
                             View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_view, null);
        }

        TextView textChild = (TextView) view.findViewById(R.id.textChild);
        String title = mLessons.get(lessonPosition).get(activityPosition);
        textChild.setText(title.substring(title.lastIndexOf(".")+1));

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
