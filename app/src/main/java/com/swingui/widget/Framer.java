package com.swingui.widget;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * SwingUI用フレーム
 * 
 * @author t.yoshida
 */
public class Framer extends JFrame
{
    /**
     * フレーム生成時のオプション設定インターフェース定義
     */
    @FunctionalInterface
    public interface Option
    {
        /**
         * フレーム生成時、フレームに対する各種設定を行う。
         * 
         * @param frame {@code Framer}
         */
        void setUp(Framer frame);
    }

    /**
     * タイトルを指定して {@code Framer} を生成する。
     * 
     * @param title フレーム・タイトル
     */
    public Framer(String title)
    {
        super(title);
    }

    /**
     * 下位コンポーネントの更新処理を行う。
     */
    public void refreshWT()
    {
        for(Component c : getContentPane().getComponents())
        {
            //↓下位コンポーネントの更新をUIスレッド上で実行
            //refresh(c);
            SwingUtilities.invokeLater(() -> refresh(c));
        }
    }

    /**
     * 下位コンポーネントの更新処理を再帰的に行う。
     * 
     * @param c コンポーネント
     */
    private void refresh(Component c)
    {
        if(c instanceof JComponent)
        {
            for(Component child : ((JComponent)c).getComponents())
            {
                if(child instanceof Widget<?>)
                {
                    ((Widget<?>)child).refreshWT();
                }

                refresh(child);
            }
        }
    }

    /**
     * 基本的なオプション設定クラス
     */
    public static class BasicOption implements Option
    {
        /**
         * 基本的なオプション設定を行う。
         */
        public void setUp(Framer frame)
        {
            frame.pack();
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationByPlatform(true);
        }
    }
}
