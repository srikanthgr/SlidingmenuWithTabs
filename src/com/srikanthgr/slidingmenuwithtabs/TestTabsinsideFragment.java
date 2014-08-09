package com.srikanthgr.slidingmenuwithtabs;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestTabsinsideFragment extends Fragment {
	View rootView;

	public TestTabsinsideFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().getActionBar().setNavigationMode(
				ActionBar.NAVIGATION_MODE_TABS);

		// Apply the layout for the fragment
		rootView = inflater.inflate(R.layout.page_a, container, false);

		getActivity().setTitle("SlidingmenuWithTabs");

		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
				/*
				 * FragmentManager fragmentManager = getFragmentManager();
				 * AppleFragment inputFragment = new AppleFragment();
				 * FragmentTransaction transaction = fragmentManager
				 * .beginTransaction(); transaction.replace(R.id.insideTab,
				 * inputFragment); transaction.addToBackStack(null);
				 * transaction.commit();
				 */
			}

			public void onTabUnselected(ActionBar.Tab tab,
					FragmentTransaction ft) {
				// hide the given tab
			}

			public void onTabReselected(ActionBar.Tab tab,
					FragmentTransaction ft) {
				// probably ignore this event
			}
		};

		// Add 3 tabs, specifying the tab's text and TabListener
		for (int i1 = 0; i1 < 3; i1++) {
			getActivity().getActionBar().addTab(
					getActivity().getActionBar().newTab()
							.setText("Tab " + (i1 + 1))
							.setTabListener(tabListener));
		}

		return rootView;
	}

}