package processmonitor.app.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Display;
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
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
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
                tv.setBackgroundColor(Color.parseColor("#013220"));
                tv.setWidth(width);
                tv.setText("Name: " + processes.get(i).processName + "\n"
                                + "PID: " + String.valueOf(processes.get(i).pid) + "\n"
                                + "Packages: " + packL
                );
                TextView splitter = new TextView(this);
                splitter.setHeight(5);
                splitter.setWidth(width);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                splitter.setBackgroundColor(Color.parseColor("#008080"));
                cont.addView(tv);
                cont.addView(splitter);
                packL = "";
            }

            ((RelativeLayout) page).addView((ScrollView) scrollView);
            pages.add(page);
            y = 0;

        // Task monitor
        List<ActivityManager.AppTask> tasks = monitor.getTasks(this);
        page = inflater.inflate(R.layout.page, null);
        scrollView = inflater.inflate(R.layout.scroll,null);
        cont = (LinearLayout) scrollView.findViewById(R.id.scroll_container);
        for (int i = 0; i < tasks.size(); i++) {
            tv = new TextView(this);
            tv.setBackgroundColor(Color.parseColor("#013220"));
            tv.setWidth(width);
            tv.setText(tasks.get(i).toString());
            TextView splitter = new TextView(this);
            splitter.setHeight(5);
            splitter.setWidth(width);
            splitter.setBackgroundColor(Color.parseColor("#008080"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            cont.addView(tv);
            cont.addView(splitter);
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
            tv.setBackgroundColor(Color.parseColor("#013220"));
            tv.setWidth(width);
            tv.setText(services.get(i).process);
            TextView splitter = new TextView(this);
            splitter.setHeight(5);
            splitter.setWidth(width);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            splitter.setBackgroundColor(Color.parseColor("#008080"));
            cont.addView(tv);
            cont.addView(splitter);
        }
        ((RelativeLayout)page).addView((ScrollView)scrollView);
        pages.add(page);


        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        setContentView(viewPager);
    }
}
