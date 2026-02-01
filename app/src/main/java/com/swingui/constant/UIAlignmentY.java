package com.swingui.constant;

import java.awt.Component;

/**
 * 垂直方向の配置方法の列挙型定義
 * 
 * @author t.yoshida
 */
public enum UIAlignmentY
{
    /** 上部寄せ */
    Top(Component.TOP_ALIGNMENT),

    /** 中央寄せ */
    Center(Component.CENTER_ALIGNMENT),

    /** 下部寄せ */
    Bottom(Component.BOTTOM_ALIGNMENT);

    public final float value;

    private UIAlignmentY(float value)
    {
        this.value = value;
    }
}
