package appewtc.masterung.banja2restaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ShowMenuFood extends AppCompatActivity {

    //Explicit
    private TextView showOfficerTextView;
    private Spinner deskSpinner;
    private ListView foodListView;
    private String officerString, deskString, orderFoodString, amountString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu_food);

        //Bind Widget
        bindWidget();

        //ShowView
        showView();

        //Create Desk Spinner
        createDeskSpinner();

        //Create Food ListView
        createFoodListView();

    }   // Main Method

    private void createDeskSpinner() {

        final String[] deskStrings = {"โต้ที่ 1", "โต้ที่ 2", "โต้ที่ 3", "โต้ที่ 4", "โต้ที่ 5",
                "โต้ที่ 6", "โต้ที่ 7", "โต้ที่ 8", "โต้ที่ 9", "โต้ที่ 10"};
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, deskStrings);
        deskSpinner.setAdapter(stringArrayAdapter);

        deskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                deskString = deskStrings[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                deskString = deskStrings[0];
            }
        });


    }   // createDeskSpinner

    private void showView() {

        officerString = getIntent().getStringExtra("Officer");
        showOfficerTextView.setText(officerString);

    }

    private void createFoodListView() {

        //Read All Data From SQLite
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + MyManage.food_table, null);
        cursor.moveToFirst();

        final String[] foodStrings = new String[cursor.getCount()];
        String[] priceStrings = new String[cursor.getCount()];
        String[] sourceStrings = new String[cursor.getCount()];

        for (int i=0;i<cursor.getCount();i++) {

            foodStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_food));
            priceStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_price));
            sourceStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_source));

            cursor.moveToNext();
        }   // for
        cursor.close();

        FoodAdapter foodAdapter = new FoodAdapter(ShowMenuFood.this,
                foodStrings, priceStrings, sourceStrings);
        foodListView.setAdapter(foodAdapter);

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                orderFoodString = foodStrings[i];

                findAmount();

            }
        });


    }   // createFoodListView

    private void findAmount() {

        CharSequence[] charSequences = {"1 set", "2 set", "3 set", "4 set", "5 set"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(orderFoodString);
        builder.setSingleChoiceItems(charSequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                amountString = Integer.toString(i + 1);
                addOrderToSQLite();
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }   // findAmount

    private void addOrderToSQLite() {

    }


    private void bindWidget() {
        showOfficerTextView = (TextView) findViewById(R.id.textView);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        foodListView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();

    }
}   // Main Class
