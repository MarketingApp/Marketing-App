package hahoang.marketingmessage.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hahoang.marketingmessage.R;


/**
 * Created by Ha Hoang on 7/2/2017.
 */

public class AddNumberActivity extends AppCompatActivity {
    private EditText etName,etPhoneNumber,etEmail,etAddress,etBirthday,etGroups,etDescription;
    private Button btAddPhoneNumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_number_activity);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }
}
