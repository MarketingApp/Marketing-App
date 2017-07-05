package hahoang.marketingmessage.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hahoang.marketingmessage.R;

/**
 * Created by Hoang Ha on 7/4/2017.
 */

public class ManagerGroupFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.manager_group,container,false);

        return view;
    }
}
