package com.swingui.front.layout;

import java.awt.Dimension;

import com.swingui.value.size.UILength;
import com.swingui.value.size.UILength.Height;
import com.swingui.value.size.UILength.Width;
import com.swingui.value.size.WxHSize;
import com.swingui.widget.PanelWT;

/**
 * スペーサー部品提供クラス
 * 
 * @author t.yoshida
 */
public class Spacer
{
    /**
     * 指定した幅・高さのスペース領域を確保する。
     * 
     * @param lengths 幅・高さのサイズ
     * @return {@code PanelWT}
     */
    public static PanelWT of(UILength... lengths)
    {
        // 幅・高さスペースの取得
        WxHSize size = WxHSize.of(WxHSize.zero(), lengths);

        // 幅・高さスペースの設定
        if(size.width.isInfinite() || size.height.isInfinite())
        {
            // 幅または高さが最大限の場合、柔軟なスペース領域を確保
            return flexible(size);
        }
        else
        {
            // 幅・高さに最大限の値を含まない場合、固定のスペース領域を確保
            return fixed(size);
        }
    }

    /**
     * レイアウトマネージャの制限内で最大限のスペース領域を確保する。
     * 
     * @return {@code PanelWT}
     */
    public static PanelWT fill()
    {
        return flexible(WxHSize.of(WxHSize.zero(), Width.Infinite, Height.Infinite));
    }

    /**
     * 指定したサイズで柔軟なスペース領域を確保する。
     * 
     * @param size サイズ
     * @return {@code PanelWT}
     */
    private static PanelWT flexible(WxHSize size)
    {
        //
        // レイアウトに応じて PanelWT を拡縮させる場合、
        // PanelWT#setPreferredSize() は設定しない。
        //
        PanelWT panel = new PanelWT();
        int minW  = (size.width.isInfinite()  ? 0 : size.width.length);
        int minH  = (size.height.isInfinite() ? 0 : size.height.length);
        panel.setMinimumSize(new Dimension(minW, minH));
        panel.setMaximumSize(new Dimension(size.width.length, size.height.length));

        return panel;
    }

    /**
     * 指定したサイズで固定のスペース領域を確保する。
     * 
     * @param size サイズ
     * @return {@code PanelWT}
     */
    private static PanelWT fixed(WxHSize size)
    {
        //
        // サイズ固定の PanelWT を生成する場合、
        // PanelWT#setPreferredSize() を含めて設定する。
        //
        PanelWT panel = new PanelWT();
        panel.setPreferredSize(new Dimension(size.width.length, size.height.length));
        panel.setMinimumSize(new Dimension(size.width.length, size.height.length));
        panel.setMaximumSize(new Dimension(size.width.length, size.height.length));

        return panel;
    }
}
