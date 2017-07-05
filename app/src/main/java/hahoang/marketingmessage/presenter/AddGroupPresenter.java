package hahoang.marketingmessage.presenter;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import hahoang.marketingmessage.model.Group;
import hahoang.marketingmessage.model.GroupDb;
import hahoang.marketingmessage.view.PushLoad;

/**
 * Created by Hoang Ha on 7/5/2017.
 */

public class AddGroupPresenter {
    PushLoad view;
    Group group;
    Context mContext;

    public AddGroupPresenter(Context context, PushLoad pushLoad, Group group) {
        this.group = group;
        view = pushLoad;
        mContext = context;
    }

    public void insertDb() {
        GroupDb groupDb = GroupDb.getInstance(mContext);
        groupDb.insert(group);

        ArrayList<Group> groups = groupDb.getAll();
        for (Group g : groups
                ) {
            Log.v("HH", g.toString());
        }
    }
}
