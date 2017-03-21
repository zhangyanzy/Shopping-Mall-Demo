package com.tarenwang.shopping_mall_demo.app;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.tarenwang.shopping_mall_demo.R;
import com.tarenwang.shopping_mall_demo.base.BaseFragment;
import com.tarenwang.shopping_mall_demo.fragment.FirstFragment;
import com.tarenwang.shopping_mall_demo.fragment.OtherFragment;
import com.tarenwang.shopping_mall_demo.fragment.SecondFragment;
import com.tarenwang.shopping_mall_demo.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private RadioGroup mdg_main;
    private List<BaseFragment> mBaseFragment;
    //定义选中的Fragemnt的相对应位置
    private int position;
    private Fragment mContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initFragment();
        setListener();
    }

    private void setListener() {
        mdg_main.setOnCheckedChangeListener(new MyOnCheckChangeListener());
        //默认选中第一个
        mdg_main.check(R.id.rdb_fist);
    }

    /**
     * 根据位置得到相应的Fragemnt
     * @return
     */
    public BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }

    class MyOnCheckChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
            switch (checkId){
                case R.id.rdb_fist:
                    position = 0;
                    break;
                case R.id.rdb_second:
                    position = 1;
                    break;
                case R.id.rdb_third:
                    position = 2;
                    break;
                case R.id.rdb_other:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }
            //根据位置得到相应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFragment(mContent,to);
        }
    }

//    private void switchFragment(BaseFragment fragment) {
//        //1得到fragment
//        FragmentManager fm = getSupportFragmentManager();
//        //开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        //替换
//        transaction.replace(R.id.fl_content,fragment);
//        //提交事务
//        transaction.commit();
//    }

    /**
     * from 刚显示的Fragment，马上就要被隐藏了
     * to 马上要切换的fragment，一会要显示
     * @param from
     * @param to
     */
    private void switchFragment(Fragment from,Fragment to) {
        if (from!=to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //切换
            //判断有没有被添加
            if (!to.isAdded()){
                // to没有被添加
                //form隐藏  添加to
                if (from!=null){
                    ft.hide(from);
                }
                if (to!=null){
                    ft.add(R.id.fl_content,to).commit();
                }
            }else {
                // to 已经被添加
                //form隐藏  显示to
                if (from!=null){
                    ft.hide(from);
                }
                if (to!=null){
                    ft.show(to).commit();
                }
            }
        }
    }



    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new FirstFragment());
        mBaseFragment.add(new SecondFragment());
        mBaseFragment.add(new ThirdFragment());
        mBaseFragment.add(new OtherFragment());
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mdg_main = (RadioGroup) findViewById(R.id.rg_main);
    }
}
