package com.mk.kilikili.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * 通用工具类
 */
public class CommonUtil {

  /**
   * 检查是否有网络
   */
  public static boolean isNetworkAvailable(Context context) {

    NetworkInfo info = getNetworkInfo(context);
    return info != null && info.isAvailable();
  }


  /**
   * 检查是否是WIFI
   */
  public static boolean isWifi(Context context) {

    NetworkInfo info = getNetworkInfo(context);
    if (info != null) {
      if (info.getType() == ConnectivityManager.TYPE_WIFI) {
        return true;
      }
    }
    return false;
  }


  /**
   * 检查是否是移动网络
   */
  public static boolean isMobile(Context context) {

    NetworkInfo info = getNetworkInfo(context);
    if (info != null) {
      if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
        return true;
      }
    }
    return false;
  }


  private static NetworkInfo getNetworkInfo(Context context) {

    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
        Context.CONNECTIVITY_SERVICE);
    return cm.getActiveNetworkInfo();
  }


  /**
   * 检查SD卡是否存在
   */
  private static boolean checkSdCard() {

    return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
  }


  /**
   * 获取手机SD卡总空间
   */
  private static long getSDcardTotalSize() {

    if (checkSdCard()) {
      File path = Environment.getExternalStorageDirectory();
      StatFs mStatFs = new StatFs(path.getPath());
      long blockSizeLong = mStatFs.getBlockSizeLong();
      long blockCountLong = mStatFs.getBlockCountLong();

      return blockSizeLong * blockCountLong;
    } else {
      return 0;
    }
  }


  /**
   * 获取SDka可用空间
   */
  private static long getSDcardAvailableSize() {

    if (checkSdCard()) {
      File path = Environment.getExternalStorageDirectory();
      StatFs mStatFs = new StatFs(path.getPath());
      long blockSizeLong = mStatFs.getBlockSizeLong();
      long availableBlocksLong = mStatFs.getAvailableBlocksLong();
      return blockSizeLong * availableBlocksLong;
    } else {
      return 0;
    }
  }


  /**
   * 获取手机内部存储总空间
   */
  public static long getPhoneTotalSize() {

    if (!checkSdCard()) {
      File path = Environment.getDataDirectory();
      StatFs mStatFs = new StatFs(path.getPath());
      long blockSizeLong = mStatFs.getBlockSizeLong();
      long blockCountLong = mStatFs.getBlockCountLong();
      return blockSizeLong * blockCountLong;
    } else {
      return getSDcardTotalSize();
    }
  }


  /**
   * 获取手机内存存储可用空间
   */
  public static long getPhoneAvailableSize() {

    if (!checkSdCard()) {
      File path = Environment.getDataDirectory();
      StatFs mStatFs = new StatFs(path.getPath());
      long blockSizeLong = mStatFs.getBlockSizeLong();
      long availableBlocksLong = mStatFs.getAvailableBlocksLong();
      return blockSizeLong * availableBlocksLong;
    } else
      return getSDcardAvailableSize();
  }

  /**
   * 检查apk是否可以debug.
   */
  public static boolean isApkDebugable(Context context) {
    try {
      ApplicationInfo info = context.getApplicationInfo();
      return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
