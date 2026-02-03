package com.swingui.widget.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.swing.JLabel;

import com.swingui.event.WTClickListener;
import com.swingui.value.UIValue;
import com.swingui.value.gap.UIGap;
import com.swingui.value.size.UILength;
import com.swingui.widget.Widget;
import com.swingui.widget.text.renderer.LabelRenderer;
import com.swingui.widget.text.renderer.LineAttr;
import com.swingui.widget.text.renderer.StrikethroughLabelRenderer;
import com.swingui.widget.text.renderer.UnderlineLabelRenderer;
import com.swingui.widget.text.renderer.LineAttr.LineColor;
import com.swingui.widget.text.renderer.LineAttr.LineStyle;
import com.swingui.widget.util.WidgetHelper;

/**
 * {@link JLabel} を拡張した {@link Widget} 実装クラス
 * 
 * @author t.yoshida
 */
public class LabelWT<T> extends JLabel implements Widget<LabelWT<T>>
{
    // 表示テキスト
    private UIValue<T> text;

    // 活性/非活性状態
    private UIValue<Boolean> isEnabled = new UIValue<>(true);

    // UIフォーカス状態
    private UIValue<Boolean> hasFocus = new UIValue<>(false);

    // 文字色
    private UIValue<Color> fgColor = new UIValue<>(getForeground());

    // 背景色
    private UIValue<Color> bgColor = new UIValue<>(getBackground());

    // フォント
    private UIValue<Font> font = new UIValue<>(getFont());

    // クリック・リスナー
    private WTClickListener<LabelWT<T>> onClicked;

    // 表示テキスト変更リスナー
    private BiConsumer<LabelWT<T>, T> onValueChanged;

    // レンダラー配列
    private List<LabelRenderer> renderers = new ArrayList<>();

    /**
     * 指定されたテキストでラベルを生成する。
     * 
     * @param text 表示テキスト
     */
    public LabelWT(UIValue<T> text)
    {
        super(text.get().toString());

        this.text = text;
        this.text.addValueChangeListener(() ->
        {
            WidgetHelper.invokeToRefresh(LabelWT.this);

            if(onValueChanged != null) onValueChanged.accept(LabelWT.this, text.get());
        });

        installClickListener();
        installFocusListener();
    }

    @Override
    public void dispose()
    {
        text = UIValue.of(null);
        isEnabled = UIValue.of(null);
        hasFocus = UIValue.of(null);
        fgColor = UIValue.of(null);
        bgColor = UIValue.of(null);
        font = UIValue.of(null);
        onClicked = null;
        onValueChanged = null;

        for(LabelRenderer renderer : renderers) renderer.dispose();
        renderers = new ArrayList<>();
    }

    /**
     * フォーカスの監視をする。
     */
    protected void installFocusListener()
    {
        addFocusListener(new FocusListener()
        {
            @Override public void focusGained(FocusEvent e) { }
            @Override public void focusLost(FocusEvent e) { hasFocus.set(false); }
        });
    }

