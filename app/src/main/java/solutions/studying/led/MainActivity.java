package solutions.studying.led;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.RecyclerView.ViewHolder;



public class MainActivity extends AppCompatActivity implements NoticeDialogFragment.NoticeDialogListener {

    List<LedSupernight> connectedLeds;
    private final LedSupernight kitchen = new LedSupernight();
    private final String TAG = MainActivity.class.getName();
    private ListView lstConnectedLeds;
    private LedAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeVars();
    }

    public void openColorPicker(View view, LedSupernight input){
        Intent intent = new Intent(this, ColorPicker.class);
        intent.putExtra("input", input);
        startActivity(intent);
    }

    public void addLed(View view){
        DialogFragment dialog = new NoticeDialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeVars(){
        kitchen.setName("Kitchen");
        kitchen.setUserName("pi");
        kitchen.setPassword("test");
        kitchen.setIp("10.0.0.16");
        connectedLeds = new ArrayList<LedSupernight>();
        connectedLeds.add(kitchen);
        lstConnectedLeds = findViewById(R.id.lstLeds);
        adapter = new LedAdapter(getApplicationContext(), connectedLeds);
        lstConnectedLeds.setAdapter(adapter);
        lstConnectedLeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openColorPicker(findViewById(R.id.content), connectedLeds.get(i));
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String ip, String user, String password, String ledName){
        LedSupernight newToAdd = new LedSupernight(ledName);
        newToAdd.setIp(ip);
        newToAdd.setUserName(user);
        newToAdd.setPassword(password);
        connectedLeds.add(newToAdd);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog){
        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
    }
}
