package com.swingui.widget.util;

import java.awt.Dimension;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.swingui.value.gap.AllSidesGap;
import com.swingui.value.gap.UIGap;
import com.swingui.value.size.UILength;
import com.swingui.value.size.WxHSize;
import com.swingui.widget.Framer;
import com.swingui.widget.Widget;

/**
 * {@link Widget} 関連のユーティリティ・クラス
 * 
 * @author t.yoshida
 */
public class WidgetHelper
{
    /**
     * ウィジェットが属するフレームまで遡り、下位コンポーネント全体の更新を行う。
     */
    public static <T extends JComponent> void invokeToRefresh(Widget<T> widget)
    {
        Window w = SwingUtilities.getWindowAncestor((JComponent)widget);
        if(w != null)
        {
            if(w instanceof Framer)
            {
                ((Framer)w).refreshWT();
            }
        }
    }

    /**
     * 指定コンポーネントのパディングの設定をする。
     * 
     * @param <T> JComponentの継承クラス
     * @param target 対象コンポーネント
     * @param gaps 四方（left, top, right, bottom）のパディング
     * @return 対象コンポーネント
     */
    public static <T extends JComponent> T padding(T target, UIGap... gaps)
    {
        // 四方のパディング取得
        AllSidesGap sides = AllSidesGap.of(gaps);

        // パディング設定
        target.setBorder
        (
            BorderFactory.createCompoundBorder
            (
                target.getBorder(),
                BorderFactory.createEmptyBorder
                (
                    sides.top.gap, sides.left.gap, sides.bottom.gap, sides.right.gap
                )
            )
        );
        return target;
    }

    /**
     * 指定コンポーネントのサイズの設定をする。
     * 
     * @param <T> JComponentの継承クラス
     * @param target 対象コンポーネント
     * @param lengths 幅・高さサイズ
     * @return 対象コンポーネント
     */
    public static <T extends JComponent> T frame(T target, UILength... lengths)
    {
        // 幅・高さ決定
        WxHSize defaults = WxHSize.from(target.getPreferredSize());
        WxHSize size = WxHSize.of(defaults, lengths);

        // サイズ設定
        if(size.width.isInfinite() || size.height.isInfinite())
        {
            // 幅または高さが最大限の場合、柔軟なサイズとして設定
            int minW  = (size.width.isInfinite()  ? 0 : size.width.length);
            int minH  = (size.height.isInfinite() ? 0 : size.height.length);
            target.setMinimumSize(new Dimension(minW, minH));

            int prefW = (size.width.isInfinite()  ? defaults.width.length  : size.width.length);
            int prefH = (size.height.isInfinite() ? defaults.height.length : size.height.length);
            target.setPreferredSize(new Dimension(prefW, prefH));

            target.setMaximumSize(new Dimension(size.width.length, size.height.length));
        }
        else
        {
            // 幅・高さに最大限の値を含まない場合、固定値として設定
            target.setMaximumSize(new Dimension(size.width.length, size.height.length));
            target.setMinimumSize(new Dimension(size.width.length, size.height.length));
            target.setPreferredSize(new Dimension(size.width.length, size.height.length));
        }

        return target;
    }
}
