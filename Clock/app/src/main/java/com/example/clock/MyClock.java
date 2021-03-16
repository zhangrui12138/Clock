package com.example.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;

import static java.lang.Math.PI;
import static java.lang.Math.floor;

public class MyClock extends View {
    private Paint paint;
    private Paint milianPaint;
    private Paint fenPaint;
    private Paint hourPaint;
    private Paint biaopanPaint;
    private float milianDu = 0.0f;
    private float fenDu = 0.0f;
    private float HourDu = 0.0f;
    private int mViewWidth = 0;
    private int mViewHeight = 0;
    ArrayList<String> arrayList = new ArrayList<>();
    private Handler handler;

    public MyClock(Context context) {
        super(context);
        init();
    }

    public MyClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        handler = new Handler();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.parseColor("#aa000000"));
        milianPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        milianPaint.setColor(Color.parseColor("#aa000000"));
        fenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fenPaint.setColor(Color.parseColor("#aa0000ff"));
        hourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourPaint.setColor(Color.parseColor("#aaff0000"));
        biaopanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        biaopanPaint.setColor(Color.parseColor("#aa000000"));
        for (int y = 0; y <= 11; y++) {
            arrayList.add(y + "");
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Calendar calendar = Calendar.getInstance();
        milianDu = calendar.get(Calendar.SECOND) * 6;
        fenDu = calendar.get(Calendar.MINUTE) * 6;
        HourDu = calendar.get(Calendar.HOUR) * 30;
        android.util.Log.d("zhangrui","calendar.get(Calendar.HOUR)="+calendar.get(Calendar.HOUR));
        android.util.Log.d("zhangrui","calendar.get(Calendar.MINUTE)="+calendar.get(Calendar.MINUTE));
        //表盘start
        canvas.drawCircle(mViewWidth / 2, mViewHeight / 2, (Math.min(mViewHeight, mViewWidth)) / 2, paint);
        canvas.drawCircle(mViewWidth / 2, mViewHeight / 2,2, paint);
        //表盘end

        //刻度start
        for (int i = 0; i < 60; i++) {
            float zhizhenchangdu = 2.1f;
            if (i % 5 == 0) {
                canvas.save();
                zhizhenchangdu = 2.5f;
                String number = arrayList.get(i/5);
                canvas.drawText(number,(int)(mViewWidth / 2 + (Math.min(mViewHeight, mViewWidth)) / zhizhenchangdu * (float) Math.cos((2 * PI / 360) * 6 * i)-8),
                        (int)(mViewHeight / 2 + (Math.min(mViewHeight, mViewWidth)) / zhizhenchangdu * (float) Math.sin((2 * PI / 360) * 6 * i)),
                        biaopanPaint);
                canvas.rotate(90, 10, 10);
                canvas.restore();
            }
            canvas.save();//先保存之前的
            //canvas.rotate(6, mViewWidth / 2, mViewHeight / 2);//延中心点旋转角度
            canvas.drawLine((mViewWidth / 2 + (Math.min(mViewHeight, mViewWidth)) / zhizhenchangdu * (float) Math.cos((2 * PI / 360) * 6 * i)),
                    (mViewHeight / 2 + (Math.min(mViewHeight, mViewWidth)) / zhizhenchangdu * (float) Math.sin((2 * PI / 360) * 6 * i)),
                    (mViewWidth / 2 + (Math.min(mViewHeight, mViewWidth)) / 2 * (float) Math.cos((2 * PI / 360) * 6 * i)),
                    (mViewHeight / 2 + (Math.min(mViewHeight, mViewWidth)) / 2 * (float) Math.sin((2 * PI / 360) * 6 * i)), biaopanPaint);//画线
            canvas.restore();//恢复
        }
        //刻度end

        //指针start
        canvas.drawLine(mViewWidth / 2, mViewHeight / 2,
                (mViewWidth / 2 + (Math.min(mViewHeight, mViewWidth)) / 4 * (float) Math.cos((2 * PI / 360 * milianDu))),
                (mViewHeight / 2 + (Math.min(mViewHeight, mViewWidth)) / 4 * (float) Math.sin((2 * PI / 360 * milianDu))), milianPaint);

        canvas.drawLine(mViewWidth / 2, mViewHeight / 2,
                (mViewWidth / 2 + (Math.min(mViewHeight, mViewWidth)) / 4 * (float) Math.cos((2 * PI / 360* fenDu) )),
                (mViewHeight / 2 + (Math.min(mViewHeight, mViewWidth)) / 4 * (float) Math.sin((2 * PI / 360* fenDu) )), fenPaint);

        canvas.drawLine(mViewWidth / 2, mViewHeight / 2,
                (mViewWidth / 2 + (Math.min(mViewHeight, mViewWidth)) / 5 * (float) Math.cos((2 * PI / 360* HourDu) )),
                (mViewHeight / 2 + (Math.min(mViewHeight, mViewWidth)) / 5 * (float) Math.sin((2 * PI / 360* HourDu) )), hourPaint);
        //指针end
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                postInvalidate();
//            }
//        }, 1000);
        postInvalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }
}
