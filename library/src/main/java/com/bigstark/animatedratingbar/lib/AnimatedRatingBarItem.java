package com.bigstark.animatedratingbar.lib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by bigstark on 2017. 2. 11..
 */

class AnimatedRatingBarItem extends FrameLayout {


    private ClipableImageView ivProgress;
    private ImageView ivSecondaryProgress;


    public AnimatedRatingBarItem(Context context) {
        this(context, null);
    }

    public AnimatedRatingBarItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatedRatingBarItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.animated_rating_bar_item, this);

        ivProgress = (ClipableImageView) findViewById(R.id.progress_image);
        ivSecondaryProgress = (ImageView) findViewById(R.id.secondary_progress_image);
    }

    public void setProgressImageDrawable(Drawable drawable) {
        ivProgress.setImageDrawable(drawable);
    }


    public void setSecondaryProgressImageDrawable(Drawable drawable) {
        ivSecondaryProgress.setImageDrawable(drawable);
    }

    public void setProgressLevel(float level) {
        ivProgress.setLevel(level);
    }

}
