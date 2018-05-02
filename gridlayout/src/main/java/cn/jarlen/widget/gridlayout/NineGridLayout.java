package cn.jarlen.widget.gridlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarlen on 2018/4/26.
 */

public class NineGridLayout<T> extends ViewGroup {

    private int mMaxSize;                       // 最大View数
    private int mRowCount;                      // 行数
    private int mColumnCount;                   // 列数
    private int mItemSpace;                     //宫格间间隔大小

    private SparseArray<View> mViews = new SparseArray<>();
    private List<T> mViewDatas = new ArrayList<>();
    private NineGridLayoutAdapter<T> mAdapter;

    public NineGridLayout(Context context) {
        this(context, null);
    }

    public NineGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NineGridLayout);
        this.mItemSpace = (int) typedArray.getDimension(R.styleable.NineGridLayout_ngl_item_space, 0);
        this.mMaxSize = typedArray.getInt(R.styleable.NineGridLayout_ngl_max_size, 9);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int totalWidth = width - getPaddingLeft() - getPaddingRight();
        Log.e("jarlen", "onMeasure   --->   width = " + width + "  height = " + height);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e("jarlen", "onLayout");
    }


    public void setViewsData(List<T> viewDatas) {
        if (viewDatas == null || viewDatas.isEmpty()) {
            setVisibility(View.GONE);
            return;
        } else {
            setVisibility(View.VISIBLE);
        }

        int oldViewCount = getChildCount();
        if (oldViewCount > 0) {
            removeAllViews();
        }
        int newViewCount = getNeedShowCount(viewDatas.size());
        int[] gridParam = calculateGridParam(newViewCount);
        mRowCount = gridParam[0];
        mColumnCount = gridParam[1];

        for (int index = 0; index < newViewCount; index++) {
            View childView = getItemView(index);
            if (childView == null) {
                return;
            }
            addView(childView, generateDefaultLayoutParams());
        }
        this.mViewDatas = viewDatas;
        requestLayout();
    }

    private View getItemView(int position) {
        View childView = null;
        if (mAdapter != null) {
            View view = mViews.get(mAdapter.getItemViewType(position));
            if (view == null) {
                view = mAdapter.onCreateView(this, position);
            }
            childView = view;
        }
        return childView;
    }

    public void setAdapter(NineGridLayoutAdapter<T> mAdapter) {
        this.mAdapter = mAdapter;
        requestLayout();
    }

    /**
     * 计算九宫格行列数目
     *
     * @param viewsSize view个数
     * @return 宫格参数 gridParam[0] 宫格行数 gridParam[1] 宫格列数
     */
    protected int[] calculateGridParam(int viewsSize) {
        int[] gridParam = new int[2];
        gridParam[0] = viewsSize / 3 + (viewsSize % 3 == 0 ? 0 : 1);
        gridParam[1] = 3;
        return gridParam;
    }

    private int getNeedShowCount(int size) {
        if (mMaxSize > 0 && size > mMaxSize) {
            return mMaxSize;
        } else {
            return size;
        }
    }
}
