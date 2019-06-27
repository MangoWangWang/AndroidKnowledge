package com.chanpay.lib_view.banner.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zhouwei on 17/5/26.
 */

public class ScaleYTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.9F;
    private static final float MIN_ALPHA = 0.5F;
    @Override
    public void transformPage(View page, float position) {

        if(position < -1){
            page.setScaleY(MIN_SCALE);
        }else if(position<= 1){
            //
            float scale = Math.max(MIN_SCALE,1 - Math.abs(position));
            page.setScaleY(scale);
            /*page.setScaleX(scale);

            if(position<0){
                page.setTranslationX(width * (1 - scale) /2);
            }else{
                page.setTranslationX(-width * (1 - scale) /2);
            }*/

        }else{
            page.setScaleY(MIN_SCALE);
        }

        if (position<=0)
        {
            float scale = Math.max(MIN_ALPHA,1 - Math.abs(position));
            page.setAlpha(scale);
        }
        if (0<position)
        {
            float scale = Math.max(MIN_ALPHA,1 - Math.abs(position));
            page.setAlpha(scale);
        }

    }

}
