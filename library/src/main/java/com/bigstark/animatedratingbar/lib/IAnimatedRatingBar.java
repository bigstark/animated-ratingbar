package com.bigstark.animatedratingbar.lib;

import android.graphics.drawable.Drawable;

/**
 * Created by bigstark on 2017. 2. 11..
 */

public interface IAnimatedRatingBar {


    public void setProgressImageResource(int resourceId);


    public void setProgressImageDrawable(Drawable drawable);


    public void setSecondaryProgressImageResource(int resourceId);


    public void setSecondaryProgressImageDrawable(Drawable drawable);


    public void setNumStars(int stars);


    public void setRating(float rating);



    public void setStarGap(int gapSize);


    public void setOnRatingChangedListener(OnRatingChangedListener listener);


    public void setSeekable(boolean seekable);


    public void setAnimateDuration(int duration);


    public void startAnimate();


}
