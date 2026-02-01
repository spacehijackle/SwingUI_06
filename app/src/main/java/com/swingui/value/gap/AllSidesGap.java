package com.swingui.value.gap;

import com.swingui.constant.UIDefaults;
import com.swingui.value.gap.UIGap.Bottom;
import com.swingui.value.gap.UIGap.Left;
import com.swingui.value.gap.UIGap.Right;
import com.swingui.value.gap.UIGap.Top;
import com.swingui.value.gap.Symmetry.Horizontal;
import com.swingui.value.gap.Symmetry.Vertical;

/**
 * 四方（{@link Left}, {@link Top}, {@link Right}, {@link Bottom}）のギャップ（間隔）値提供クラス
 * 
 * @author t.yoshida
 */
public class AllSidesGap
{
    /** 左側の間隔 */
    public final Left left;

    /** 上部の間隔 */
    public final Top top;

    /** 右側の間隔 */
    public final Right right;

    /** 下部の間隔 */
    public final Bottom bottom;

    private AllSidesGap(Left left, Top top, Right right, Bottom bottom)
    {
        this.left   = left;
        this.top    = top;
        this.right  = right;
        this.bottom = bottom;
    }

    /**
     * デフォルトの間隔値で {@code AllSidesGap} を生成する。
     * 
     * @return {@code AllSidesGap}
     */
    public static AllSidesGap defaults()
    {
        Left   left   = Left.of(UIDefaults.COMPONENT_GAP);
        Top    top    = Top.of(UIDefaults.COMPONENT_GAP);
        Right  right  = Right.of(UIDefaults.COMPONENT_GAP);
        Bottom bottom = Bottom.of(UIDefaults.COMPONENT_GAP);

        return new AllSidesGap(left, top, right, bottom);
    }

    /**
     * 指定された間隔値で {@code AllSidesGap} を生成する。
     * 
     * @param gaps 間隔値（Left, Top, Right, Bottom）
     * @return {@code AllSidesGap}
     */
    public static AllSidesGap of(UIGap... gaps)
    {
        // 四方のパディング決定
        // ※デフォルト値を設定した後、指定された値で上書き
        AllSidesGap paddings = defaults();
        Left   left   = paddings.left;
        Top    top    = paddings.top;
        Right  right  = paddings.right;
        Bottom bottom = paddings.bottom;
        for(UIGap gap : gaps)
        {
            if(gap instanceof Left)   left   = (Left)gap;
            if(gap instanceof Top)    top    = (Top)gap;
            if(gap instanceof Right)  right  = (Right)gap;
            if(gap instanceof Bottom) bottom = (Bottom)gap;
        }

        return new AllSidesGap(left, top, right, bottom);
    }

    /**
     * 指定された間隔値で {@code AllSidesGap} を生成する。
     * 
     * @param gaps 間隔値（Horizontal, Vertical）
     * @return {@code AllSidesGap}
     */
    public static AllSidesGap of(Symmetry... symmetries)
    {
        // 四方のパディング決定
        // ※デフォルト値を設定した後、指定された値で上書き
        AllSidesGap paddings = defaults();
        Left   left   = paddings.left;
        Top    top    = paddings.top;
        Right  right  = paddings.right;
        Bottom bottom = paddings.bottom;
        for(Symmetry symmetry : symmetries)
        {
            if(symmetry instanceof Horizontal)
            {
                Horizontal horizontal = (Horizontal)symmetry;
                left  = Left.of(horizontal.gap);
                right = Right.of(horizontal.gap);
            }
            else if(symmetry instanceof Vertical)
            {
                Vertical vertical = (Vertical)symmetry;
                top    = Top.of(vertical.gap);
                bottom = Bottom.of(vertical.gap);
            }
        }

        return new AllSidesGap(left, top, right, bottom);
    }
}
