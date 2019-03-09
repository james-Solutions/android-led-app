package solutions.studying.led;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import solutions.studying.led.LedSupernight;
import solutions.studying.led.R;

public class LedAdapter extends BaseAdapter {
    private Context context;
    private List<LedSupernight> leds;
    private LayoutInflater inflater;

    public LedAdapter(Context applicationContext, List<LedSupernight> inputLed){
        this.context = applicationContext;
        inflater = LayoutInflater.from(applicationContext);
        leds = inputLed;
    }

    @Override
    public int getCount() {
        return leds.size();
    }

    @Override
    public LedSupernight getItem(int i){
        return leds.get(i);
    }

    public long getItemId(int i){
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        view = inflater.inflate(R.layout.list_item_1, null);
        TextView ledName = view.findViewById(R.id.lst_name_view);
        ledName.setText(leds.get(i).getName());

        return view;
    }
}
