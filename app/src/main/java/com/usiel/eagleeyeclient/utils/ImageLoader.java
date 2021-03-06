package com.usiel.eagleeyeclient.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

public class ImageLoader {


        public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            BitmapFactory.decodeResource(res,resId,options);

            options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);

            options.inJustDecodeBounds=false;
            return BitmapFactory.decodeResource(res,resId,options);
        }

        public static Bitmap decodeSampledBitmapFromFile(File file, int reqWidth, int reqHeight){
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            BitmapFactory.decodeFile(file.getPath(),options);

            options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);

            options.inJustDecodeBounds=false;
            return BitmapFactory.decodeFile(file.getPath(),options);
        }

        private static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight){
            final int height=options.outHeight;
            final int width=options.outWidth;
            int inSampleSize=1;

            if(height>reqHeight||width>reqWidth){
                final int halfHeight=height/2;
                final int halfWidth=width/2;
                while((halfHeight/inSampleSize)>=reqHeight
                        &&(halfWidth/inSampleSize)>=reqWidth){
                    inSampleSize*=2;
                }
            }
            return inSampleSize;
        }

}
