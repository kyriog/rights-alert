package fr.keuse.rightsalert.handler;

import java.util.List;

import fr.keuse.rightsalert.R;
import fr.keuse.rightsalert.entity.Application;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class LoadApplicationsHandler extends Handler {
	public final static int MSG_START_PROGRESS = 1;
	public final static int MSG_UPDATE_PROGRESS = 2;
	public final static int MSG_FINISH_PROGRESS = 3;
	
	private ProgressDialog progress;
	private Activity activity;
	private TextView count;
	
	public LoadApplicationsHandler(ProgressDialog progress, Activity activity, TextView count) {
		this.progress = progress;
		this.activity = activity;
		this.count = count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleMessage(Message msg) {
		switch(msg.arg1) {
		case MSG_START_PROGRESS:
			progress.setMax(msg.arg2);
			progress.show();
			break;
		case MSG_UPDATE_PROGRESS:
			progress.setProgress(msg.arg2);
			progress.setMessage((String) msg.obj);
			break;
		case MSG_FINISH_PROGRESS:
			List<Application> applications = (List<Application>) msg.obj;
			count.setText(activity.getString(R.string.applist_count, applications.size()));
			progress.dismiss();
		}
	}

}
