package appewtc.masterung.banja2restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by masterUNG on 3/1/16 AD.
 */
public class OrderAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] foodStrings, amountStrings;

    public OrderAdapter(Context context, String[] foodStrings, String[] amountStrings) {
        this.context = context;
        this.foodStrings = foodStrings;
        this.amountStrings = amountStrings;
    }

    @Override
    public int getCount() {
        return foodStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.order_listview, viewGroup, false);

        TextView foodTextView = (TextView) view1.findViewById(R.id.textView6);
        foodTextView.setText(foodStrings[i]);

        TextView amountTextView = (TextView) view1.findViewById(R.id.textView7);
        amountTextView.setText(amountStrings[i]);


        return view1;
    }
}   // Main Class
