package com.swingui.front.layout;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

import com.swingui.constant.UIAlignmentY;
import com.swingui.value.Spacing;
import com.swingui.widget.PanelWT;

/**
 * コンポーネントを横に並べるレイアウト提供クラス
 * 
 * @author t.yoshida
 */
public class HStack
{
    /**
     * 指定されたコンポーネントを横に並べる。
     *
     * @param components 並べるコンポーネント群
     * @return {@link PanelWT}
     */
    public static PanelWT of(JComponent... components)
    {
        return init(UIAlignmentY.Center, Spacing.defaults(), components);
    }

    /**
     * 垂直方向の位置指定をし、指定されたコンポーネントを横に並べる。
     * 
     * @param alignmentY 垂直方向の位置指定
     * @param components 並べるコンポーネント群
     * @return {@link PanelWT}
     */
    public static PanelWT of(UIAlignmentY alignmentY, JComponent... components)
    {
        return init(alignmentY, Spacing.defaults(), components);
    }

    /**
     * 指定された間隔でコンポーネントを横に並べる。
     * 
     * @param spacing コンポーネント間の間隔
     * @param components 並べるコンポーネント群
     * @return {@link PanelWT}
     */
    public static PanelWT of(Spacing spacing, JComponent... components)
    {
        return init(UIAlignmentY.Center, spacing, components);
    }

    /**
     * 垂直方向の位置指定をし、指定されたコンポーネントを横に並べる。
     *
     * @param alignmentY 垂直方向の位置
     * @param spacing コンポーネント間の間隔
     * @param components 並べるコンポーネント群
     * @return {@link PanelWT}
     */
    public static PanelWT of(UIAlignmentY alignmentY, Spacing spacing, JComponent... components)
    {
        return init(alignmentY, spacing, components);
    }

    /**
     * 初期化処理
     * 
     * @param alignmentY 垂直方向の位置指定
     * @param spacing コンポーネント間の間隔
     * @param components 並べるコンポーネント群
     * @return {@link PanelWT}
     */
    private static PanelWT init(UIAlignmentY alignmentY, Spacing spacing, JComponent... components)
    {
        /*
         * Box.createGlue() について
         *
         * これを追加するとウィンドウを拡大した際、子コンポーネントのサイズが拡大に合わせて
         * いっぱいに広がらないため（JList, ListBoxWT等）、使用しない方針である。
         */

        PanelWT panel = new PanelWT();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        //panel.add(Box.createGlue());  // フレームが広がった場合の上部調整用スペース
        for(int i=0; i<components.length; i++)
        {
            JComponent c = components[i];

            c.setAlignmentX(Component.CENTER_ALIGNMENT);
            c.setAlignmentY(alignmentY.value);
            //panel.add(Box.createGlue());  // フレームが広がった場合のコンポーネント間調整用スペース
            panel.add(c);

            // コンポーネント間の間隔設定
            boolean isTail = (i == (components.length - 1));
            if(!isTail)
            {
                panel.add(Box.createRigidArea(new Dimension(spacing.gap, 0)));
            }
        }
        //panel.add(Box.createGlue());  // フレームが広がった場合の下部調整用スペース

        return panel;
    }
}