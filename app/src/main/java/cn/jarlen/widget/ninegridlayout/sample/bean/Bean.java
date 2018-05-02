package cn.jarlen.widget.ninegridlayout.sample.bean;

import java.util.List;

/**
 * Created by jarlen on 2018/5/2.
 */

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

    public static class PicBean {
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
