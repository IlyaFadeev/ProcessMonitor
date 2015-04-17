package processmonitor.app.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import processmonitor.app.R;
import processmonitor.app.activities.process.ProcessMonitor;
import processmonitor.app.activities.process.SamplePagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


/**
 * Created by Ilya on 06.04.2015.
 */
public class MainActivity extends Activity {
    TableLayout processTable;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

        ProcessMonitor monitor = new ProcessMonitor();
        List<ActivityManager.RunningAppProcessInfo> processes = monitor.getProcesses(this);
        String[] pack;
        String packL = "";
        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> pages = new ArrayList<View>();
        // Process monitor
        View page = inflater.inflate(R.layout.page, null);
        View scrollView = inflater.inflate(R.layout.scroll,null);
        LinearLayout cont = (LinearLayout) scrollView.findViewById(R.id.scroll_container);
        TextView tv;
        float y = 0;
        for (int i = 0; i < processes.size(); i++) {
            pack = processes.get(i).pkgList;
            for (int j = 0; j < pack.length; j++) {
                packL += pack[j];
                packL += ';';
            }
             tv = new TextView(this);
            tv.setBackgroundColor(Color.GREEN);
            tv.setWidth(1000);
            tv.setY(y);
            tv.setText("Name: " + processes.get(i).processName + "\n"
                            + "PID: " + String.valueOf(processes.get(i).pid) + "\n"
                            + "Packages: " + packL
            );

            cont.addView(tv);
            y += 5;
            packL = "";
        }
        tv = new TextView(this);
        tv.setBackgroundColor(Color.WHITE);
        tv.setWidth(1000);
        tv.setHeight(80);
        tv.setY(y);
        cont.addView(tv);
        ((RelativeLayout)page).addView((ScrollView)scrollView);
        pages.add(page);

        // Task monitor
        page = inflater.inflate(R.layout.page, null);
        tv = new TextView(this);
        tv.setBackgroundColor(Color.GRAY);
        tv.setWidth(1000);
        tv.setText("Page 2");
        ((RelativeLayout) page).addView(tv);
        pages.add(page);

        // Service monitor
        page = inflater.inflate(R.layout.page, null);
        tv = new TextView(this);
        tv.setBackgroundColor(Color.GRAY);
        tv.setWidth(1000);
        tv.setText("Page 3 \n" +
                "dlkvj");
        ((RelativeLayout) page).addView(tv);
        pages.add(page);


        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        setContentView(viewPager);
    }
}
