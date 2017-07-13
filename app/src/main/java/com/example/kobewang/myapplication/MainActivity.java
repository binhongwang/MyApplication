package com.example.kobewang.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = "MainActivity";

    private ExpandableListView expandableListView;

    private List<String> group_list;

    private List<String> item_lt;

    private List<List<String>> item_list;

    private List<List<Integer>> item_list2;

    private List<List<Integer>> gr_list2;

    private TextView mTxt;

    private MyExpandableListViewAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxt = (TextView) findViewById(R.id.txt);
        group_list = new ArrayList<String>();
        group_list.add("我的好友");
        group_list.add("我的家人");
        group_list.add("兄弟姐妹");
        group_list.add("我的同学");

        item_lt = new ArrayList<String>();
        item_lt.add("张三丰");
        item_lt.add("董存瑞");
        item_lt.add("李大钊");

        item_list = new ArrayList<List<String>>();
        item_list.add(item_lt);
        item_list.add(item_lt);
        item_list.add(item_lt);
        item_list.add(item_lt);

        List<Integer> tmp_list = new ArrayList<Integer>();
        tmp_list.add(R.drawable.ic_new);
        tmp_list.add(R.drawable.ic_new);
        tmp_list.add(R.drawable.ic_new);
        tmp_list.add(R.drawable.ic_new);

        item_list2 = new ArrayList<List<Integer>>();
        item_list2.add(tmp_list);
        item_list2.add(tmp_list);
        item_list2.add(tmp_list);
        item_list2.add(tmp_list);

        List<Integer> gr_list = new ArrayList<Integer>();
        gr_list.add(R.drawable.ic_new);
        gr_list.add(R.drawable.ic_new);
        gr_list.add(R.drawable.ic_new);
        gr_list.add(R.drawable.ic_new);

        gr_list2 = new ArrayList<List<Integer>>();
        gr_list2.add(gr_list);
        gr_list2.add(gr_list);
        gr_list2.add(gr_list);
        gr_list2.add(gr_list);

        expandableListView = (ExpandableListView)findViewById(R.id.expendlist);
        expandableListView.setGroupIndicator(null);

        // 监听组点击
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
            {
                if (item_list.get(groupPosition).isEmpty())
                {
                    return true;
                }
                return false;
            }
        });

        // 监听每个分组里子控件的点击事件
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {

                Toast.makeText(MainActivity.this, "group=" + groupPosition + "---child=" + childPosition + "---" + item_list.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        adapter = new MyExpandableListViewAdapter(this);

        expandableListView.setAdapter(adapter);
        expandableListView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
            }
        });
        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                 Log.v("kobe","first-->"+ i + " type-->"+ adapter.getGroupId(i));

                if(expandableListView.getChildAt(i) instanceof View){
                    Log.v("kobe","first-->"+ expandableListView.getChildAt(i).findViewById(R.id.group));
                    if(expandableListView.getChildAt(i).findViewById(R.id.group)!= null){
                        String str = ((TextView) expandableListView.getChildAt(i).findViewById(R.id.txt)).getText().toString();
                        Log.v("kobe","first-->"+ str);
                        mTxt.setText(str);
                    }
                };
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    // 用过ListView的人一定很熟悉，只不过这里是BaseExpandableListAdapter
    class MyExpandableListViewAdapter extends BaseExpandableListAdapter
    {

        private Context context;

        public MyExpandableListViewAdapter(Context context)
        {
            this.context = context;
        }

        /**
         *
         * 获取组的个数
         *
         * @return
         * @see android.widget.ExpandableListAdapter#getGroupCount()
         */
        @Override
        public int getGroupCount()
        {
            return group_list.size();
        }

        /**
         *
         * 获取指定组中的子元素个数
         *
         * @param groupPosition
         * @return
         * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
         */
        @Override
        public int getChildrenCount(int groupPosition)
        {
            return item_list.get(groupPosition).size();
        }

        /**
         *
         * 获取指定组中的数据
         *
         * @param groupPosition
         * @return
         * @see android.widget.ExpandableListAdapter#getGroup(int)
         */
        @Override
        public Object getGroup(int groupPosition)
        {
            return group_list.get(groupPosition);
        }

        /**
         *
         * 获取指定组中的指定子元素数据。
         *
         * @param groupPosition
         * @param childPosition
         * @return
         * @see android.widget.ExpandableListAdapter#getChild(int, int)
         */
        @Override
        public Object getChild(int groupPosition, int childPosition)
        {
            return item_list.get(groupPosition).get(childPosition);
        }

        /**
         *
         * 获取指定组的ID，这个组ID必须是唯一的
         *
         * @param groupPosition
         * @return
         * @see android.widget.ExpandableListAdapter#getGroupId(int)
         */
        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }

        /**
         *
         * 获取指定组中的指定子元素ID
         *
         * @param groupPosition
         * @param childPosition
         * @return
         * @see android.widget.ExpandableListAdapter#getChildId(int, int)
         */
        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        /**
         *
         * 组和子元素是否持有稳定的ID,也就是底层数据的改变不会影响到它们。
         *
         * @return
         * @see android.widget.ExpandableListAdapter#hasStableIds()
         */
        @Override
        public boolean hasStableIds()
        {
            return true;
        }

        /**
         *
         * 获取显示指定组的视图对象
         *
         * @param groupPosition 组位置
         * @param isExpanded 该组是展开状态还是伸缩状态
         * @param convertView 重用已有的视图对象
         * @param parent 返回的视图对象始终依附于的视图组
         * @return
         * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean, android.view.View,
         *      android.view.ViewGroup)
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
        {
            GroupHolder groupHolder = null;
            if (convertView == null)
            {
                convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_group, null);
                groupHolder = new GroupHolder();
                groupHolder.txt = (TextView)convertView.findViewById(R.id.txt);
                groupHolder.img = (ImageView)convertView.findViewById(R.id.img);
                convertView.setTag(groupHolder);
            }
            else
            {
                groupHolder = (GroupHolder)convertView.getTag();
            }

            if (!isExpanded)
            {
                groupHolder.img.setBackgroundResource(R.drawable.ic_new);
            }
            else
            {
                groupHolder.img.setBackgroundResource(R.drawable.ic_new);
            }

            groupHolder.txt.setText(group_list.get(groupPosition));
            return convertView;
        }

        /**
         *
         * 获取一个视图对象，显示指定组中的指定子元素数据。
         *
         * @param groupPosition 组位置
         * @param childPosition 子元素位置
         * @param isLastChild 子元素是否处于组中的最后一个
         * @param convertView 重用已有的视图(View)对象
         * @param parent 返回的视图(View)对象始终依附于的视图组
         * @return
         * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View,
         *      android.view.ViewGroup)
         */
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
        {
            ItemHolder itemHolder = null;
            if (convertView == null)
            {
                convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_item, null);
                itemHolder = new ItemHolder();
                itemHolder.txt = (TextView)convertView.findViewById(R.id.txt);
                itemHolder.img = (ImageView)convertView.findViewById(R.id.img);
                convertView.setTag(itemHolder);
            }
            else
            {
                itemHolder = (ItemHolder)convertView.getTag();
            }
            itemHolder.txt.setText(item_list.get(groupPosition).get(childPosition));
            itemHolder.img.setBackgroundResource(item_list2.get(groupPosition).get(childPosition));
            return convertView;
        }

        /**
         *
         * 是否选中指定位置上的子元素。
         *
         * @param groupPosition
         * @param childPosition
         * @return
         * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
         */
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition)
        {
            return true;
        }

    }

    class GroupHolder
    {
        public TextView txt;

        public ImageView img;
    }

    class ItemHolder
    {
        public ImageView img;

        public TextView txt;
    }

}
