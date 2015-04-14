package com.wlz.getshitdone;

import android.os.Bundle;

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
    }
}
