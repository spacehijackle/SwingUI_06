package com.swingui.widget.text.line;

import java.awt.Color;

import com.swingui.widget.text.LabelWT;

/**
 * 線属性クラス
 * 
 * @author t.yoshida
 */
public class LineAttribute
{
    /** 線パターン */
    public final int pattern;

    /** 線カラー */
    public final Color color;

    private LineAttribute(int pattern, Color color)
    {
        this.pattern = pattern;
        this.color = color;
    }

    /**
     * ラベルからデフォルトの線属性を返す。
     * 
     * @param label {@link LabelWT}
     * @return {@code LineAttribute}
     */
    private static LineAttribute from(LabelWT<?> label)
    {
        return new LineAttribute(LinePattern.Solid.pattern, label.getForeground());
    }

    /**
     * ラベルと属性群から線属性を生成する。
     * 
     * @param label {@link LabelWT}
     * @param attrs {@code LineAttribute}配列
     * @return {@code LineAttribute}
     */
    public static LineAttribute of(LabelWT<?> label, LineAttribute... attrs)
    {
        //
        // デフォルト属性から指定された属性で上書き
        //
        LineAttribute defaults = from(label);
        Color color = defaults.color;
        int pattern = defaults.pattern;
        for(LineAttribute attr : attrs)
        {
            if(attr instanceof LineColor)   color   = ((LineColor)attr).color;
            if(attr instanceof LinePattern) pattern = ((LinePattern)attr).pattern;
        }

        return new LineAttribute(pattern, color);
    }

    /**
     * 線パターンクラス
     */
    public static class LinePattern extends LineAttribute
    {
        /** 実線 */
        public static final LinePattern Solid = new LinePattern(1);

        /** 太線 */
        public static final LinePattern Bold = new LinePattern(2);

        /** 二重線 */
        public static final LinePattern Double = new LinePattern(3);

        private LinePattern(int pattern)
        {
            super(pattern, new Color(0, 0, 0));  // 色はダミー
        }
    }

    /**
     * 線カラークラス
     */
    public static class LineColor extends LineAttribute
    {
        private LineColor(Color color)
        {
            super(-1, color);  // 線パターンはダミー
        }

        /**
         * 線カラー属性を生成する。
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
