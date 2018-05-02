package cn.jarlen.widget.ninegridlayout.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.jarlen.richcommon.adapter.RvCommonAdapter;
import cn.jarlen.richcommon.adapter.RvViewHolder;
import cn.jarlen.widget.gridlayout.NineGridLayout;
import cn.jarlen.widget.gridlayout.NineGridLayoutAdapter;

public class NineGridLayoutActivity extends Activity {

    private String[] pics = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524549349083&di=e2e8d1920745ce2aef76c4ecb0a6209e&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fphotoblog%2F1207%2F18%2Fc3%2F12381706_12381706_1342623251484_mthumb.jpg",
            "https://pic4.zhimg.com/02685b7a5f2d8cbf74e1fd1ae61d563b_xll.jpg",
            "https://pic4.zhimg.com/fc04224598878080115ba387846eabc3_xll.jpg",
            "https://pic3.zhimg.com/d1750bd47b514ad62af9497bbe5bb17e_xll.jpg",
            "https://pic4.zhimg.com/da52c865cb6a472c3624a78490d9a3b7_xll.jpg",
            "https://pic3.zhimg.com/0c149770fc2e16f4a89e6fc479272946_xll.jpg",
            "https://pic1.zhimg.com/76903410e4831571e19a10f39717988c_xll.png",
            "https://pic3.zhimg.com/33c6cf59163b3f17ca0c091a5c0d9272_xll.jpg",
            "https://pic4.zhimg.com/52e093cbf96fd0d027136baf9b5cdcb3_xll.png",
            "https://pic3.zhimg.com/f6dc1c1cecd7ba8f4c61c7c31847773e_xll.jpg",
    };

    private RecyclerView recyclerView;

    private PicAdapter picAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine_grid_layout);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        picAdapter = new PicAdapter(this);
        recyclerView.setAdapter(picAdapter);
    }

    public class PicAdapter extends RvCommonAdapter<Bean> {

//        private NineGridLayoutAdapter<PicBean> nineGridLayoutAdapter = new

        public PicAdapter(Context context) {
            super(context);
        }

        @Override
        public void onBindView(RvViewHolder viewHolder, Bean item) {
            TextView nameTv = viewHolder.getView(R.id.name);
            NineGridLayout nineGridLayout = viewHolder.getView(R.id.nineGridLayout);

            nameTv.setText(item.getName());
            nineGridLayout.setViewsData(item.getPicList());
            nineGridLayout.setAdapter(new NineGridLayoutAdapter() {

                @Override
                public View onCreateView(ViewGroup parentView, int position) {
                    return null;
                }

                @Override
                public int getItemViewType(int position) {
                    return 0;
                }

                @Override
                public void onBindView(ViewGroup parentView, View view, int position) {

                }
            });
        }

        @Override
        public int getLayoutResId(int viewType) {
            return R.layout.layout_nine_grid_item;
        }
    }


    public class Bean {
        String name;

        List<PicBean> picList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<PicBean> getPicList() {
            return picList;
        }

        public void setPicList(List<PicBean> picList) {
            this.picList = picList;
        }
    }

    public class PicBean {
        int type;
        String picUrl;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
    }

}
