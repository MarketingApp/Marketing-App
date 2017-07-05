package hahoang.marketingmessage.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import hahoang.marketingmessage.R;
import hahoang.marketingmessage.model.Group;
import hahoang.marketingmessage.presenter.AddGroupPresenter;
import hahoang.marketingmessage.view.PushLoad;

/**
 * Created by Hoang Ha on 7/4/2017.
 */

public class AddGroupFragment extends Fragment implements View.OnClickListener, PushLoad {
    private EditText etName, etDescription;
    private Button btAddGroup;
    private AddGroupPresenter addGroupPresenter;
    private Group group;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_group_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etName = (EditText) view.findViewById(R.id.et_group_name);
        etDescription = (EditText) view.findViewById(R.id.et_group_description);
        btAddGroup = (Button) view.findViewById(R.id.bt_add_group);
        btAddGroup.setOnClickListener(this);
        getActivity().setTitle(getString(R.string.add_group));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_add_group:
                if (!etName.getText().toString().isEmpty())
                    onPush();
                else
                    Toast.makeText(getContext(), "name is empty", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onPush() {
        Calendar calendar = Calendar.getInstance();
        int time = (int) calendar.getTimeInMillis();
        group = new Group();
        group.setId(1);
        group.setName(etName.getText().toString());
        group.setCreated_at(time);
        group.setUpdate_at(time);
        group.setStatus(1);
        group.setText(etDescription.getText().toString());
        group.setUser_id(-1);
        addGroupPresenter = new AddGroupPresenter(getContext(), this, group);
        addGroupPresenter.insertDb();

    }

    @Override
    public <T> void onLoad(ArrayList<T> t) {

    }
}
