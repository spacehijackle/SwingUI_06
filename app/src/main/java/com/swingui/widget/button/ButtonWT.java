package com.swingui.widget.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.function.Consumer;

import javax.swing.Icon;
import javax.swing.JButton;

import com.swingui.event.WTClickListener;
import com.swingui.value.UIValue;
import com.swingui.value.gap.UIGap;
import com.swingui.value.size.UILength;
import com.swingui.widget.Widget;
import com.swingui.widget.util.WidgetHelper;

/**
 * {@link JButton} を拡張した {@link Widget} 実装クラス
 * 
 * @author t.yoshida
 */
public class ButtonWT extends JButton implements Widget<ButtonWT>
{
    // 活性/非活性状態
    private UIValue<Boolean> isEnabled = new UIValue<>(true);

    // UIフォーカス状態
    private UIValue<Boolean> hasFocus = new UIValue<>(false);

    // 背景色
    private UIValue<Color> bgColor = new UIValue<>(getBackground());

    // フォント
    private UIValue<Font> font = new UIValue<>(getFont());

    // クリック・リスナー
    private WTClickListener<ButtonWT> onClickListener;

    /**
     * 指定されたテキストでボタンを生成する。
     * 
     * @param text 表示テキスト
     */
    public ButtonWT(String text)
    {
        super(text);

        init();
    }

    /**
     * 指定されたアイコンでボタンを生成する。
     * 
     * @param icon 表示アイコン
     */
    public ButtonWT(Icon icon)
    {
        super(icon);

        init();
    }

    @Override
    public void dispose()
    {
        isEnabled = UIValue.of(null);
        hasFocus = UIValue.of(null);
        bgColor = UIValue.of(null);
        font = UIValue.of(null);
        onClickListener = null;
    }

    /**
     * 初期化処理
     */
    private void init()
    {
        installFocusListener();
        installClickListener();
    }

    /**
     * フォーカスの監視をする。
     */
    private void installFocusListener()
    {
        addFocusListener(new FocusListener()
        {
            @Override public void focusLost(FocusEvent e) { hasFocus.set(false); }

            @Override public void focusGained(FocusEvent e) { }
        });
    }

    /**
     * クリックイベントを拾うためにリスナーを登録する。
     */
    private void installClickListener()
    {
        addActionListener(v ->
        {
            if(onClickListener != null)
            {
                onClickListener.onClicked(ButtonWT.this);
            }
        });
    }

    @Override
    public void refreshWT()
    {
        // 活性/非活性設定
        setEnabled(isEnabled.get());

        // 背景色設定
        setBackground(bgColor.get());

        // フォント設定
        setFont(font.get());

        // フォーカス取得
        if(hasFocus.get()) requestFocusInWindow();
    }

    @Override
    public ButtonWT padding(UIGap... gaps)
    {
        return WidgetHelper.padding(this, gaps);
    }

    @Override
    public ButtonWT enabled(UIValue<Boolean> isEnabled)
    {
        this.isEnabled = isEnabled;
        this.isEnabled.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(ButtonWT.this));
        setEnabled(isEnabled.get());
        return this;
    }

    @Override
    public ButtonWT frame(UILength... sizes)
    {
        return WidgetHelper.frame(this, sizes);
    }

    @Override
    public ButtonWT focus(UIValue<Boolean> hasFocus)
    {
        this.hasFocus = hasFocus;
        this.hasFocus.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(ButtonWT.this));
        if(hasFocus.get()) requestFocusInWindow();
        return this;
    }

    @Override
    public ButtonWT background(UIValue<Color> bgColor)
    {
        this.bgColor = bgColor;
        this.bgColor.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(ButtonWT.this));
        setBackground(bgColor.get());
        return this;
    }

    @Override
    public ButtonWT self(Consumer<ButtonWT> self)
    {
        self.accept(this);
        return this;
    }

    /**
     * フォントを設定する。
     * 
     * @param font フォント
     * @return 自身のインスタンス
     */
    public ButtonWT font(Font font)
    {
        return font(UIValue.of(font));
    }

    /**
     * フォントを設定する。
     * 
     * @param font フォント
     * @return 自身のインスタンス
     */
    public ButtonWT font(UIValue<Font> font)
    {
        this.font = font;
        this.font.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(ButtonWT.this));
        setFont(font.get());
        return this;
    }

    /**
     * クリック・リスナーを設定する。
     * 
     * @param listener クリック・リスナー
     * @return 自身のインスタンス
     */
    public ButtonWT onClicked(WTClickListener<ButtonWT> listener)
    {
        this.onClickListener = listener;
        return this;
    }
}
