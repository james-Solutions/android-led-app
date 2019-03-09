package solutions.studying.led;

import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerView;
import com.skydoves.colorpickerpreference.FlagMode;
import com.skydoves.colorpickerpreference.FlagView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class ColorPicker extends AppCompatActivity {

    private ColorPickerView colorPickerView;
    private Boolean isWheel = true;
    private TextView txtRed;
    private TextView txtGreen;
    private TextView txtBlue;
    private LedSupernight ledStrip;
    private TextView txtPi;
    private Switch powerSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        colorPickerView = findViewById(R.id.colorPickerView);
        colorPickerView.setPreferenceName("Randal");
        initializeVars();
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {
                updateText(colorEnvelope.getColorRGB());
            }
        });
        powerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (powerSwitch.isChecked()==false){
                    colorPickerView.setEnabled(false);
                    tempColor[0] = ledStrip.getRedBrightness();
                    tempColor[1] = ledStrip.getGreenBrightness();
                    tempColor[2] = ledStrip.getBlueBrightness();
                    ledStrip.setRGB(0, 0, 0);
                    updateBoxes();
                } else {
                    colorPickerView.setEnabled(true);
                    ledStrip.setRGB(tempColor[0], tempColor[1], tempColor[2]);
                    updateBoxes();
                }
            }
        });
    }

    public void updateText(int[] color){
        if (color.length == 3) ledStrip.setRGB(color[0], color[1], color[2]);
        updateBoxes();
    }

    protected void updateBoxes(){
        txtRed.setText("Red: " + ledStrip.getRedBrightness());
        txtGreen.setText("Green: " + ledStrip.getGreenBrightness());
        txtBlue.setText("Blue: " + ledStrip.getBlueBrightness());
    }


    private int[] tempColor = new int[3];


    public void points(View v) {
        int x = (int)(Math.random() * 600) + 100;
        int y = (int)(Math.random() * 400) + 150;
        colorPickerView.setSelectorPoint(x, y);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        colorPickerView.saveData();
    }

    public void initializeVars() {
        Intent whereFrom = getIntent();
        ledStrip = (LedSupernight) whereFrom.getSerializableExtra("input");
        txtBlue = findViewById(R.id.txtBlue);
        txtGreen = findViewById(R.id.txtGreen);
        txtRed = findViewById(R.id.txtRed);
        txtPi = findViewById(R.id.txtPiName);
        txtPi.setText(ledStrip.getName());
        powerSwitch = findViewById(R.id.swOn);

    }

}
