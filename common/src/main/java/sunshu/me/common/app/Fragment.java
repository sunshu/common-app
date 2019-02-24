package sunshu.me.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：sunshu
 * time：2019/2/24:21:58
 * desc：
 */
public  abstract class Fragment extends android.support.v4.app.Fragment {

    private View mRoot;
    private Unbinder mRootBind;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    /**
     * 初始化相关参数
     * @param args
     * @return
     */
    protected  void initArgs(Bundle args){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null){
            int layoutId = getContentLayoutId();
            View root = inflater.inflate(layoutId,container,false);
            initWidget(root);
            mRoot = root;
        }else{
            if (mRoot.getParent() != null){
                // 把root从父root移除
                ((ViewGroup)mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected  abstract  int getContentLayoutId();

    /**
     * 资源
     * @param root
     */
    protected  void initWidget(View root){
        mRootBind = ButterKnife.bind(root);
    }

    /**
     * 数据
     */
    protected  void initData(){

    }

    /**
     * 返回按键时候调用
     * @return true 已经处理,false 未处理
     */
    public boolean onBackPress(){
        return false;
    }
}
