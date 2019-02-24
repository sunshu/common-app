package sunshu.me.common.widget;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import sunshu.me.common.R;

/**
 * author：sunshu
 * time：2019/2/24:22:40
 * desc：
 */
public abstract class RecyclerAdapter<Data>
        extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Data>>
        implements View.OnClickListener,View.OnLongClickListener ,AdapterCallBack<Data>{

    private final List<Data> mDataList ;
    private AdapterListener<Data> adapterListener;

    public RecyclerAdapter() {
        this(null);
    }
    public RecyclerAdapter(AdapterListener<Data> listener) {
        this(new ArrayList<Data>(),listener);
    }

    public RecyclerAdapter(List<Data> dataList,AdapterListener<Data> listener) {
        this.mDataList = dataList;
        this.adapterListener = listener;
    }

    /**
     * 复写默认的布局类型返回
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position,mDataList.get(position));
    }

    /**
     * 返回的都是XML布局的Id
     * @param position
     * @param data
     * @return
     */
    @LayoutRes
    protected abstract int getItemViewType(int position,Data data);

    /**
     * 创建一个新的ViewHolder
     * @param viewGroup
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(viewType,viewGroup,false);
        ViewHolder holder = onCreateViewHolder(root,viewType);

        root.setOnClickListener(this);
        root.setOnLongClickListener(this);

        root.setTag(R.id.tag_recycler_holder,holder);

        holder.unbinder = ButterKnife.bind(holder,root);

        holder.callBack = this;

        return holder;
    }

    protected abstract ViewHolder<Data> onCreateViewHolder(View holder,int ViewType);





    /**
     * 绑定数据到Holder
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Data data = mDataList.get(i);
        viewHolder.bind(data);
    }



    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void addData(Data data){
        mDataList.add(data);
        notifyItemInserted(mDataList.size() -1);
    }
    public void addData(Data... dataList){
        if (dataList!= null && dataList.length > 0){
            int startPos = mDataList.size();
            Collections.addAll(mDataList,dataList);
            notifyItemRangeChanged(startPos,mDataList.size() -1);
        }
    }

    public void addData(Collection dataList){
        if (dataList!= null && dataList.size() > 0){
            int startPos = mDataList.size();
            mDataList.addAll(dataList);
            notifyItemRangeChanged(startPos,mDataList.size() -1);
        }
    }

    public void clean(){
        mDataList.clear();
        notifyDataSetChanged();
    }

    public void replace(Collection<Data> data){
        mDataList.clear();
        if (data != null && data.size() > 0){
            mDataList.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        ViewHolder viewHolder = (ViewHolder) v.getTag(R.id.airPanelSubLayout);
        if (adapterListener != null){
            int position = viewHolder.getAdapterPosition();
            adapterListener.onItemClick(viewHolder,mDataList.get(position));
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ViewHolder viewHolder = (ViewHolder) v.getTag(R.id.airPanelSubLayout);
        if (adapterListener != null){
            int position = viewHolder.getAdapterPosition();
            adapterListener.onItemLongClick(viewHolder,mDataList.get(position));
            return true;
        }else {
            return false;
        }
    }

    public void setAdapterListener(AdapterListener listener){
        this.adapterListener = listener;
    }
    public interface AdapterListener<Data>{
        void onItemClick(RecyclerAdapter.ViewHolder holder,Data data);
        void onItemLongClick(RecyclerAdapter.ViewHolder holder,Data data);
    }


    public static abstract class ViewHolder<Data> extends RecyclerView.ViewHolder {
        protected Data mData;
        private Unbinder unbinder;
        private AdapterCallBack<Data> callBack;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        /**
         * 用于绑定数据的触发
         * @param data
         */
        void bind(Data data){
            this.mData = data;
            onBind(data);
        }

        protected abstract void onBind(Data data);

        public void update(Data data){
            if (callBack != null){
                callBack.update(data,this);
            }
        }


    }

}
