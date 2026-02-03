package swingui_06.decoration;

import java.awt.Color;

import com.swingui.constant.HorizontalAlignment;
import com.swingui.front.Frame;
import com.swingui.front.layout.Spacer;
import com.swingui.front.layout.VStack;
import com.swingui.front.text.Text;
import com.swingui.value.gap.Symmetry.Horizontal;
import com.swingui.value.size.UILength.Height;
import com.swingui.value.size.UILength.Width;
import com.swingui.widget.text.renderer.LineAttr.LineColor;
import com.swingui.widget.text.renderer.LineAttr.LineStyle;

/**
 * 静的にテキスト装飾するサンプル
 * 
 * @author t.yoshida
 */
public class StaticTextDecoration
{
    public void test()
    {
        Frame.of
        (
            "ラベル修飾テスト",

            (f) -> f.setResizable(true),  // 画面リサイズ可能

            VStack.of
            (
                Spacer.fill(),

                Text.of("Underline Label Test")
                    .frame(Width.Infinite, Height.of(80))
                    .padding(8)
                    .background(Color.white)
                    .underline(),

                Text.of("Underline Label Test", HorizontalAlignment.Center)
                    .frame(Width.Infinite, Height.of(80))
                    .padding(8)
                    .background(Color.white)
                    .underline(LineStyle.Bold),

                Text.of("Underline Label Test", HorizontalAlignment.Trailing)
                    .frame(Width.Infinite, Height.of(80))
                    .padding(8)
                    .background(Color.white)
                    .underline(LineStyle.Double, LineColor.of(Color.red)),

                Text.of("Strikethrough & Underline Label Test", HorizontalAlignment.Center)
                    .frame(Width.Infinite, Height.of(80))
                    .padding(8)
                    .background(Color.white)
                    .strikethrough(LineStyle.Double, LineColor.of(Color.red))
                    .underline(LineColor.of(Color.blue), LineStyle.Bold),

                Spacer.fill()
            )
            .padding(Horizontal.of(24))
        );
    }
}
