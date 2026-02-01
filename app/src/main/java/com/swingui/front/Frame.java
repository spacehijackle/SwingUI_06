package com.swingui.front;

import java.awt.BorderLayout;
import javax.swing.JComponent;

import com.swingui.widget.Framer;

/**
 * フレーム提供クラス
 * 
 * @author t.yoshida
 */
public class Frame
{
    /**
     * 指定されたタイトルと子コンポーネントでフレームを生成する。
     * 
     * @param title フレームのタイトル
     * @param child フレームに表示する子コンポーネント
     * @return 生成された {@code Framer} インスタンス
     */
    public static Framer of(String title, JComponent child)
    {
        return of(title, null, child);
    }

    /**
     * 指定されたタイトルと追加オプションでフレームを生成する。
     * 
     * @param title フレームのタイトル
     * @param extraOption フレームに適用する追加オプション
     * @return 生成された {@code Framer} インスタンス
     */
    public static Framer of(String title, Framer.Option extraOption)
    {
        return of(title, extraOption, null);
    }

    /**
     * 指定されたタイトル、追加オプション、子コンポーネントでフレームを生成する。
     * 
     * @param title フレームのタイトル
     * @param extraOption フレームに適用する追加オプション
     * @param child フレームに表示する子コンポーネント
     * @return 生成された {@code Framer} インスタンス
     */
    public static Framer of(String title, Framer.Option extraOption, JComponent child)
    {
        Framer frame = new Framer(title);
        if(child != null)
        {
            // 子コンポーネントをフレーム中心に配置
            frame.getContentPane().add(child, BorderLayout.CENTER);
        }

        //
        // フレームへの初期化オプション設定
        //
        new Framer.BasicOption().setUp(frame);  // 基本オプションの実施
        if(extraOption != null)
        {
            // フレームへの追加オプション設定
            extraOption.setUp(frame);
        }

        // フレームの表示
        frame.setVisible(true);

        return frame;
    }
}
