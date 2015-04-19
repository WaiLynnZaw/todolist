package com.wlz.getshitdone.ui;

import android.os.Bundle;

import com.wlz.getshitdone.R;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by WaiLynnZaw on 4/13/15.
 */
public class DrawerActivity extends MaterialNavigationDrawer{
    @Override
    public void init(Bundle bundle) {
        this.disableLearningPattern();
        this.setDrawerHeaderImage(R.drawable.drawer_image);
        this.addSection(newSection("My Notebooks",R.drawable.ic_action_diary,new MyNotebooks()));
        this.addSection(newSection("TODO List",R.drawable.ic_action_done,new TODOFragment()));
        this.addSection(newSection("My Vitamins",R.drawable.ic_action_format_quote,new VitaminsFragment()));
        this.addSection(newSection("My Money Tracker",R.drawable.ic_action_account_balance_wallet,new MoneyTrackerFragment()));
        this.addSection(newSection("About",R.drawable.ic_action_info,new AboutFragment()));

    }
}
