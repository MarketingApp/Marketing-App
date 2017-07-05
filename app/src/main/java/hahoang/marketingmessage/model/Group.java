package hahoang.marketingmessage.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hoang Ha on 7/5/2017.
 */

public class Group implements Serializable, Parcelable {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("created_at")
    int created_at;
    @SerializedName("update_at")
    int update_at;
    @SerializedName("status")
    int status;
    @SerializedName("text")
    String text;
    @SerializedName("user_id")
    int user_id;

    public Group() {
    }

    protected Group(Parcel in) {
        id = in.readInt();
        name = in.readString();
        created_at = in.readInt();
        update_at = in.readInt();
        status = in.readInt();
        text = in.readString();
        user_id = in.readInt();
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(created_at);
        dest.writeInt(update_at);
        dest.writeInt(status);
        dest.writeString(text);
        dest.writeInt(user_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(int update_at) {
        this.update_at = update_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String toString() {
        return id + " " + name + " " + created_at + " " + update_at + " " + text + " " + status + " " + user_id;
    }
}
