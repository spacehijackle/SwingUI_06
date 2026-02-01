package com.swingui.value.gap;

/**
 * ギャップ（間隔）値提供クラス
 * 
 * @author t.yoshida
 */
public class UIGap
{
    /** 間隔 */
    public final int gap;

    private UIGap(int gap)
    {
        this.gap = gap;
    }

    /**
     * 左側の間隔値提供クラス
     */
    public static class Left extends UIGap
    {
        private Left(int gap)
        {
            super(gap);
        }

        /**
         * 指定された間隔で {@code Left} を生成する。
         * 
         * @param gap 間隔
         * @return {@code Left}
         */
        public static Left of(int gap)
        {
            return new Left(gap);
        }
    }

    /**
     * 上部の間隔値提供クラス
     */
    public static class Top extends UIGap
    {
        private Top(int gap)
        {
            super(gap);
        }

        /**
         * 指定された間隔で {@code Top} を生成する。
         *
         * @param gap 間隔
         * @return {@code Top}
         */
        public static Top of(int gap)
        {
            return new Top(gap);
        }
    }

    /**
     * 右側の間隔値提供クラス
     */
    public static class Right extends UIGap
    {
        private Right(int gap)
        {
            super(gap);
        }

        /**
         * 指定された間隔で {@code Right} を生成する。
         *
         * @param gap 間隔
         * @return {@code Right}
         */
        public static Right of(int gap)
        {
            return new Right(gap);
        }
    }

    /**
     * 下部の間隔値提供クラス
     */
    public static class Bottom extends UIGap
    {
        private Bottom(int gap)
        {
            super(gap);
        }

        /**
         * 指定された間隔で {@code Bottom} を生成する。
         *
         * @param gap 間隔
         * @return {@code Bottom}
         */
        public static Bottom of(int gap)
        {
            return new Bottom(gap);
        }
    }
}
