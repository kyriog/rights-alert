package fr.keuse.rightsalert.helper;

import android.Manifest;

public class Score {
	private Score() {}
	
	public static int calculate(String[] permissions) {
		int score = 0;
		for(String permission : permissions) {
			if(permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION))
				score += 22;
			else if(permission.equals(Manifest.permission.ACCESS_FINE_LOCATION))
				score += 50;
			else if(permission.equals(Manifest.permission.BATTERY_STATS))
				score += 5;
			else if(permission.equals(Manifest.permission.BLUETOOTH))
				score += 7;
			else if(permission.equals(Manifest.permission.BLUETOOTH_ADMIN))
				score += 13;
			else if(permission.equals(Manifest.permission.BRICK))
				score += 45;
			else if(permission.equals(Manifest.permission.CALL_PHONE))
				score += 27;
			else if(permission.equals(Manifest.permission.CALL_PRIVILEGED))
				score += 30;
			else if(permission.equals(Manifest.permission.CAMERA))
				score += 3;
			else if(permission.equals(Manifest.permission.GET_ACCOUNTS))
				score += 6;
			else if(permission.equals(Manifest.permission.INTERNET))
				score += 10;
			else if(permission.equals(Manifest.permission.MANAGE_ACCOUNTS))
				score += 20;
			else if(permission.equals(Manifest.permission.READ_CALENDAR))
				score += 11;
			else if(permission.equals(Manifest.permission.READ_CONTACTS))
				score += 13;
			else if(permission.equals(Manifest.permission.READ_HISTORY_BOOKMARKS))
				score += 9;
			else if(permission.equals(Manifest.permission.READ_PHONE_STATE))
				score += 20;
			else if(permission.equals(Manifest.permission.READ_SMS))
				score += 23;
			else if(permission.equals(Manifest.permission.SEND_SMS))
				score += 22;
		}
		return score;
	}
	
	public static boolean isDangerous(int score) {
		return (score > 40);
	}
}
