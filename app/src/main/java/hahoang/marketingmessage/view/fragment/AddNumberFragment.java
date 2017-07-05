package hahoang.marketingmessage.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import hahoang.marketingmessage.R;
import hahoang.marketingmessage.view.activity.MapActivity;

/**
 * Created by Hoang Ha on 7/4/2017.
 */

public class AddNumberFragment extends Fragment implements View.OnClickListener {
    private ImageView ivLocation;
    private Intent intent;
    public static int MAP_REQUEST = 1;
    private EditText etName, etNumber, etEmail, etAddress, etBirthday, etGroup, etDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_number_activity, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.add_number));
        ivLocation = (ImageView) view.findViewById(R.id.iv_choose_location);
        ivLocation.setOnClickListener(this);
        etName = (EditText) view.findViewById(R.id.et_name);
        etNumber = (EditText) view.findViewById(R.id.et_number);
        etEmail = (EditText) view.findViewById(R.id.et_email);
        etAddress = (EditText) view.findViewById(R.id.et_address);
        etBirthday = (EditText) view.findViewById(R.id.et_birthday);
        etGroup = (EditText) view.findViewById(R.id.et_group);
        etDescription = (EditText) view.findViewById(R.id.et_description);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_choose_location:
                intent = new Intent(getContext(), MapActivity.class);
                break;
        }
        if (intent != null) {
            startActivityForResult(intent, MAP_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAP_REQUEST && resultCode == 1) {
            if (data.getStringExtra("address") != null)
                etAddress.setText(data.getStringExtra("address"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
