package fr.keuse.rightsalert.thread;

import java.util.ArrayList;
import java.util.List;

import fr.keuse.rightsalert.entity.Application;
import fr.keuse.rightsalert.handler.LoadApplicationsHandler;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Message;

public class LoadApplicationsThread extends Thread {
	PackageManager pm;
	LoadApplicationsHandler handler;
	
	public LoadApplicationsThread(PackageManager pm, LoadApplicationsHandler handler) {
		this.pm = pm;
		this.handler = handler;
	}

	@Override
	public void run() {
		List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS);
		ArrayList<Application> applications = new ArrayList<Application>();
		
		Message msg = handler.obtainMessage();
		msg.arg1 = LoadApplicationsHandler.MSG_START_PROGRESS;
		msg.arg2 = packages.size();
		handler.sendMessage(msg);
		
		for(PackageInfo p : packages) {
			if(isInterrupted())
				return;
			
			msg = handler.obtainMessage();
			msg.arg1 = LoadApplicationsHandler.MSG_UPDATE_PROGRESS;
			msg.arg2 = packages.indexOf(p);
			msg.obj = pm.getApplicationLabel(p.applicationInfo).toString();
			handler.sendMessage(msg);
			
			Application app = new Application(p, pm);
			applications.add(app);
			
			try {
				// Sleep for 10 ms on each PackageInfo to prevent lags on the application
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		msg = handler.obtainMessage();
		msg.arg1 = LoadApplicationsHandler.MSG_FINISH_PROGRESS;
		msg.obj = applications;
		handler.sendMessage(msg);
	}
}
