package com.chanpay.lib_view.banner.holder;

/**
 * Created by zhouwei on 17/5/26.
 */

public interface MZHolderCreator<VH extends MZViewHolder> {
    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}
