package fr.keuse.rightsalert.entity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;

public class Application {
	private String name;
	private Drawable icon;
	private int score = -1;
	private PermissionInfo[] permissions;
	
	public Application(PackageInfo p, PackageManager pm) {
		name = (String) p.applicationInfo.loadLabel(pm);
		icon = pm.getApplicationIcon(p.applicationInfo);
		permissions = p.permissions;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public PermissionInfo[] getPermissions() {
		return permissions;
	}
	public void setPermissions(PermissionInfo[] permissions) {
		this.permissions = permissions;
	}
}
