package hahoang.marketingmessage.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import hahoang.marketingmessage.R;
import hahoang.marketingmessage.view.activity.AddGroupActivity;
import hahoang.marketingmessage.view.activity.ManagerGroupActivity;
import hahoang.marketingmessage.view.activity.SendMessageActivity;

/**
 * Created by Ha Hoang on 7/1/2017.
 */

public class MainFragment extends Fragment implements View.OnClickListener {
    private ImageView addNumber, managerNumber, addGroup, managerGroups, sendMessages;
    private Intent intent;
    private boolean isOpenNewActivity = false;
    public static String TAG = MainFragment.class.getSimpleName();
    protected Fragment fragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.main_fragment, container, false);
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
        getActivity().setTitle(getString(R.string.home));
    }

    @Override
    public void onClick(View v) {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.menu_icon_anim);
        v.startAnimation(animation);
        if (!isOpenNewActivity) {
            isOpenNewActivity = true;
            int id = v.getId();
            switch (id) {
                case R.id.iv_add_number:
                    fragment = new AddNumberFragment();
                    break;
                case R.id.iv_manager_number:
                    fragment = new ManagerNumberFragment();
                    break;
                case R.id.iv_add_group:
                    fragment = new AddGroupFragment();
                    break;
                case R.id.iv_manager_groups:
                    fragment = new ManagerGroupFragment();
                    break;
                case R.id.iv_send_messages:
                    fragment = new SendMessageFragment();
                    break;
            }
            final Fragment fragment1 = fragment;
            CountDownTimer countDownTimer = new CountDownTimer(700, 100) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    isOpenNewActivity = false;
                    if (fragment1 != null)
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .add(R.id.fl_main_activity, fragment1).addToBackStack("").commit();
                }
            }.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
