package sunshu.me.common.widget;

/**
 * author：sunshu
 * time：2019/2/24:22:57
 * desc：
 */
public interface AdapterCallBack<Data>     {
    void update(Data data,RecyclerAdapter.ViewHolder<Data> holder);
}
