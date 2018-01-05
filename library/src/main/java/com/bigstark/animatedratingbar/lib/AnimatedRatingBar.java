package com.bigstark.animatedratingbar.lib;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by bigstark on 2017. 2. 11..
 */

public class AnimatedRatingBar extends LinearLayout implements IAnimatedRatingBar {

    private OnRatingChangedListener listener;
    private int duration = 500;
    private boolean seekable = true;

    private Drawable progressImage;
    private Drawable secondaryProgressImage;
    private int numStars;
    private float rating;
    private int max;
    private int gapSize;
    private int starSize = 0;

    private boolean isNeedRedraw = true;


    public AnimatedRatingBar(Context context) {
        this(context, null);
    }

    public AnimatedRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatedRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setWillNotDraw(false);
        setAttrs(context, attrs);
        resetItems();
    }


    private void setAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AnimatedRatingBar);
        int progressImageResource = ta.getResourceId(R.styleable.AnimatedRatingBar_progressImage, R.id.progress_image);
        int secondaryProgressImageResource = ta.getResourceId(R.styleable.AnimatedRatingBar_secondaryProgressImage, R.id.secondary_progress_image);

        if (progressImageResource != 0) {
            progressImage = getResources().getDrawable(progressImageResource);
        }

        if (secondaryProgressImageResource != 0) {
            secondaryProgressImage = getResources().getDrawable(secondaryProgressImageResource);
        }


        numStars = ta.getInt(R.styleable.AnimatedRatingBar_numStars, 5);
        max = ta.getInt(R.styleable.AnimatedRatingBar_max, 5);
        rating = ta.getFloat(R.styleable.AnimatedRatingBar_rating, 2.5f);
        gapSize = ta.getDimensionPixelSize(R.styleable.AnimatedRatingBar_gapSize, 20);
        starSize = ta.getDimensionPixelSize(R.styleable.AnimatedRatingBar_starSize, 30);

        ta.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (isNeedRedraw) {
            resetItems();
        }

        super.onDraw(canvas);
    }


    private void resetItems() {
        if (getChildCount() == 0) {
            for (int i = 0; i < numStars; i++) {
                AnimatedRatingBarItem item = new AnimatedRatingBarItem(getContext());
                LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(starSize, starSize);
                itemParams.leftMargin = i == 0 ? 0 : gapSize;
                item.setLayoutParams(itemParams);
                addView(item);
            }
        }

        float progressStars = numStars * rating / max;
        int fillStars = (int) progressStars;
        float levelStar = progressStars - fillStars;

        for (int i = 0; i < numStars; i++) {
            AnimatedRatingBarItem item = (AnimatedRatingBarItem) getChildAt(i);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) item.getLayoutParams();
            params.leftMargin = i == 0 ? 0 : gapSize;
            item.setLayoutParams(params);

            float level = i < fillStars ? 1 : i == fillStars ? levelStar : 0;
            item.setProgressLevel(level);
            item.setProgressImageDrawable(progressImage);
            item.setSecondaryProgressImageDrawable(secondaryProgressImage);
        }

        isNeedRedraw = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!seekable) {
            return false;
        }
        if (event.getX() < 0) {
            setRating(0);
            return true;
        } else if (event.getX() > getWidth()) {
            setRating(max);
            return true;
        }
        float rating = event.getX() / getWidth() * 5;
        rating = Math.round(rating * 100) / 100f;
        Log.v("TAG", "rating : " + rating);
        setRating(rating);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode == MeasureSpec.AT_MOST && widthMode == MeasureSpec.AT_MOST) {
            int width = starSize * numStars + gapSize * (numStars - 1) + getPaddingLeft() + getPaddingRight();
            int height = starSize + getPaddingTop() + getPaddingBottom();

            setMeasuredDimension(width, height);
            return;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setProgressImageResource(int resourceId) {
        if (resourceId == 0) {
            return;
        }

        Drawable drawable = getResources().getDrawable(resourceId);
        setProgressImageDrawable(drawable);
    }

    @Override
    public void setProgressImageDrawable(Drawable drawable) {
        this.progressImage = drawable;
        isNeedRedraw = true;
        postInvalidate();
    }

    @Override
    public void setSecondaryProgressImageResource(int resourceId) {
        if (resourceId == 0) {
            return;
        }

        Drawable drawable = getResources().getDrawable(resourceId);
        setSecondaryProgressImageDrawable(drawable);
    }

    @Override
    public void setSecondaryProgressImageDrawable(Drawable drawable) {
        this.secondaryProgressImage = drawable;
        isNeedRedraw = true;
        postInvalidate();
    }

    @Override
    public void setNumStars(int stars) {
        if (this.numStars == stars) {
            return;
        }

        this.numStars = stars;
        isNeedRedraw = true;
        postInvalidate();
    }

    @Override
    public void setRating(float rating) {
        if (this.rating == rating) {
            return;
        }

        if (max < rating) {
            rating = max;
        }

        this.rating = rating;
        isNeedRedraw = true;
        postInvalidate();
    }

    @Override
    public void setStarGap(int gapSize) {
        if (this.gapSize == gapSize) {
            return;
        }

        this.gapSize = gapSize;
        isNeedRedraw = true;
        postInvalidate();
    }


    @Override
    public void setOnRatingChangedListener(OnRatingChangedListener listener) {
        this.listener = listener;
    }


    @Override
    public void setSeekable(boolean seekable) {
        this.seekable = seekable;
    }


    @Override
    public void setAnimateDuration(int duration) {
        this.duration = duration;
    }


    @Override
    public void startAnimate() {
        int delay = 0;
        for (int i = 0; i < numStars; i++) {
            final int position = i;
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    ValueAnimator animator = ValueAnimator.ofInt(1, 1);
                    animator.setDuration(duration);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            float fraction = animation.getAnimatedFraction();
                            getChildAt(position).setRotationY((fraction * 3 * 360) % 360);
                        }
                    });
                    animator.start();
                }
            }, delay);

            delay += duration / numStars;
        }
    }

}
