
package com.sunyabin.sunapputils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
*BitmapUtil
*created at 2017-06-27 10:48 by SUN
*作用：操作图片
*/
public class BitmapUtil {
    private BitmapUtil() {
    }

    /**
     * 缩放图片 正方形
     *
     * @param bitmap
     * @param  lengthOfSide  边长
     * @return  Bitmap
     */
    public static Bitmap zoom(Bitmap bitmap, float lengthOfSide) {
        Matrix matrix = new Matrix();
        matrix.postScale(lengthOfSide, lengthOfSide);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 缩放图片  矩形
     * @param bitmap
     * @param width
     * @param height
     * @return Bitmap
     */
    public static Bitmap zoom(Bitmap bitmap, float width, float height) {
        Matrix matrix = new Matrix();
        matrix.postScale(width, width);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 图片圆角处理
     *
     * @param bitmap
     * @param roundPX
     * @return  Bitmap
     */

    public static Bitmap getRCB(Bitmap bitmap, float roundPX) {
        Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(dstbmp);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectf = new RectF(rect);//矩形
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectf, roundPX, roundPX, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return dstbmp;
    }

    /**
     * * 将图片保存到本地时进行压缩, 即将图片从Bitmap形式变为File形式时进行压缩,
     *  优点:  File形式的图片确实被压缩了
     *  缺点:  当你重新读取压缩后的file为 Bitmap是,它占用的内存并没有改变
     * @param bmp  要压缩的Bitmap
     * @param dirPath  保存的文件夹位置
     * @param picName   保存文件的名称(不带后缀)
     *  @param max 最大比例
     * @return   保存文件的路径
     */
    private String compressBmpToFile(Bitmap bmp,String dirPath,String picName,long max) throws IOException{
        String filePath = dirPath + picName +".jpg";
        File dir = new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;//第一次压缩的压缩率
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > max) { //设置最大压缩尺寸
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        return filePath;
    }


}
