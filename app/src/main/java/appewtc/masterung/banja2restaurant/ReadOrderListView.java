package appewtc.masterung.banja2restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import static appewtc.masterung.banja2restaurant.R.id.textView5;

public class ReadOrderListView extends AppCompatActivity {

    //Explicit
    private TextView showOfficerTextView, showDeskTextView;
    private ListView orderListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_order_list_view);

        //Bind Widget
        bindWidget();

    }   // Main Method

    private void bindWidget() {
        showOfficerTextView = (TextView) findViewById(R.id.textView4);
        showDeskTextView = (TextView) findViewById(textView5);
        orderListView = (ListView) findViewById(R.id.listView2);
    }
}   // Main Class
