package com.me.obo.myapplication.pullrefresh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.me.obo.myapplication.R;
import com.me.obo.myapplication.tool.ScreenUtil;

/**
 * @author obo
 * @date 2018/1/23
 */

public class PullToRefreshLayout extends ViewGroup {

    private static final String TAG = "PullToRefreshLayout";

    private static final int TYPE_TARGET_LIST = 1;
    private static final int TYPE_TARGET_RECYCLER = 2;

    private static final float DAMPING_RATE = 0.3f;

    private View mHeadLoadingView;
    private View mTargeView;
    private int mHeadLoadingViewHeight;
    private int mMovement = 0;
    private int mTargetViewType;
    private PullToRefreshListener mPullToRefreshListener;
    private boolean mIsRefreshing = false;

    private ProgressBar mProgressBar;

    public PullToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView(generalHeadLoadingView());
    }

    private View generalHeadLoadingView() {
        mHeadLoadingView = LayoutInflater.from(getContext()).inflate(R.layout.layout_head_loading, this, false);
        mProgressBar = mHeadLoadingView.findViewById(R.id.pb_progress);
        mProgressBar.setMax(100);
        mProgressBar.setIndeterminate(false);
        mProgressBar.setProgress(0);
        return mHeadLoadingView;
    }

    public void setPullToRefreshListener(PullToRefreshListener pullToRefreshListener) {
        this.mPullToRefreshListener = pullToRefreshListener;
    }

    public void stopRefreshing() {
        if (mIsRefreshing) {
            final ValueAnimator valueAnimator = ValueAnimator.ofInt(mMovement, 0);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mMovement = (int) animation.getAnimatedValue();
                    requestLayout();
                }
            });
            valueAnimator.start();
            mIsRefreshing = false;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure");
        int count = getChildCount();
        if (count < 2) {
            Log.e(TAG, "Child views should be more than 2");
            return;
        }

        mHeadLoadingViewHeight = ScreenUtil.dip2px(getContext(), 60);
        if (mHeadLoadingView == null) {
            mHeadLoadingView = getChildAt(0);
        }

        mHeadLoadingView.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(mHeadLoadingViewHeight, MeasureSpec.EXACTLY));
        if (mTargeView == null) {
            mTargeView = getChildAt(1);
        }
        if (mTargeView instanceof ListView) {
            mTargetViewType = TYPE_TARGET_LIST;
        } else if (mTargeView instanceof RecyclerView) {
            mTargetViewType = TYPE_TARGET_RECYCLER;
        }
        mTargeView.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "onLayout");
        mHeadLoadingView.layout(0, -mHeadLoadingViewHeight + mMovement, r,  mMovement);
        mTargeView.layout(0, mMovement, r, b);
    }

    private float mFirstDownY;
    private float mDragY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent");
        if (mIsRefreshing) {
            return super.onInterceptTouchEvent(ev);
        }
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mFirstDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float direction = ev.getY() - mFirstDownY;
                if (direction > 0) {
                    if (mTargetViewType == TYPE_TARGET_LIST) {
                        ListView listView = (ListView) mTargeView;
                        if (!listView.canScrollVertically(-1)) {
                            mDragY = ev.getY();
                            return true;
                        }
                    } else if (mTargetViewType == TYPE_TARGET_RECYCLER) {
                        RecyclerView recyclerView = (RecyclerView) mTargeView;
                        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        if (layoutManager instanceof LinearLayoutManager) {
                            if (((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                                mDragY = ev.getY();
                                return true;
                            }
                        } else if (layoutManager instanceof GridLayoutManager) {
                            if (((GridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0) {
                                mDragY = ev.getY();
                                return true;
                            }
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent");
        mMovement = (int) ((event.getY() - mDragY) * DAMPING_RATE);
        requestLayout();
        int progress = mMovement * 100 / mHeadLoadingViewHeight;
        if (progress > 100) {
            progress = 100;
        }
        mProgressBar.setProgress(progress);
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            triggerLoading();
        }
        return true;
    }

    private void triggerLoading() {
        int targetAnimationValue = 0;
        if (mMovement > mHeadLoadingViewHeight) {
            if (mPullToRefreshListener != null) {
                mPullToRefreshListener.onRefresh();
            }
            targetAnimationValue = mHeadLoadingViewHeight;
            mIsRefreshing = true;
        }



        final ValueAnimator valueAnimator = ValueAnimator.ofInt(mMovement, targetAnimationValue);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mMovement = (int) animation.getAnimatedValue();
                requestLayout();
            }
        });
        valueAnimator.start();
    }

    /**
     * 拦截该方法后，onInterceptTouchEvent就可以不断接收到事件
     * @param disallowIntercept
     */
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
}
