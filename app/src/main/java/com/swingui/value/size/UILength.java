package com.swingui.value.size;

/**
 * UIの長さ（幅/高さ）提供クラス
 * 
 * @author t.yoshida
 */
public class UILength
{
    /** 長さ */
    public final int length;

    /**
     * 指定された長さで {@code UILength} を生成する。
     * 
     * @param length 長さ
     */
    private UILength(int length)
    {
        this.length = length;
    }

    /**
     * 幅サイズ提供クラス
     */
    public static class Width extends UILength
    {
        /** 最大限の幅 */
        public static final Width Infinite = new Width(Integer.MAX_VALUE);

        private Width(int length)
        {
            super(length);
        }

        /**
         * 指定された長さで {@code Width} を生成する。
         * 
         * @param length 長さ
         * @return {@code Width}
         */
        public static Width of(int length)
        {
            return new Width(length);
        }

        /**
         * 幅が最大限かどうかを判定する。
         * 
         * @return true: 最大限の場合
         */
        public boolean isInfinite()
        {
            return this == Infinite;
        }
    }

    /**
     * 高さサイズ提供クラス
     */
    public static class Height extends UILength
    {
        /** 最大限の高さ */
        public static final Height Infinite = new Height(Integer.MAX_VALUE);

        private Height(int length)
        {
            super(length);
        }

        /**
         * 指定された長さで {@code Height} を生成する。
         * 
         * @param length 長さ
         * @return {@code Height}
         */
        public static Height of(int length)
        {
            return new Height(length);
        }

        /**
         * 高さが最大限かどうかを判定する。
         * 
         * @return true: 最大限の場合
         */
        public boolean isInfinite()
        {
            return this == Infinite;
        }
    }
}
