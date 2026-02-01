package com.swingui.front.text;

import com.swingui.constant.HorizontalAlignment;
import com.swingui.value.UIValue;
import com.swingui.widget.text.LabelWT;

/**
 * テキスト部品提供クラス
 * 
 * @author t.yoshida
 */
public class Text
{
    /**
     * テキスト部品を生成する。
     * 
     * @param text 表示テキスト
     * @return テキスト部品
     */
    public static LabelWT<String> of(String text)
    {
        return of(text, HorizontalAlignment.Leading);
    }

    /**
     * テキスト部品を生成する。
     * 
     * @param text 表示テキスト
     * @param alignment 表示位置
     * @return テキスト部品
     */
    public static LabelWT<String> of(String text, HorizontalAlignment alignment)
    {
        return init(new UIValue<String>(text), alignment);
    }

    /**
     * テキスト部品を生成する。
     * 
     * @param <T> 任意のクラス型（{@link Object#toString()} の返却値を表示）
     * @param state 任意型の {@link UIValue}
     * @return テキスト部品
     */
    public static <T> LabelWT<T> of(UIValue<T> state)
    {
        return init(state, HorizontalAlignment.Leading);
    }

    /**
     * テキスト部品を生成する。
     * 
     * @param <T> 任意のクラス型（{@link Object#toString()} の返却値を表示）
     * @param state 任意型の {@link UIValue}
     * @param alignment 表示位置
     * @return テキスト部品
     */
    public static <T> LabelWT<T> of(UIValue<T> state, HorizontalAlignment alignment)
    {
        return init(state, alignment);
    }

    private static <T> LabelWT<T> init(UIValue<T> state, HorizontalAlignment alignment)
    {
        LabelWT<T> label = new LabelWT<>(state);
        label.setHorizontalAlignment(alignment.value);
        return label;
    }
}