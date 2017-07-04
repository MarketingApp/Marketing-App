package hahoang.marketingmessage.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import hahoang.marketingmessage.R;
import hahoang.marketingmessage.view.activity.AddNumberActivity;

/**
 * Created by Ha Hoang on 7/1/2017.
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    private ImageView addNumber, managerNumber, addGroup, managerGroups, sendMessages;
    private Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.main_frament, container, false);
        return content;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addNumber = (ImageView) view.findViewById(R.id.iv_add_number);
        managerNumber = (ImageView) view.findViewById(R.id.iv_manager_number);
        addGroup = (ImageView) view.findViewById(R.id.iv_add_group);
        managerGroups = (ImageView) view.findViewById(R.id.iv_manager_groups);
        sendMessages = (ImageView) view.findViewById(R.id.iv_send_messages);
//set onclick event
        addNumber.setOnClickListener(this);
        managerNumber.setOnClickListener(this);
        addGroup.setOnClickListener(this);
        managerGroups.setOnClickListener(this);
        sendMessages.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_add_number:
                intent = new Intent(getContext(), AddNumberActivity.class);
                startActivity(intent);
                break;
        }
    }
}
