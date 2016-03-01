package appewtc.masterung.banja2restaurant;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

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

    public void clickOrder(View view) {

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        for (int i=0;i<foodStrings.length;i++) {

            try {

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
                nameValuePairs.add(new BasicNameValuePair(MyManage.column_officer, officerString));
                nameValuePairs.add(new BasicNameValuePair(MyManage.column_desk, deskString));
                nameValuePairs.add(new BasicNameValuePair(MyManage.column_food, foodStrings[i]));
                nameValuePairs.add(new BasicNameValuePair(MyManage.column_amount, amountStrings[i]));

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://swiftcodingthai.com/29feb/php_add_order.php");
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                httpClient.execute(httpPost);

                Toast.makeText(ReadOrderListView.this, "Update Finish", Toast.LENGTH_SHORT).show();



            } catch (Exception e) {
                Toast.makeText(ReadOrderListView.this, "Cannot Update", Toast.LENGTH_SHORT).show();
            }

        }   // for

    }   // clickOrder

    public void clickMore(View view) {
        finish();
    }

    private void createListView() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MyManage.order_table, null);
        cursor.moveToFirst();

         foodStrings = new String[cursor.getCount()];
         amountStrings = new String[cursor.getCount()];

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
