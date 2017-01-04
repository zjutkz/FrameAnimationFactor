package com.zjutkz.frameanimationlib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by kangzhe on 17/1/3.
 */

public class AnimationFactor {

    private int[] mAnimFrames;

    private int mFrameIndex;
    private boolean mIsRunning;
    private boolean mIsStarted;
    private WeakReference<ImageView> mWeakReferenceImageView;
    private Handler mFactorHandler;
    private int mDuration;

    private Bitmap mBitmap = null;
    private BitmapFactory.Options mBitmapOptions;

    AnimationFactor(int fps,int[] mAnimFrames,ImageView imageView) {
        this.mAnimFrames = mAnimFrames;

        mFactorHandler = new Handler();
        mFrameIndex = -1;
        mWeakReferenceImageView = new WeakReference<>(imageView);
        mIsRunning = false;
        mDuration = 1000 / fps;

        imageView.setImageResource(mAnimFrames[0]);

        if (Build.VERSION.SDK_INT >= 11) {
            Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            Bitmap.Config config = bmp.getConfig();
            mBitmap = Bitmap.createBitmap(width, height, config);
            mBitmapOptions = new BitmapFactory.Options();
            mBitmapOptions.inBitmap = mBitmap;
            mBitmapOptions.inMutable = true;
            mBitmapOptions.inSampleSize = 1;
        }
    }

    public static class Builder{
        private int fps = 30;
        private int[] mAnimFrames;
        private ImageView imageView;

        public static Builder newBuilder(){
            return new Builder();
        }

        public Builder setFps(int fps){
            this.fps = fps;

            return this;
        }

        public Builder setAnimFrames(int[] mAnimFrames){
            this.mAnimFrames = mAnimFrames;

            return this;
        }

        public Builder setImageView(ImageView imageView){
            this.imageView = imageView;

            return this;
        }

        public AnimationFactor build(){
            return new AnimationFactor(fps,mAnimFrames,imageView);
        }
    }

    private int runNextFrame() {
        mFrameIndex++;
        if (mFrameIndex >= mAnimFrames.length)
            mFrameIndex = 0;
        return mAnimFrames[mFrameIndex];
    }

    public synchronized void start() {
        if (mIsStarted) {
            return;
        }

        mIsStarted = true;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ImageView imageView = mWeakReferenceImageView.get();
                if (!mIsStarted || imageView == null) {
                    mIsRunning = false;
                    return;
                }

                mIsRunning = true;
                mFactorHandler.postDelayed(this, mDuration);

                if (imageView.isShown()) {
                    int imageRes = runNextFrame();
                    if (mBitmap != null) {
                        Bitmap bitmap = null;
                        try {
                            bitmap = BitmapFactory.decodeResource(imageView.getResources(), imageRes, mBitmapOptions);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (bitmap != null) {
                            imageView.setImageBitmap(bitmap);
                        } else {
                            imageView.setImageResource(imageRes);
                            mBitmap.recycle();
                            mBitmap = null;
                        }
                    } else {
                        imageView.setImageResource(imageRes);
                    }
                }

            }
        };

        mFactorHandler.post(runnable);
    }

    public synchronized void stop() {
        mIsStarted = false;
    }

    public synchronized void reset() {
        if(mWeakReferenceImageView.get() != null){
            mWeakReferenceImageView.get().setImageResource(mAnimFrames[0]);
        }
        mFrameIndex = -1;
    }

    public boolean isStarted(){
        return mIsStarted;
    }
}