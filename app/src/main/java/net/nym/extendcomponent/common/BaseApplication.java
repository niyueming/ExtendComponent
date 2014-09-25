package net.nym.extendcomponent.common;

import android.app.Application;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.ConnectivityManager;

import net.nym.extendcomponent.broadcastreceiver.NetBroadcastReceiver;

/**
 * 要在manifest文件的<application></application>里声明
 *
 * */
public class BaseApplication extends Application {
	public final static String TAG = "BaseApplication";
	private NetBroadcastReceiver mNetReceiver;
	private static BaseApplication instance;


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		IntentFilter filter = new IntentFilter(
				ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mNetReceiver = new NetBroadcastReceiver(), filter);

		instance = this;

	}


	/**
	 * Called when the overall system is running low on memory
	 */
	@Override
	public void onLowMemory() {
		super.onLowMemory();

	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		unregisterReceiver(mNetReceiver);
	}

	/**
	 * @return the main context of the Application
	 */
	public static BaseApplication getAppContext() {
		return instance;
	}

	/**
	 * @return the main resources from the Application
	 */
	public static Resources getAppResources() {
		if (instance == null)
			return null;
		return instance.getResources();
	}

}
