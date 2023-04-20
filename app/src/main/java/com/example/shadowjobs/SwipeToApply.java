package com.example.shadowjobs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeToApply extends ItemTouchHelper.Callback {

    Context mcontext;
    private Paint mpaint;
    private int backcolor;
    private int myWidth;
    private int myhight;
    private ColorDrawable mbackground;
    private Drawable deletDraw;



    SwipeToApply(Context context){
        mcontext = context;
        mbackground = new ColorDrawable();
        backcolor = Color.parseColor("#FFBB86FC");
        mpaint = new Paint();
        mpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        deletDraw = ContextCompat.getDrawable(mcontext, R.drawable.ic_delete);
        myWidth = deletDraw.getIntrinsicWidth();
        myhight = deletDraw.getIntrinsicHeight();
    }


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0,ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }


    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemview = viewHolder.itemView;
        int itemHeight = itemview.getHeight();

        boolean isCancel = dX == 0 && !isCurrentlyActive;

        if(isCancel){
            clearCanavas(c,itemview.getRight() + dX, (float) itemview.getTop(), (float) itemview.getRight(), (float) itemview.getBottom());
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        }

        mbackground.setColor(backcolor);
        mbackground.setBounds(itemview.getRight(),itemview.getTop(),itemview.getRight(),itemview.getBottom());
        mbackground.draw(c);


        int deleteTop = itemview.getTop() + (itemHeight - myhight) / 2;
        int deleteMargin = (itemHeight - myhight)/2;
        int deleteLeft = itemview.getRight() - deleteMargin - myWidth;
        int deleteRight = itemview.getRight()-deleteMargin;
        int deleteButton = deleteTop + myhight;

        deletDraw.setBounds(deleteLeft,deleteTop,deleteRight,deleteButton);
        deletDraw.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

    }

    private void clearCanavas( Canvas c,Float left,Float top,Float right, Float bottom){
        c.drawRect(left,top,right,bottom,mpaint);
    }



    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.7f;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

}