    /**
     * クリック・リスナーを設定する。
     */
    private void installClickListener()
    {
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(onClicked != null) onClicked.onClicked(LabelWT.this);
            }
        });
    }

    @Override
    public void paintComponent(Graphics g)
    {
        // ラベル下にレンダリング
        renderers.forEach(renderer -> renderer.paintUnder(this, g));

        super.paintComponent(g);

        // ラベル上にレンダリング
        renderers.forEach(renderer -> renderer.paintOver(this, g));
    }

    /**
     * レンダラーを設定する。
     * 
     * @param renderer レンダラー
     * @return 自身のインスタンス
     */
    public LabelWT<T> rendering(LabelRenderer renderer)
    {
        // レンダラー追加
        this.renderers.add(renderer);

        // 再描画
        repaint();

        return this;
    }

    @Override
    public void refreshWT()
    {
        setEnabled(isEnabled.get());

        setText(text.get().toString());

        setForeground(fgColor.get());
        setBackground(bgColor.get());

        setFont(font.get());

        // フォーカス取得
        if(hasFocus.get()) requestFocusInWindow();
    }

    @Override
    public LabelWT<T> padding(UIGap... gaps)
    {
        return WidgetHelper.padding(this, gaps);
    }

    @Override
    public LabelWT<T> enabled(UIValue<Boolean> isEnabled)
    {
        this.isEnabled = isEnabled;
        this.isEnabled.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(LabelWT.this));
        setEnabled(isEnabled.get());
        return this;
    }

    @Override
    public LabelWT<T> frame(UILength... sizes)
    {
        return WidgetHelper.frame(this, sizes);
    }

    @Override
    public LabelWT<T> focus(UIValue<Boolean> hasFocus)
    {
        this.hasFocus = hasFocus;
        this.hasFocus.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(LabelWT.this));
        if(hasFocus.get()) requestFocusInWindow();
        return this;
    }

    @Override
    public LabelWT<T> background(UIValue<Color> bgColor)
    {
        if(!isOpaque()) setOpaque(true);  // 背景色を表示するために不透明化

        this.bgColor = bgColor;
        this.bgColor.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(LabelWT.this));
        setBackground(bgColor.get());
        return this;
    }

    @Override
    public LabelWT<T> self(Consumer<LabelWT<T>> self)
    {
        self.accept(this);
        return this;
    }

    /**
     * テキスト色を設定する。
     * 
     * @param fgColor テキスト色
     * @return 自身のインスタンス
     */
    public LabelWT<T> foreground(Color fgColor)
    {
        return foreground(UIValue.of(fgColor));
    }

    /**
     * テキスト色を設定する。
     * 
     * @param fgColor テキスト色
     * @return 自身のインスタンス
     */
    public LabelWT<T> foreground(UIValue<Color> fgColor)
    {
        this.fgColor = fgColor;
        this.fgColor.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(LabelWT.this));
        setForeground(fgColor.get());
        return this;
    }

    /**
     * フォントを設定する。
     * 
     * @param font フォント
     * @return 自身のインスタンス
     */
    public LabelWT<T> font(Font font)
    {
        return font(new UIValue<>(font));
    }

    /**
     * フォントを設定する。
     * 
     * @param font フォント
     * @return 自身のインスタンス
     */
    public LabelWT<T> font(UIValue<Font> font)
    {
        this.font = font;
        this.font.addValueChangeListener(() -> WidgetHelper.invokeToRefresh(LabelWT.this));
        setFont(font.get());
        return this;
    }

    /**
     * 下線を引く。
     * 
     * @return 自身のインスタンス
     */
    public LabelWT<T> underline()
    {
        return underline(UIValue.of(LineStyle.Solid));
    }

    /**
     * 下線を引く（動的変更不可）。
     * 
     * @param attrs 線属性
     * @return 自身のインスタンス
     */
    public LabelWT<T> underline(LineAttr... attrs)
    {
        return underline(WidgetHelper.convertToUIValues(attrs));
    }

    /**
     * 下線を引く（動的変更可）。
     * 
     * @param attrs 線属性
     * @return 自身のインスタンス
     */
    public LabelWT<T> underline(UIValue<? extends LineAttr>... attrs)
    {
        /*↓処理を外だし
        // デフォルト属性を作成し、指定された属性で上書き
        UIValue<LineStyle> style = UIValue.of(LineStyle.Solid);
        UIValue<LineColor> color = UIValue.of(LineColor.of(getForeground()));
        for(UIValue<? extends LineAttr> attr : attrs)
        {
            if(attr.get() instanceof LineStyle) style = (UIValue<LineStyle>)attr;
            if(attr.get() instanceof LineColor) color = (UIValue<LineColor>)attr;
        }
        return rendering(new UnderlineLabelRenderer(style, color, () -> repaint()));*/

        LineAttr.Fields fields = LineAttr.Fields.of(this, attrs);
        return rendering(new UnderlineLabelRenderer(fields.style, fields.color, () -> repaint()));
    }

    /**
     * 打ち消し線を引く。
     * 
     * @return 自身のインスタンス
     */
    public LabelWT<T> strikethrough()
    {
        return strikethrough(UIValue.of(LineStyle.Solid));
    }

    /**
     * 打ち消し線を引く（動的変更不可）。
     * 
     * @param attrs 線属性
     * @return 自身のインスタンス
     */
    public LabelWT<T> strikethrough(LineAttr... attrs)
    {
        return strikethrough(WidgetHelper.convertToUIValues(attrs));
    }

    /**
     * 打ち消し線を引く（動的変更可）。
     * 
     * @param attrs 線属性
     * @return 自身のインスタンス
     */
    public LabelWT<T> strikethrough(UIValue<? extends LineAttr>... attrs)
    {
        LineAttr.Fields fields = LineAttr.Fields.of(this, attrs);
        return rendering(new StrikethroughLabelRenderer(fields.style, fields.color, () -> repaint()));
    }

    /**
     * クリック・リスナーを設定する。
     * 
     * @param listener クリック・リスナー
     * @return 自身のインスタンス
     */
    public LabelWT<T> onClicked(WTClickListener<LabelWT<T>> listener)
    {
        this.onClicked = listener;
        return this;
    }

    /**
     * 表示テキスト変更リスナーを設定する。
     * 
     * @param onValueChanged 表示テキスト変更リスナー
     * @return 自身のインスタンス
     */
    public LabelWT<T> onValueChanged(BiConsumer<LabelWT<T>, T> onValueChanged)
    {
        this.onValueChanged = onValueChanged;
        return this;
    }
}
