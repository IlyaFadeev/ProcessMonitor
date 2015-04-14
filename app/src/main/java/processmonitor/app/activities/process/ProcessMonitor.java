package processmonitor.app.activities.process;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.widget.Toast;

import java.util.List;


/**
 * Created by Ilya on 06.04.2015.
 */
public class ProcessMonitor {
    public List<ActivityManager.RunningAppProcessInfo> getProcesses(Activity activity)
    {

        ActivityManager manager = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> process = manager.getRunningAppProcesses();
        return process;
    }

    public void killProcess(int pid)
    {
        android.os.Process.killProcess(pid);
    }





}
