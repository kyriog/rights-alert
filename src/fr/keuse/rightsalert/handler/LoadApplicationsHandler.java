package fr.keuse.rightsalert.handler;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

public class LoadApplicationsHandler extends Handler {
	public final static int MSG_START_PROGRESS = 1;
	public final static int MSG_UPDATE_PROGRESS = 2;
	public final static int MSG_FINISH_PROGRESS = 3;
	
	private ProgressDialog progress;
	
	public LoadApplicationsHandler(ProgressDialog progress) {
		this.progress = progress;
	}
	
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
			progress.dismiss();
		}
	}

}
