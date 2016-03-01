package appewtc.masterung.banja2restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowMenuFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu_food);
    }   // Main Method

    @Override
    protected void onPause() {
        super.onPause();

        finish();

    }
}   // Main Class
