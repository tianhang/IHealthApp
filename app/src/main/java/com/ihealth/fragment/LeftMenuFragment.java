package com.ihealth.fragment;

import java.util.ArrayList;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ihealth.MainActivity;
import com.ihealth.R;
import com.ihealth.base.impl.NewsCenterPager;
import com.ihealth.domain.NewsData;
import com.ihealth.domain.NewsData.NewsMenuData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 侧边栏
 * 
 * @author Kevin
 * 
 */
public class LeftMenuFragment extends BaseFragment {

	private ViewPager mViewPager;
	@ViewInject(R.id.rg_group)
	private RadioGroup rgGroup;

	@ViewInject(R.id.lv_list)
	private ListView lvList;
	private ArrayList<NewsMenuData> mMenuList;

	private int mCurrentPos;// 当前被点击的菜单项
	//private MenuAdapter mAdapter;

	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	public void initData() {

		// 监听RadioGroup的选择事件
		rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.rb_home:
						// mViewPager.setCurrentItem(0);// 设置当前页面
						setCurrentMenuDetailPager(0);// 去掉切换页面的动画
						break;
					case R.id.rb_news:
						setCurrentMenuDetailPager(1);// 设置当前页面
						break;
					case R.id.rb_smart:
						setCurrentMenuDetailPager(2);// 设置当前页面
						break;
					case R.id.rb_gov:
						setCurrentMenuDetailPager(3);// 设置当前页面
						break;
					case R.id.rb_setting:
						setCurrentMenuDetailPager(4);// 设置当前页面
						break;

					default:
						break;
				}
			}
		});

//				lvList.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				mCurrentPos = position;
//				//mAdapter.notifyDataSetChanged();
//
//				setCurrentMenuDetailPager(position);
//
//				toggleSlidingMenu();// 隐藏
//			}
//		});
	}

	/**
	 * 切换SlidingMenu的状态
	 * 
	 * @param
	 */
	protected void toggleSlidingMenu() {
		MainActivity mainUi = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUi.getSlidingMenu();
		slidingMenu.toggle();// 切换状态, 显示时隐藏, 隐藏时显示
	}

	/**
	 * 设置当前菜单详情页
	 * 
	 * @param position
	 */
	protected void setCurrentMenuDetailPager(int position) {
		MainActivity mainUi = (MainActivity) mActivity;
		ContentFragment fragment = mainUi.getContentFragment();// 获取主页面fragment
		mViewPager = fragment.getmViewPager();
		mViewPager.setCurrentItem(position, false);
		toggleSlidingMenu();// 隐藏
		Log.i("tianhang","choose");
		//mViewPager
		//NewsCenterPager pager = fragment.getNewsCenterPager();// 获取新闻中心页面
		//pager.setCurrentMenuDetailPager(position);// 设置当前菜单详情页
	}

	// 设置网络数据
	public void setMenuData(NewsData data) {
		// System.out.println("侧边栏拿到数据啦:" + data);

	}


}
