package net.nym.extendcomponent.broadcastreceiver;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.ArrayList;


/**
 * 类 <code>NetBroadcastReceiver</code>
 * 监听网络状态变化
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 * <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
 * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
 *
 * IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
 *
 * @author nym
 * @version 2013-6-4
 * @since 
 * @time 上午9:23:32
 * @see 
 */
public class NetBroadcastReceiver extends BroadcastReceiver {

	private final String TAG = NetBroadcastReceiver.class.getSimpleName();
    private ArrayList<OnConnectivityChangeListener> mLiseners = ConnectivityUtils.getOnConnectivityChangeListeners();
	@Override
	public void onReceive(Context arg0, Intent arg1) {
//		Log.i(TAG + ":%s", "网络状态改变");

		boolean success = false;
		boolean wifiState = false;
		boolean mobileState = false;
		String mobileNetName = "";	//网络名称

		// 获得网络连接服务
		ConnectivityManager connManager = (ConnectivityManager) arg0
				.getSystemService(Context.CONNECTIVITY_SERVICE);
//		connManager.setNetworkPreference(ConnectivityManager.TYPE_WIFI);	//优先使用wifi
		NetworkInfo  wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if(wifiInfo != null)
		{
			if(wifiInfo.isConnected())
			{
				wifiState = true;
			}
		}
		
		NetworkInfo  mobileInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        int networkType = 0;
		if(mobileInfo != null)
		{

			if(mobileInfo.isConnected())
			{
				mobileState = true;
			}
            networkType = mobileInfo.getSubtype();
            switch (mobileInfo.getSubtype()) {
			case TelephonyManager.NETWORK_TYPE_UMTS:
			case TelephonyManager.NETWORK_TYPE_HSDPA:
				mobileNetName = "联通3G";
				break;
			case TelephonyManager.NETWORK_TYPE_GPRS:
			case TelephonyManager.NETWORK_TYPE_EDGE:
				mobileNetName = "移动或联通2G";
				break;
			case TelephonyManager.NETWORK_TYPE_CDMA:
				mobileNetName = "电信2G";
				break;
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
			case TelephonyManager.NETWORK_TYPE_EVDO_B:
				mobileNetName = "电信3G";
				break;
			case TelephonyManager.NETWORK_TYPE_UNKNOWN:
				mobileNetName = "UNKNOWN";
				break;
			default:
				break;
			}
		}
		
		if (wifiState | mobileState) { // 判断是否正在使用网络
			success = true;
		}
		Log.i(TAG ,String.format("%s:%b,%s状态:%s","wifi状态" , wifiState, mobileNetName, mobileState + ""));
//		OperateSharePreferences.getInstance().saveNetState(success);
        for (int i = 0 ;i < mLiseners.size();i++)
        {
            if (mLiseners.get(i) == null) {
                mLiseners.remove(i);
                i--;
            }
            else {
                mLiseners.get(i).onChange(success, wifiState, networkType, mobileNetName);
            }
        }
	}
    public static interface OnConnectivityChangeListener{
        void onChange(boolean hasNet,boolean isWifi,int networkType,String networkTypeName);
    }
}
