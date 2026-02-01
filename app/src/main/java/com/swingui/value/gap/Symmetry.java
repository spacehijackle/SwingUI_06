package com.swingui.value.gap;

/**
 * 水平方向、垂直方向のギャップ（間隔）値提供クラス
 * 
 * @author t.yoshida
 */
public class Symmetry
{
    /** 間隔 */
    public final int gap;

    private Symmetry(int gap)
    {
        this.gap = gap;
    }

    /**
     * 水平方向の間隔値提供クラス
     */
    public static class Horizontal extends Symmetry
    {
        private Horizontal(int gap)
        {
            super(gap);
        }

        /**
         * 指定された間隔で {@code Horizontal} を生成する。
         * 
         * @param gap 間隔
         * @return {@code Horizontal}
         */
        public static Horizontal of(int gap)
        {
            return new Horizontal(gap);
        }
    }

    /**
     * 垂直方向の間隔値提供クラス
     */
    public static class Vertical extends Symmetry
    {
        private Vertical(int gap)
        {
            super(gap);
        }

        /**
         * 指定された間隔で {@code Vertical} を生成する。
         * 
         * @param gap 間隔
         * @return {@code Vertical}
         */
        public static Vertical of(int gap)
        {
            return new Vertical(gap);
        }
    }
}
