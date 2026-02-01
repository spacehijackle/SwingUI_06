package com.swingui.value.size;

import java.awt.Dimension;

import com.swingui.value.size.UILength.Height;
import com.swingui.value.size.UILength.Width;

/**
 * UIサイズ（W:幅・H:高さ）提供クラス
 * 
 * @author t.yoshida
 */
public class WxHSize
{
    /** 幅 */
    public final Width width;

    /** 高さ */
    public final Height height;

    /**
     * 指定された幅と高さで {@code WxHSize} を生成する。
     * 
     * @param width 幅
     * @param height 高さ
     */
    private WxHSize(Width width, Height height)
    {
        this.width = width;
        this.height = height;
    }

    /**
     * 幅・高さともにゼロの {@code WxHSize} を生成する。
     * 
     * @return {@code WxHSize}
     */
    public static WxHSize zero()
    {
        return new WxHSize(Width.of(0), Height.of(0));
    }

    /**
     * 指定された {@code Dimension} から {@code WxHSize} を生成する。
     * 
     * @param dim {@code Dimension}
     * @return {@code WxHSize}
     */
    public static WxHSize from(Dimension dim)
    {
        return new WxHSize(Width.of(dim.width), Height.of(dim.height));
    }

    /**
     * デフォルト値と指定されたサイズで {@code WxHSize} を生成する。
     *
     * @param defaults 幅・高さのデフォルト値
     * @param lengths 指定されたサイズ
     * @return {@code WxHSize}
     */
    public static WxHSize of(WxHSize defaults, UILength... lengths)
    {
        Width  width  = defaults.width;
        Height height = defaults.height;
        for(UILength length : lengths)
        {
            if(length instanceof Width)  width  = (Width)length;
            if(length instanceof Height) height = (Height)length;
        }

        return new WxHSize(width, height);
    }
}