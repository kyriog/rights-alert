package fr.keuse.rightsalert.adapter;

import java.util.ArrayList;

import fr.keuse.rightsalert.entity.Application;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ApplistAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Application> applications;
	
	public ApplistAdapter(Context context, ArrayList<Application> applications) {
		this.context = context;
		this.applications = applications;
	}

	public int getCount() {
		return applications.size();
	}

	public Application getItem(int location) {
		return applications.get(location);
	}

	public long getItemId(int location) {
		return location;
	}

	public View getView(int location, View convertView, ViewGroup parent) {
		Application application = getItem(location);
		
		ImageView icon = new ImageView(context);
		TextView name = new TextView(context);
		LinearLayout view = new LinearLayout(context);
		
		icon.setImageDrawable(application.getIcon());
		icon.setAdjustViewBounds(true);
		icon.setMaxHeight(40);
		name.setText(application.getName());
		
		view.setOrientation(LinearLayout.HORIZONTAL);
		view.addView(icon);
		view.addView(name);
		
		return view;
	}

}
