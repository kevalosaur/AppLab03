package com.daviskevin.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    View.OnClickListener listener;
    TextView tl, tr, bl, br;
    SeekBar seekBar;
    Toast toast;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int viewid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        Context context = getApplicationContext();
        CharSequence text = "Some boi was clickulated";
        int duration = Toast.LENGTH_SHORT;
        toast = Toast.makeText(context, text, duration);
//        editor.putInt(""+R.id.tl, 0);
//        editor.putInt(""+R.id.tr, 0);
//        editor.putInt(""+R.id.bl, 0);
//        editor.putInt(""+R.id.br, 0);
//        editor.apply();
        tl = findViewById(R.id.tl);
        tr = findViewById(R.id.tr);
        bl = findViewById(R.id.bl);
        br = findViewById(R.id.br);
        seekBar = findViewById(R.id.textDilate);
        tl.setText(""+sharedPreferences.getInt(""+R.id.tl, 0));
        tr.setText(""+sharedPreferences.getInt(""+R.id.tr, 0));
        bl.setText(""+sharedPreferences.getInt(""+R.id.bl, 0));
        br.setText(""+sharedPreferences.getInt(""+R.id.br, 0));
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView x = (TextView)v;
                System.out.println(x.toString());
                viewid = x.getId();
                int clicks = sharedPreferences.getInt(""+viewid, 0);
                x.setText(String.format("%d", clicks + 1));
                editor.putInt(""+viewid, clicks+1);
                editor.apply();
                toast.show();
            }
        };
        tl.setOnClickListener(listener);
        tr.setOnClickListener(listener);
        bl.setOnClickListener(listener);
        br.setOnClickListener(listener);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tl.setTextSize(progress);
                tr.setTextSize(progress);
                bl.setTextSize(progress);
                br.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
