package com.demo.colin.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PreCSEActivity extends AppCompatActivity {

    // 创建adapter
    private CheckBoxListAdapter checkBoxListAdapter;
    // 创建选项的arrayList
    private ArrayList<String> contextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_cse);

        // 找到List View
        ListView listView = findViewById(R.id.ap_checkbox_list);
        //选项 array List
        contextList = new ArrayList<>();
        // 选项插入
        initData();
        // 记录的选项选中的Map
        HashMap<Integer, Boolean> satisfyClass = new HashMap<>();
        // 实体化Adapter
        checkBoxListAdapter = new CheckBoxListAdapter(contextList,this, satisfyClass);
        // 关联ListView 和adapter
        listView.setAdapter(checkBoxListAdapter);
        // 选项被选中的反应函数
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBoxListAdapter.ViewHolder holder = (CheckBoxListAdapter.ViewHolder) view.getTag();
                holder.cb.toggle();
                CheckBoxListAdapter.getIsSelected().put(position, holder.cb.isChecked());
                dataChanged();
            }
        });
        // 下一步的按钮设置 和响应函数
        Button nextStep = findViewById(R.id.ap_confirm_button);
        nextStep.setOnClickListener(nextStepListener);
    }

    private void initData(){
        // 加入ListView 的选项
        contextList.add("我的ap可以换掉CSE1223");
        contextList.add("我的ap可以换掉MATH1151");
        contextList.add("我的ap可以换掉MATH1152");
        contextList.add("我的ap可以换掉ENG1110");
        contextList.add("我的ap可以换掉PHY1250");
        contextList.add("我的ap可以换掉PHY1251");
    }

    // 选项变化后 更新展示的内容
    private void dataChanged(){
        checkBoxListAdapter.notifyDataSetChanged();
    }

    // 下一步按键的响应函数
    Button.OnClickListener nextStepListener= new Button.OnClickListener() {
        public void onClick(View v) {
            //跳转下一个的页面
            Intent jumpPre = new Intent(com.demo.colin.demo.PreCSEActivity.this, EnglishMath.class);
            // pass set 到下一个页面
            jumpPre.putExtra("Set",getSatisfySet(checkBoxListAdapter.getSelectedMap()));
            //jump 页面
            startActivity(jumpPre);
            // 关闭当前页面
            PreCSEActivity.this.finish();
        }
    };

    private HashSet<String> getSatisfySet(HashMap<Integer,Boolean> map) {
        HashSet<String> set = new HashSet<>();
        if(map.get(0)) set.add("CSE1223");

        if(map.get(1))set.add("MATH1151");

        if(map.get(2))set.add("MATH1152");

        if(map.get(3)) set.add("ENG1110");

        if(map.get(4)) set.add("PHY1250");

        if(map.get(5)) set.add("PHY1251");

        return set;
    }



}
