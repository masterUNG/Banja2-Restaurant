package appewtc.masterung.banja2restaurant;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ReadOrderListView extends AppCompatActivity {

    //Explicit
    private TextView showOfficerTextView, showDeskTextView;
    private ListView orderListView;
    private String officerString, deskString;
    private String[] foodStrings, amountStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_order_list_view);

        //Bind Widget
        bindWidget();

        //Show View
        showView();

        //Create ListView
        createListView();

    }   // Main Method

    public void clickMore(View view) {
        finish();
    }

    private void createListView() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MyManage.order_table, null);
        cursor.moveToFirst();

        String[] foodStrings = new String[cursor.getCount()];
        String[] amountStrings = new String[cursor.getCount()];

        for (int i=0;i<cursor.getCount();i++) {
            foodStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_food));
            amountStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_amount));

            cursor.moveToNext();
        }   //for
        cursor.close();

        OrderAdapter orderAdapter = new OrderAdapter(ReadOrderListView.this,
                foodStrings, amountStrings);
        orderListView.setAdapter(orderAdapter);


    }   // createListView

    private void showView() {
        officerString = getIntent().getStringExtra("Officer");
        deskString = getIntent().getStringExtra("Desk");
        showOfficerTextView.setText(officerString);
        showDeskTextView.setText(deskString);
    }

    private void bindWidget() {
        showOfficerTextView = (TextView) findViewById(R.id.textView4);
        showDeskTextView = (TextView) findViewById(R.id.textView5);
        orderListView = (ListView) findViewById(R.id.listView2);
    }
}   // Main Class
