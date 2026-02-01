package com.swingui.widget.text.renderer;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import javax.swing.Icon;

import java.awt.BasicStroke;
import java.awt.Color;

import com.swingui.value.UIValue;
import com.swingui.widget.text.LabelWT;
import com.swingui.widget.text.line.LineStyle;

/**
 * ラベルの下線レンダラー
 * 
 * @author t.yoshida
 */
public class UnderlineLabelRenderer implements LabelRenderer
{
    private UIValue<LineStyle> style;

    private UIValue<Color> color;

    public UnderlineLabelRenderer()
    {
        this(UIValue.of(LineStyle.Solid), UIValue.of(Color.BLACK), () -> { });
    }
    public UnderlineLabelRenderer(UIValue<LineStyle> style, UIValue<Color> color, Runnable repaint)
    {
        this.style = style;
        this.color = color;
        this.style.addValueChangeListener(() -> repaint.run());
        this.color.addValueChangeListener(() -> repaint.run());
    }

    @Override
    public void paintUnder(LabelWT<?> target, Graphics g) { }

    @Override
    public void paintOver(LabelWT<?> target, Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g.create();

        // アンチエイリアス
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //
        // SwingUtilitiesを使って、テキストとアイコンのレイアウト情報を取得
        // ※3点リーダ(...)でクリップされた後のテキスト描画領域が正確にわかる
        //
        Icon icon = target.getIcon();
        FontMetrics fm = target.getFontMetrics(target.getFont());

        Insets insets = target.getInsets();
        Rectangle viewRect = new Rectangle
        (
            insets.left,
            insets.top,
            target.getWidth()  - (insets.left + insets.right),
            target.getHeight() - (insets.top + insets.bottom)
        );
        Rectangle iconRect = new Rectangle();
        Rectangle textRect = new Rectangle();

        String text = target.getText();
        String clippedText = javax.swing.SwingUtilities.layoutCompoundLabel
        (
            target,
            fm,
            text,
            icon,
            target.getVerticalAlignment(),
            target.getHorizontalAlignment(),
            target.getVerticalTextPosition(),
            target.getHorizontalTextPosition(),
            viewRect,
            iconRect,
            textRect,
            target.getIconTextGap()
        );

        // テキストが表示されない場合は何もしない
        if(clippedText == null || clippedText.isEmpty())
        {
            g2.dispose();
            return;
        }

        // テキストのベースラインを基準に下線のY座標を計算
        int baselineY = textRect.y + fm.getAscent();
        int lineY = baselineY + 1;

        // 下線を描画 (描画位置と幅は layoutCompoundLabel の結果(textRect)を使用)
        ////g2.setColor(target.getForeground());
        ////g2.draw(new Line2D.Double(textRect.x, lineY, textRect.x + textRect.width, lineY));
        g2.setColor(color.get());
        drawLine(g2, textRect.x, lineY, textRect.x + textRect.width, lineY);
        g2.dispose();
    }

    private void drawLine(Graphics2D g2, int x1, int y1, int x2, int y2)
    {
        switch(style.get())
        {
            case Solid:
            {
                g2.setStroke(new BasicStroke(1.0f));
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
                break;
            }
            case Bold:
            {
                g2.setStroke(new BasicStroke(2.0f));
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
                break;
            }
            case Double:
            {
                g2.setStroke(new BasicStroke(1.0f));
                g2.draw(new Line2D.Double(x1, y1 - 1, x2, y2 - 1));
                g2.draw(new Line2D.Double(x1, y1 + 1, x2, y2 + 1));
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void dispose()
    {
        style = UIValue.of(null);
        color = UIValue.of(null);
    }
}
