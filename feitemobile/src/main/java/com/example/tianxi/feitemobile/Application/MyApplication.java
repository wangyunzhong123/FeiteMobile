package com.example.tianxi.feitemobile.Application;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.example.tianxi.feitemobile.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

/**
 * Created by tianxi on 15-12-8.
 */
public class MyApplication extends Application{


    private static Context context;

    public static String IMAGE_CACHE_PATH = "imageloader/Cache"; // 图片缓存路径

    // 异步加载图片
    public static ImageLoader mImageLoader;
    public static DisplayImageOptions options;



    @Override
    public void onCreate(){
        context = getApplicationContext();

        // 使用ImageLoader之前初始化
        initImageLoader();

    }

    public static Context getContext(){
        return context;
    }


    private void initImageLoader() {
        File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
                .getOwnCacheDirectory(getContext(),
                        IMAGE_CACHE_PATH);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory().cacheOnDisc().build();///.cacheInMemory(true).cacheOnDisc(true).build();
//        LogUtil.w("getActivity(),,, ",getActivity().toString());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext()).defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new LruMemoryCache(12 * 1024 * 1024))
                .memoryCacheSize(12 * 1024 * 1024)
                .discCacheSize(32 * 1024 * 1024).discCacheFileCount(100)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();

        ImageLoader.getInstance().init(config);

        //全局实例
        mImageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.top_banner_android)
                .showImageForEmptyUri(R.drawable.top_banner_android)
                .showImageOnFail(R.drawable.top_banner_android)
                .cacheInMemory().cacheOnDisc()/////////.cacheInMemory(ture).cacheOnDisc(ture)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY).build();

    }

}
