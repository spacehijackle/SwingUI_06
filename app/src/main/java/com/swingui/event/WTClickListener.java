package com.swingui.event;

import javax.swing.JComponent;

import com.swingui.widget.Widget;

/**
 * ウィジットのクリック・リスナー定義
 * 
 * @author t.yoshida
 */
@FunctionalInterface
public interface WTClickListener<WT extends Widget<? extends JComponent>>
{
    /**
     * マウスのクリック・イベント通知
     * 
     * @param source クリック対象ウィジット
     */
    void onClicked(WT source);
}
