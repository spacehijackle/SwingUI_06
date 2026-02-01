package com.swingui.widget;

import java.awt.Color;
import java.util.function.Consumer;

import javax.swing.JComponent;

import com.swingui.value.UIValue;
import com.swingui.value.gap.AllSidesGap;
import com.swingui.value.gap.UIGap;
import com.swingui.value.gap.Symmetry;
import com.swingui.value.gap.UIGap.Bottom;
import com.swingui.value.gap.UIGap.Left;
import com.swingui.value.gap.UIGap.Right;
import com.swingui.value.gap.UIGap.Top;
import com.swingui.value.size.UILength;

/**
 * Swingの各GUI部品（ウィジット）を宣言的UI用に拡張するインターフェース定義
 * 
 * @author t.yoshida
 *
 * @param <T> GUI部品クラス
 */
public interface Widget<T extends JComponent>
{
    /**
     * パディングの設定をする。
     * 
     * @param gaps 各サイド（left, top, right, bottom）のパディング
     * @return 自身のインスタンス
     */
    T padding(UIGap... gaps);

    /**
     * 水平, 垂直方向のパディングの設定をする。
     * 
     * @param symmetries 水平, 垂直方向のパディング
     * @return 自身のインスタンス
     */
    default T padding(Symmetry... symmetries)
    {
        AllSidesGap sides = AllSidesGap.of(symmetries);
        return padding(sides.left, sides.top, sides.right, sides.bottom);
    }

    /**
     * パディングの設定をする。
     * 
     * @param padding パディング値
     * @return 自身のインスタンス
     */
    default T padding(int padding)
    {
        return padding(Left.of(padding), Top.of(padding), Right.of(padding), Bottom.of(padding));
    }

    /**
     * 活性/非活性の設定をする。
     * 
     * @param isEnabled true: 活性, false: 非活性
     * @return 自身のインスタンス
     */
    T enabled(UIValue<Boolean> isEnabled);

    /**
     * 自身のサイズの設定をする。
     * 
     * @param lengths 幅・高さサイズ
     * @return 自身のインスタンス
     */
    T frame(UILength... lengths);

    /**
     * フォーカスの設定をする。
     * 
     * @param hasFocus フォーカス・フラグ（true: フォーカスを得る）
     * @return 自身のインスタンス
     */
    T focus(UIValue<Boolean> hasFocus);

    /**
     * 背景色の設定をする。
     * 
     * @param bgColor 背景色
     * @return 自身のインスタンス
     */
    T background(UIValue<Color> bgColor);

    /**
     * 背景色の設定をする。
     * 
     * @param bgColor 背景色
     * @return 自身のインスタンス
     */
    default T background(Color bgColor)
    {
        return background(UIValue.of(bgColor));
    }

    /**
     * 引数経由で自身のインスタンスを渡す。
     * 
     * @apiNote SwingUI で未実装機能へのアクセス用
     * 
     * @param self 自身へのインスタンス参照
     * @return 自身のインスタンス
     */
    T self(Consumer<T> self);

    /**
     * 自身のコンポーネント表示を更新する。
     */
    void refreshWT();

    /**
     * 保持しているリソースを破棄する。
     * 
     * @warning このメソッド内で自身で保持している UIValue#dispose() の呼出しはしないこと。
     *          UIValue を共有している他のクラスに影響を与える可能性があるため。
     */
    void dispose();
}
