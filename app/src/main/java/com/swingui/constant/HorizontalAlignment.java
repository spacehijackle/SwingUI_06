package com.swingui.constant;

import javax.swing.SwingConstants;

/**
 * 水平方向の配置方法の列挙型定義
 * 
 * @author t.yoshida
 */
public enum HorizontalAlignment
{
    /** コンテナの方向のリーディング・エッジ */
    Leading(SwingConstants.LEADING),

    /** 領域内の中央位置 */
    Center(SwingConstants.CENTER),

    /** コンテナの方向のトレーリング・エッジ */
    Trailing(SwingConstants.TRAILING);

    public final int value;

    private HorizontalAlignment(int value)
    {
        this.value = value;
    }
}
