/**
 * Copyright (c) 2015 Translation Exchange, Inc. All rights reserved.
 *
 *  _______                  _       _   _             ______          _
 * |__   __|                | |     | | (_)           |  ____|        | |
 *    | |_ __ __ _ _ __  ___| | __ _| |_ _  ___  _ __ | |__  __  _____| |__   __ _ _ __   __ _  ___
 *    | | '__/ _` | '_ \/ __| |/ _` | __| |/ _ \| '_ \|  __| \ \/ / __| '_ \ / _` | '_ \ / _` |/ _ \
 *    | | | | (_| | | | \__ \ | (_| | |_| | (_) | | | | |____ >  < (__| | | | (_| | | | | (_| |  __/
 *    |_|_|  \__,_|_| |_|___/_|\__,_|\__|_|\___/|_| |_|______/_/\_\___|_| |_|\__,_|_| |_|\__, |\___|
 *                                                                                        __/ |
 *                                                                                       |___/
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.translationexchange.samples.wammer.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.translationexchange.android.activities.LanguageSelectorActivity;
import com.translationexchange.android.activities.LocalizedActivity;
import com.translationexchange.android.Tml;
import com.translationexchange.samples.wammer.R;
import com.translationexchange.samples.wammer.adapters.NavDrawerListAdapter;
import com.translationexchange.samples.wammer.fragments.NewsfeedFragment;
import com.translationexchange.samples.wammer.models.NavDrawerItem;

public class MainActivity extends LocalizedActivity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private String currentViewTitle;
	
	private ArrayList<NavDrawerItem> navDrawerItems;
	
	private List<NavDrawerItem> getNavDrawerItems() {
		if (navDrawerItems == null) {
			navDrawerItems = new ArrayList<NavDrawerItem>();
			navDrawerItems.add(new NavDrawerItem("Newsfeed", NewsfeedFragment.class.getName()));
			navDrawerItems.add(new NavDrawerItem("People"));
			navDrawerItems.add(new NavDrawerItem("Add Coworkers"));
			navDrawerItems.add(new NavDrawerItem("All Company"));
			navDrawerItems.add(new NavDrawerItem("Engineering"));
			navDrawerItems.add(new NavDrawerItem("More Groups"));
			navDrawerItems.add(new NavDrawerItem("My Profile"));
			navDrawerItems.add(new NavDrawerItem("My Networks"));
			navDrawerItems.add(new NavDrawerItem("Settings"));
			navDrawerItems.add(new NavDrawerItem("Logout"));
		}
		return navDrawerItems;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		currentViewTitle = "Wammer";
		
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		mDrawerList.setAdapter(new NavDrawerListAdapter(getApplicationContext(), getNavDrawerItems()));
		mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				displayView(position);
			}
		});
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
//		getActionBar().setHomeButtonEnabled(true);
	
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(Tml.translate(currentViewTitle));
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(Tml.translate("Main Menu"));
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	
		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
			case R.id.change_language:
		    	startActivity(new Intent(MainActivity.this, LanguageSelectorActivity.class));
				return true;
			case R.id.inline_translator:
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Tml.getSession().getApplication().getHost() + "/mobile/login")));				
//		    	startActivity(new Intent(MainActivity.this, InAppTranslatorActivity.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		MenuItem menuItem = (MenuItem) menu.findItem(R.id.change_language);
		menuItem.setVisible(!drawerOpen);
		menuItem.setTitle("Change Language");
		menuItem = (MenuItem) menu.findItem(R.id.inline_translator);
		menuItem.setVisible(!drawerOpen);
		menuItem.setTitle("Translate");
		return super.onPrepareOptionsMenu(menu);
	}
	
	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		
		NavDrawerItem item = getNavDrawerItems().get(position);
		Fragment fragment = item.getFragment();
		
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
			currentViewTitle = item.getTitle();
			getActionBar().setTitle(Tml.translate(currentViewTitle));
			
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			// Log.e("MainActivity", "Error in creating fragment");
		}
	}
	
	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public void onLocalize() {
		((NavDrawerListAdapter)mDrawerList.getAdapter()).notifyDataSetChanged();
		getActionBar().setTitle(Tml.translate(currentViewTitle));
	}

	@Override
	public void registerSources() {
	}
}

