package com.srikanthgr.slidingmenuwithtabs;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SlidingMenuWithTabs extends Activity {

	// Within which the entire activity is enclosed
	DrawerLayout mDrawerLayout;

	// ListView represents Navigation Drawer
	ListView drawerLeftMenu, drawerMenuRight;

	// ActionBarDrawerToggle indicates the presence of Navigation Drawer in the
	// action bar
	ActionBarDrawerToggle mDrawerToggle;

	// Title of the action bar
	String mTitle = "";
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding_menu_with_tabs);

		actionBar = getActionBar();

		
		mTitle = (String) getTitle();

		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		drawerLeftMenu = (ListView) findViewById(R.id.left_menu);
		drawerMenuRight = (ListView) findViewById(R.id.right_menu);

		// Getting reference to the ActionBarDrawerToggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				// getActionBar().setTitle(mTitle);
				// invalidateOptionsMenu();
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				// getActionBar().setTitle("Select a river");
				// invalidateOptionsMenu();
				actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

			}

		};

		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Creating an ArrayAdapter to add items to the listview drawerLeftMenu
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.drawer_list_item, getResources()
						.getStringArray(R.array.left_menu));

		// Setting the adapter on drawerLeftMenu

		ArrayAdapter<String> rigthMenuadapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.drawer_list_item, getResources()
						.getStringArray(R.array.right_menu));
		drawerLeftMenu.setAdapter(adapter);
		drawerMenuRight.setAdapter(rigthMenuadapter);
		// Enabling Home button
		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Setting item click listener for the listview drawerLeftMenu
		drawerLeftMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Closing the drawer
				mDrawerLayout.closeDrawer(drawerLeftMenu);

			}
		});
		
		drawerMenuRight.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Closing the drawer
				mDrawerLayout.closeDrawer(drawerMenuRight);

			}
		});

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	/** Handling the touch event of app icon */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			boolean drawerOpenRight = mDrawerLayout
					.isDrawerOpen(drawerMenuRight);
			if (drawerOpenRight) {
				mDrawerLayout.closeDrawer(drawerMenuRight);
			}

			return true;
		} else {
			switch (item.getItemId()) {
			case R.id.action_settings:
				boolean drawerOpen = mDrawerLayout
						.isDrawerOpen(drawerMenuRight);
				if (drawerOpen) {
					mDrawerLayout.closeDrawer(drawerMenuRight);
				} else if (mDrawerLayout.isDrawerOpen(drawerLeftMenu)) {
					mDrawerLayout.closeDrawer(drawerLeftMenu);

				} else {
					mDrawerLayout.openDrawer(drawerMenuRight);
				}

				
			}
		}

		return super.onOptionsItemSelected(item);
	}

	/** Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(drawerLeftMenu);
		// getActionBar().hide();
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class CustomTabListener<T extends Fragment> implements TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		public CustomTabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// Nothing special to do here for this application
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			/*
			 * if (mFragment == null) { //mFragment =
			 * Fragment.instantiate(mActivity, mClass.getName());
			 * 
			 * ft.replace(android.R.id.content, new AppleFragment(), mTag); }
			 * else { ft.attach(mFragment); }
			 */
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			/*
			 * if (mFragment != null) ft.detach(mFragment);
			 */
		}
	}
}
