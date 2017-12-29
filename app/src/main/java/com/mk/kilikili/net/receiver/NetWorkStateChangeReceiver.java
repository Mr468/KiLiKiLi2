package com.mk.kilikili.net.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mk.kilikili.utils.CommonUtil;
import com.mk.kilikili.utils.manager.C;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.mk.kilikili.utils.CommonUtil.isNetworkAvailable;

/**
 * 监听网络状态
 */
public class NetWorkStateChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NetWorkListenerManager.INSTANCE.setConnected(context);
    }

    public enum NetWorkListenerManager {

        INSTANCE;

        private List<WeakReference<NetworkStateChangeListener>> listeners;

        public interface NetworkStateChangeListener {

            /**
             * @param isNetAvailable 网络状态是否可用
             */
            void onStateChange(boolean isNetAvailable);
        }

        public void addNetWorkListener(NetworkStateChangeListener networkreceiver) {
            if (null == listeners) {
                listeners = new ArrayList<>();
            }
            listeners.add(new WeakReference<>(networkreceiver));
        }

        public void removeNetWorkListener(NetworkStateChangeListener listener) {
            if (listeners != null) {
                for (int i = 0; i < listeners.size(); i++) {
                    WeakReference<NetworkStateChangeListener> reference = listeners.get(i);
                    if (reference == null || reference.get() == null) {
                        listeners.remove(i);
                        i--;
                        continue;
                    }
                    if (reference.get() == listener) {
                        listeners.remove(i);
                        //i--;
                        break;
                    }
                }
            }
        }

        public void clearNetWorkListeners() {
            if (listeners != null) {
                listeners.clear();
            }
        }

        /**
         * 设置网络状态
         */
        public void setConnected(Context context) {

            Boolean connected = CommonUtil.isNetworkAvailable(context);

            if (listeners != null) {
                for (int i = 0, z = listeners.size(); i < z; i++) {
                    WeakReference<NetworkStateChangeListener> listener = listeners.get(i);
                    if (listener != null) {
                        NetworkStateChangeListener networkreceiver = listener.get();
                        if (networkreceiver != null) networkreceiver.onStateChange(connected);
                    }
                }
            }
        }
    }
}
