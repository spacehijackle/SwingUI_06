package com.swingui.constant;

import java.awt.Component;

/**
 * 水平方向の配置方法の列挙型定義
 * 
 * @author t.yoshida
 */
public enum UIAlignmentX
{
    /** 左寄せ */
    Leading(Component.LEFT_ALIGNMENT),

    /** 中央寄せ */
    Center(Component.CENTER_ALIGNMENT),

    /** 右寄せ */
    Trailing(Component.RIGHT_ALIGNMENT);

    public final float value;

    private UIAlignmentX(float value)
    {
        this.value = value;
    }
}
