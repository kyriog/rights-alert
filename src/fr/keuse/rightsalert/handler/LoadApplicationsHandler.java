package fr.keuse.rightsalert.handler;

import java.util.ArrayList;
import java.util.List;

import fr.keuse.rightsalert.R;
import fr.keuse.rightsalert.adapter.ApplistAdapter;
import fr.keuse.rightsalert.entity.ApplicationEntity;
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
	private ApplistAdapter adapter;
	private ArrayList<ApplicationEntity> applications;
	
	public LoadApplicationsHandler(ProgressDialog progress, Activity activity, TextView count, ApplistAdapter adapter, ArrayList<ApplicationEntity> applications) {
		this.progress = progress;
		this.activity = activity;
		this.count = count;
		this.adapter = adapter;
		this.applications = applications;
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
			applications.addAll((List<ApplicationEntity>) msg.obj);
			refreshView();
			progress.dismiss();
		}
	}
	
	public void refreshView() {
		adapter.notifyDataSetChanged();
		count.setText(activity.getString(R.string.applist_count, applications.size()));
	}
}
