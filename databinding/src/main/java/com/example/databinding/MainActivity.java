package com.example.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.databinding.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setClicklistener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_setdata:
                setData();
                break;

            default:
                break;
        }
    }

    public void setData() {
        UserBean userBean = new UserBean("liubin", "13", "guangzhou");
        mainBinding.setUser(userBean);
        List<UserBean> list = new ArrayList<UserBean>();
        list.add(userBean);
        list.add(new UserBean("liub", "12", "shaoguang"));
        list.add(new UserBean("liub1", "12", "heyuan"));
        HashMap<String, UserBean> map = new HashMap<>();
        map.put("liu", new UserBean("liu1", "1", "one"));
        map.put("liub", new UserBean("liu2", "1", "two"));
        map.put("liu1", new UserBean("liu3", "1", "three"));
        mainBinding.setMap(map);
        mainBinding.setList(list);
    }

}
