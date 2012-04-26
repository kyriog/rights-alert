package fr.keuse.rightsalert.activity;

import fr.keuse.rightsalert.R;
import fr.keuse.rightsalert.handler.LoadApplicationsHandler;
import fr.keuse.rightsalert.thread.LoadApplicationsThread;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class ApplistActivity extends Activity implements DialogInterface.OnCancelListener, DialogInterface.OnClickListener {
	private ProgressDialog progress;
	private LoadApplicationsHandler handler;
	private LoadApplicationsThread thread;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applist);
        
        PackageManager pm = getPackageManager();
        
        progress = new ProgressDialog(this);
        progress.setProgress(0);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setTitle(getString(R.string.app_name));
        progress.setMessage(getString(R.string.applist_loading));
        progress.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.applist_cancel), this);
        progress.setOnCancelListener(this);
        
        handler = new LoadApplicationsHandler(progress);
        thread = new LoadApplicationsThread(pm, handler);
        thread.start();
    }

	public void onClick(DialogInterface dialog, int which) {
		switch(which) {
		case DialogInterface.BUTTON_NEGATIVE:
			onCancel(dialog);
		}
	}

	public void onCancel(DialogInterface dialog) {
		thread.interrupt();
		progress.dismiss();
		finish();
	}
}