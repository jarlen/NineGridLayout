package cn.jarlen.widget.gridlayout;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jarlen on 2018/4/26.
 */

public interface NineGridLayoutAdapter<T> {

    View onCreateView(ViewGroup parentView, int position);

    int getItemViewType(int position);

    void onBindView(ViewGroup parentView, View view, int position);
}
