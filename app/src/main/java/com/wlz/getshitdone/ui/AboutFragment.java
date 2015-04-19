package com.wlz.getshitdone.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wlz.getshitdone.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.psdev.licensesdialog.LicensesDialog;

/**
 * Created by WaiLynnZaw on 4/19/15.
 */
public class AboutFragment extends Fragment {
    @InjectView(R.id.button)
    Button osl;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about,container,false);
        ButterKnife.inject(this,v);
        osl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOSL();
            }
        });
        return v;

    }

   private void showOSL(){
       new LicensesDialog.Builder(getActivity()).setNotices(R.raw.notices).build().show();
   }
}
