package processmonitor.app.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;
import processmonitor.app.R;
import processmonitor.app.activities.process.ProcessMonitor;

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
        processTable = (TableLayout) findViewById(R.id.processTable);
        processTable.setStretchAllColumns(true);
        processTable.setColumnShrinkable(4, true);
        TableRow row = new TableRow(this);
        TextView name = new TextView(this);
        name.setText("Name");
        name.setGravity(Gravity.CENTER);
        name.setTypeface(Typeface.SERIF, Typeface.BOLD);
        TextView pid = new TextView(this);
        pid.setText("PID");
        pid.setGravity(Gravity.CENTER);
        pid.setTypeface(Typeface.SERIF, Typeface.BOLD);
        TextView packages = new TextView(this);
        packages.setText("Packages");
        packages.setGravity(Gravity.CENTER);
        packages.setTypeface(Typeface.SERIF, Typeface.BOLD);
        TextView time = new TextView(this);
        time.setText("Time");
        time.setGravity(Gravity.CENTER);
        time.setTypeface(Typeface.SERIF, Typeface.BOLD);
        row.addView(name);
        row.addView(pid);
        row.addView(packages);
        row.addView(time);
        processTable.addView(row);
        processTable.computeScroll();

        //Get processes
        ProcessMonitor monitor = new ProcessMonitor();
        List<ActivityManager.RunningAppProcessInfo> processes = monitor.getProcesses(this);
        String[] pack;
        String packL = "";
        for (int i = 0; i < processes.size(); i++) {
            TableRow rows = new TableRow(this);
            TextView currName = new TextView(this);
            currName.setGravity(Gravity.CENTER);
            currName.setTypeface(Typeface.SERIF, Typeface.BOLD);
            TextView currPID = new TextView(this);
            currPID.setGravity(Gravity.CENTER);
            currPID.setTypeface(Typeface.SERIF, Typeface.BOLD);
            TextView currPKG = new TextView(this);
            currPKG.setGravity(Gravity.CENTER);
            currPKG.setTypeface(Typeface.SERIF, Typeface.BOLD);
            currName.setText(processes.get(i).processName);
            currPID.setText(String.valueOf(processes.get(i).pid));
            pack = processes.get(i).pkgList;
            for (int j = 0; j < pack.length; j++) {
                packL += pack[j];
                packL += ';';
            }
            currPKG.setText(packL);
            rows.addView(currName);
            rows.addView(currPID);
            rows.addView(currPKG);
            processTable.addView(rows);
            packL = "";
        }

    }
}
