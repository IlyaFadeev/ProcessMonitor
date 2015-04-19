package processmonitor.app.activities.process;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by Ilya on 06.04.2015.
 */
public class SystemMonitor {
    public List<ActivityManager.RunningAppProcessInfo> getProcesses(Activity activity)
    {

        ActivityManager manager = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> process = manager.getRunningAppProcesses();
        return process;
    }

    public List<ActivityManager.AppTask> getTasks(Activity activity)
    {

        ActivityManager manager = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> process;
            process = manager.getAppTasks();
        return process;
    }

    public List<ActivityManager.RunningServiceInfo> getServices(Activity activity)
    {

        ActivityManager manager = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> process = manager.getRunningServices(Integer.MAX_VALUE);
        return process;
    }

    public void killProcess(int pid)
    {
        android.os.Process.killProcess(pid);
    }





}
