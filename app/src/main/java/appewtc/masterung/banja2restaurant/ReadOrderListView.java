package appewtc.masterung.banja2restaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

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
        showDeskTextView = (TextView) findViewById(R.id.textView5);
        orderListView = (ListView) findViewById(R.id.listView2);
    }
}   // Main Class
