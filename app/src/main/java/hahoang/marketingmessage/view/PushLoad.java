package hahoang.marketingmessage.view;

import java.util.ArrayList;

/**
 * Created by Hoang Ha on 7/5/2017.
 */

public interface PushLoad {
    public void onPush();
    public <T> void onLoad(ArrayList<T> t);
}
