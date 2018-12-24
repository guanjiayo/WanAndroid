package zs.xmx.home.ui.adapter.vlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.chad.library.adapter.base.BaseViewHolder;

import androidx.annotation.NonNull;

/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/11/04 17:22
 * @本类描述	  VLayout适配器
 * @内容说明
 * @补充内容 todo 转为我们自己的BaseViewHolder,再转kotlin语法
 *
 * ---------------------------------
 * @新增内容
 *
 */
public class BaseVLayoutAdapter extends DelegateAdapter.Adapter<BaseViewHolder> {

    private LayoutHelper mLayoutHelper;
    private int          mCount        = -1;
    private int          mLayoutId     = -1;
    private Context      mContext;
    private int          mViewTypeItem = -1;

    public BaseVLayoutAdapter(Context context, LayoutHelper layoutHelper, int layoutId, int count, int viewTypeItem) {
        this.mContext = context;
        this.mCount = count;
        this.mLayoutHelper = layoutHelper;
        this.mLayoutId = layoutId;
        this.mViewTypeItem = viewTypeItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == mViewTypeItem) {
            return new BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {

    }

    @Override
    public int getItemViewType(int position) {
        return mViewTypeItem;
    }

    @Override
    public int getItemCount() {
        return mCount;
    }
}
