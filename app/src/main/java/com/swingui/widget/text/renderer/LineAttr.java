package com.swingui.widget.text.renderer;

import java.awt.Color;

/**
 * 線属性クラス
 * 
 * @author t.yoshida
 */
public interface LineAttr
{
    /**
     * 線スタイル定義
     */
    public enum LineStyle implements LineAttr
    {
        /** 実線 */
        Solid,

        /** 太線 */
        Bold,

        /** 二重線 */
        Double,
    }

    /**
     * 線カラークラス
     */
    public static class LineColor implements LineAttr
    {
        public final Color color;

        private LineColor(Color color)
        {
            this.color = color;
        }

        /**
         * 線カラーを生成する。
         * 
         * @param color
         * @return
         */
        public static LineColor of(Color color)
        {
            return new LineColor(color);
        }
    }
}
