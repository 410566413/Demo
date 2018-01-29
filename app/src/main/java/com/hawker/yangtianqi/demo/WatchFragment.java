package com.hawker.yangtianqi.demo;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WatchFragment extends Fragment implements View.OnClickListener {
    private int seconds=0;
    private  boolean running;
    private  boolean wasRunning;

    public WatchFragment() {
        // Required empty public constructor
    }

    //重写方法
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        if (saveInstanceState!=null){
            seconds = saveInstanceState.getInt("seconds");
            running = saveInstanceState.getBoolean("running");
            wasRunning = saveInstanceState.getBoolean("wasRunning");
            if (wasRunning){
                running = true;
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
         View layout= inflater.inflate(R.layout.fragment_watch, container, false);
        runTime(layout);

        Button btnStart = (Button) layout.findViewById(R.id.btnWatchStart);
        btnStart.setOnClickListener(this);
        Button btnStop = (Button) layout.findViewById(R.id.btnWatchStop);
        btnStop.setOnClickListener(this);
        Button btnReset = (Button) layout.findViewById(R.id.btnWatchReset);
        btnReset.setOnClickListener(this);

        return layout;
    }

    public void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }

    public void onResume(){
        super.onResume();
        if (wasRunning){
            running = true;
        }
    }

    public void onSaveInstanceState(Bundle saveInstanceState){
        saveInstanceState.putInt("seconds",seconds);
        saveInstanceState.putBoolean("wasRunning",wasRunning);
        saveInstanceState.putBoolean("running",running);
    }

    public  void watchStart(){
        running = true;
    }

    public  void watchStop(){
        running = false;
    }

    public  void watchReset(){
        running = false;
        seconds = 0;
    }

    private void runTime(View view) {
        final TextView tv = (TextView) view.findViewById(R.id.timeView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                tv.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnWatchStart:
                watchStart();
                break;
            case R.id.btnWatchStop:
                watchStop();
                break;
            case R.id.btnWatchReset:
                watchReset();
                break;
            default:
                break;
        }
    }
}
