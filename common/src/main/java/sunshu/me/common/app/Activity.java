package sunshu.me.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * author：sunshu
 * time：2019/2/24:21:58
 * desc：
 */
public abstract class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (initArgs(getIntent().getExtras())){
            int layoutId = getContentLayoutId();
            setContentView(layoutId);
            initWidget();
            initData();
        }else {
            finish();
        }
    }

    /**
     * 初始化窗口
     */
    protected void initWindows(){

    }

    /**
     * 初始化相关参数
     * @param args
     * @return
     */
    protected  boolean initArgs(Bundle args){
        return true;
    }
    protected  abstract  int getContentLayoutId();

    /**
     * 资源
     */
    protected  void initWidget(){
        ButterKnife.bind(this);
    }

    /**
     * 数据
     */
    protected  void initData(){

    }

    @Override
    public boolean onSupportNavigateUp() {
        // 点击界面导航返回
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments!= null && fragments.size() > 0 ){
            if (fragments instanceof sunshu.me.common.app.Fragment){
                if (((sunshu.me.common.app.Fragment) fragments).onBackPress()){
                    return;
                }
            }
        }

        super.onBackPressed();

    }
}
