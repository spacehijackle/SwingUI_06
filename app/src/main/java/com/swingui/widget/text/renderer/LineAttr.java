package com.swingui.widget.text.renderer;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.swingui.value.UIValue;
import com.swingui.widget.text.LabelWT;

/**
 * 線属性定義
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

    public static class Fields
    {
        /** 線スタイル */
        public UIValue<LineStyle> style;

        /** 線カラー */
        public UIValue<LineColor> color;

        private Fields(UIValue<LineStyle> style, UIValue<LineColor> color)
        {
            this.style = style;
            this.color = color;
        }

        /**
         * デフォルト属性を作成し、指定された属性で上書きして返す。
         */
        public static Fields of(LabelWT<?> label, UIValue<? extends LineAttr>... attrs)
        {
            UIValue<LineStyle> style = UIValue.of(LineStyle.Solid);
            UIValue<LineColor> color = UIValue.of(LineColor.of(label.getForeground()));
            for(UIValue<? extends LineAttr> attr : attrs)
            {
                if(attr.get() instanceof LineStyle) style = (UIValue<LineStyle>)attr;
                if(attr.get() instanceof LineColor) color = (UIValue<LineColor>)attr;
            }

            return new Fields(style, color);
        }
    }
}
