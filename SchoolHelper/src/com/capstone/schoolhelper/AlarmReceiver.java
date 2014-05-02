package com.capstone.schoolhelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		PendingIntent pending = PendingIntent
				.getActivity(context, 0, intent, 0);

		Notification notification;
		notification = new Notification.Builder(context)
				.setContentTitle(AddEventFrag.closestEventName)
				.setContentText(AddEventFrag.closestEventTime)
				.setSmallIcon(R.drawable.ic_launcher)
				.setWhen(System.currentTimeMillis()).setContentIntent(pending)
				.setAutoCancel(true).build();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.defaults |= Notification.DEFAULT_SOUND;
		nm.notify(0, notification);
	}

}
