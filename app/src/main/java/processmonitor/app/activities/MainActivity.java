package processmonitor.app.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import processmonitor.app.R;
import processmonitor.app.activities.process.SystemMonitor;
import processmonitor.app.activities.process.SamplePagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ilya on 06.04.2015.
 */
public class MainActivity extends Activity {
    TableLayout processTable;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

        SystemMonitor monitor = new SystemMonitor();
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
            tv.setWidth(2000);
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
        tv.setHeight(115);
        tv.setY(y);
        cont.addView(tv);
        ((RelativeLayout)page).addView((ScrollView) scrollView);
        pages.add(page);
        y = 0;
        // Task monitor
        List<ActivityManager.AppTask> tasks = monitor.getTasks(this);
        page = inflater.inflate(R.layout.page, null);
        scrollView = inflater.inflate(R.layout.scroll,null);
        cont = (LinearLayout) scrollView.findViewById(R.id.scroll_container);
        for (int i = 0; i < tasks.size(); i++) {
            tv = new TextView(this);
            tv.setBackgroundColor(Color.GRAY);
            tv.setWidth(2000);
            tv.setText(tasks.get(i).toString());
            cont.addView(tv);
            y += 5;
        }
        ((RelativeLayout)page).addView((ScrollView) scrollView);
        pages.add(page);
        y = 0;

        // Service monitor
        List<ActivityManager.RunningServiceInfo> services = monitor.getServices(this);
        page = inflater.inflate(R.layout.page, null);
        scrollView = inflater.inflate(R.layout.scroll,null);
        cont = (LinearLayout) scrollView.findViewById(R.id.scroll_container);
        for (int i = 0; i < services.size(); i++) {
            tv = new TextView(this);
            tv.setBackgroundColor(Color.BLUE);
            tv.setWidth(2000);
            tv.setY(y);
            tv.setText(services.get(i).process);
            cont.addView(tv);
            y += 5;
        }
        tv = new TextView(this);
        tv.setBackgroundColor(Color.WHITE);
        tv.setWidth(2000);
        tv.setHeight(50);
        tv.setY(y);
        cont.addView(tv);
        ((RelativeLayout)page).addView((ScrollView)scrollView);
        pages.add(page);


        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        setContentView(viewPager);
    }
}
