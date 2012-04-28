package fr.keuse.rightsalert.activity;

import java.util.ArrayList;

import fr.keuse.rightsalert.R;
import fr.keuse.rightsalert.adapter.ApplistAdapter;
import fr.keuse.rightsalert.entity.ApplicationEntity;
import fr.keuse.rightsalert.handler.LoadApplicationsHandler;
import fr.keuse.rightsalert.thread.LoadApplicationsThread;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ApplistActivity extends Activity implements DialogInterface.OnCancelListener, DialogInterface.OnClickListener {
	private ProgressDialog progress;
	private static LoadApplicationsThread thread;
	private ArrayList<ApplicationEntity> applications;
	private ApplistAdapter adapter;
	private TextView count;
	
    @SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applist);
        
        count = (TextView) findViewById(R.id.applist_count);
        ListView list = (ListView) findViewById(R.id.applist_list);
        
        progress = new ProgressDialog(this);
        
        applications = (ArrayList<ApplicationEntity>) getLastNonConfigurationInstance();
        if(applications == null)
        	applications = new ArrayList<ApplicationEntity>();
        
        adapter = new ApplistAdapter(this, applications);
        list.setAdapter(adapter);
        
        if(applications.size() == 0) {
        	PackageManager pm = getPackageManager();
	        
	        progress.setProgress(0);
	        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	        progress.setTitle(getString(R.string.app_name));
	        progress.setMessage(getString(R.string.applist_loading));
	        progress.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.applist_cancel), this);
	        progress.setOnCancelListener(this);
	    
	        LoadApplicationsHandler handler = new LoadApplicationsHandler(progress, this, applications);
	        
	        if(thread == null || !thread.isAlive()) {
		        thread = new LoadApplicationsThread(pm);
		        thread.start();
	        }
	        thread.setHandler(handler);
        	thread.sendOpenPopup();
        } else {
        	refreshView();
        }
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(progress.isShowing())
			progress.dismiss();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return applications;
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
	
	public void refreshView() {
		adapter.notifyDataSetChanged();
		count.setText(getString(R.string.applist_count, applications.size()));
	}
}