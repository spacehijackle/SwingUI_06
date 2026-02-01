package com.swingui.front.button;

import javax.swing.Icon;

import com.swingui.widget.button.ButtonWT;

/**
 * ボタン部品提供クラス
 * 
 * @author t.yoshida
 */
public class Button
{
    /**
     * 指定されたテキストでボタンを生成する。
     * 
     * @param text ボタンに表示するテキスト
     * @return 生成された {@code ButtonWT} インスタンス
     */
    public static ButtonWT of(String text)
    {
        ButtonWT button = new ButtonWT(text);
        return button;
    }

    /**
     * 指定されたアイコンでボタンを生成する。
     * 
     * @param icon ボタンに表示するアイコン
     * @return 生成された {@code ButtonWT} インスタンス
     */
    public static ButtonWT of(Icon icon)
    {
        ButtonWT button = new ButtonWT(icon);
        return button;
    }
}