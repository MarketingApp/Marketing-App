package hahoang.marketingmessage.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ha Hoang on 7/1/2017.
 */

public class MainMenu implements Parcelable {
    public int icon;
    public String title;
    public int id;

    public MainMenu() {
    }

    public MainMenu(int icon, String title, int id) {
        this.icon = icon;
        this.title = title;
        this.id = id;
    }

    protected MainMenu(Parcel in) {
        icon = in.readInt();
        title = in.readString();
        id = in.readInt();
    }

    public static final Creator<MainMenu> CREATOR = new Creator<MainMenu>() {
        @Override
        public MainMenu createFromParcel(Parcel in) {
            return new MainMenu(in);
        }

        @Override
        public MainMenu[] newArray(int size) {
            return new MainMenu[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeString(title);
        dest.writeInt(id);
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
