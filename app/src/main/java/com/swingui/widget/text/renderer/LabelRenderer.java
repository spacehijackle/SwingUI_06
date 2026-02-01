package com.swingui.widget.text.renderer;

import java.awt.Graphics;

import com.swingui.widget.text.LabelWT;

/**
 * ラベル描画用レンダラー定義
 * 
 * @author t.yoshida
 */
public interface LabelRenderer
{
    /**
     * ラベル下の描画処理
     * 
     * @param target {@link LabelWT}
     * @param g {@link Graphics}
     */
    void paintUnder(LabelWT<?> target, Graphics g);

    /**
     * ラベル上の描画処理
     * 
     * @param target {@link LabelWT}
     * @param g {@link Graphics}
     */
    void paintOver(LabelWT<?> target, Graphics g);

    /**
     * リソース解放処理
     */
    void dispose();
}
