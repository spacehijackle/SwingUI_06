package swingui_06.decoration;

import java.awt.Color;
import java.awt.Font;

import com.swingui.constant.HorizontalAlignment;
import com.swingui.front.Frame;
import com.swingui.front.button.Button;
import com.swingui.front.layout.HStack;
import com.swingui.front.layout.Spacer;
import com.swingui.front.layout.VStack;
import com.swingui.front.text.Text;
import com.swingui.value.UIValue;
import com.swingui.value.gap.Symmetry.Horizontal;
import com.swingui.value.size.UILength.Height;
import com.swingui.value.size.UILength.Width;
import com.swingui.widget.text.line.LineStyle;

/**
 * 動的にテキスト装飾するサンプル
 * 
 * @author t.yoshida
 */
public class DynamicTextDecoration
{
    // 線スタイル
    private UIValue<LineStyle> lineStyle = UIValue.of(LineStyle.Solid);

    // 線カラー
    private UIValue<Color> lineColor = UIValue.of(Color.BLACK);

    public void test()
    {
        Frame.of
        (
            "ラベル修飾テスト",

            VStack.of
            (
                Spacer.fill(),

                Text.of("Underline Label Test", HorizontalAlignment.Center)
                    .font(new Font("Dialog", Font.PLAIN, 18))
                    .frame(Width.Infinite, Height.of(80))
                    .padding(8)
                    .background(Color.white)
                    //.underline(lineStyle),  // 線スタイルのみ動的変更
                    //.underline(lineColor),  // 線カラーのみ動的変更
                    .underline(lineStyle, lineColor),  // 線スタイル・線カラー両方を動的変更

                Spacer.of(Height.of(8)),

                //
                // 線スタイル設定ボタン
                // 
                Text.of("Line Style:")
                    .foreground(Color.darkGray)
                    .frame(Width.Infinite),

                HStack.of
                (
                    Button.of("Solid")
                        .frame(Width.of(80))
                        .onClicked(self -> lineStyle.set(LineStyle.Solid)),

                    Button.of("Bold")
                        .frame(Width.of(80))
                        .onClicked(self -> lineStyle.set(LineStyle.Bold)),

                    Button.of("Double")
                        .frame(Width.of(80))
                        .onClicked(self -> lineStyle.set(LineStyle.Double))
                ),

                Spacer.of(Height.of(8)),

                //
                // 線カラー設定ボタン
                // 
                Text.of("Line Color:")
                    .foreground(Color.darkGray)
                    .frame(Width.Infinite),

                HStack.of
                (
                    Button.of("Black")
                        .frame(Width.of(80))
                        .onClicked(self -> lineColor.set(Color.black)),

                    Button.of("Red")
                        .frame(Width.of(80))
                        .onClicked(self -> lineColor.set(Color.red)),

                    Button.of("Blue")
                        .frame(Width.of(80))
                        .onClicked(self -> lineColor.set(Color.blue))
                ),

                Spacer.fill()
            )
            .padding(Horizontal.of(24))
        );
    }
}
