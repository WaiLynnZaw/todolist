package com.wlz.getshitdone.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wlz.getshitdone.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.MITLicense;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

/**
 * Created by WaiLynnZaw on 4/19/15.
 */
public class AboutFragment extends Fragment {
    @InjectView(R.id.txt_osl)
    TextView osl;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about,container,false);
        ButterKnife.inject(this,v);
        osl.setClickable(true);
        changeTextColor(osl);
        osl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOSL();
            }
        });
        return v;

    }

    void showOSL(){
        final Notices notices = new Notices();
        notices.addNotice(new Notice("Material Navigation Drawer", "https://github.com/neokree/MaterialNavigationDrawer", "nerokee", new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Floating Action Button", "https://github.com/makovkastar/FloatingActionButton", "makovkastar", new MITLicense()));
        notices.addNotice(new Notice("ErrorView","https://github.com/xiprox/ErrorView","xiprox",new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("ButterKnife","https://github.com/JakeWharton/butterknife","Jake Wharton",new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("OrmLite","","",new ApacheSoftwareLicense20()));
        notices.addNotice(new Notice("Material-Dialogs","https://github.com/afollestad/material-dialogs","afollestad",new ApacheSoftwareLicense20()));
        new LicensesDialog.Builder(getActivity()).setNotices(notices).setIncludeOwnLicense(true).setTitle("Open Source Licences").build().show();
    }
    private void changeTextColor(final TextView tv){
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        tv.setTextColor(getResources().getColor(R.color.primary));
                        break;
                    case MotionEvent.ACTION_UP:
                        tv.setTextColor(getResources().getColor(R.color.blue));
                        break;
                }
                return false;
            }
        });
    }
}
