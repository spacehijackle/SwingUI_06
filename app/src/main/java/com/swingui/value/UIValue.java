package com.swingui.value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UIが保持する値クラス
 * 
 * @author t.yoshida
 */
public class UIValue<T>
{
    /**
     * 値変更通知リスナーのインターフェース定義
     */
    @FunctionalInterface
    public interface ValueChangeListener
    {
        /**
         * 保持する値の変更通知
         */
        void onValueChanged();
    }

    // 保持する値
    private T value;

    // 変更通知リスナーリスト
    private List<ValueChangeListener> listeners = new ArrayList<>();

    /**
     * 保持する値を指定して {@code UIValue} を生成する。
     * 
     * @param value 保持する値
     */
    public UIValue(T value)
    {
        this.value = value;
    }

    /**
     * {@code UIValue} を生成する（短縮記述用）。
     * 
     * <pre>
     * 例:
     *   VStack.of
     *   (
     *       Text.of("Hello, World!")
     *   )
     *   .background(UIValue.of(Color.RED)); ← .background(new UIValue<Color>(Color.RED));
     * </pre>
     * 
     * @param <T> 任意の値型
     * @param value 保持する値
     * @return {@code UIValue} インスタンス
     */
    public static <T> UIValue<T> of(T value)
    {
        return new UIValue<>(value);
    }

    /**
     * 値変更通知リスナーを追加する。
     * 
     * @param listener 値変更通知リスナー
     */
    public void addValueChangeListener(ValueChangeListener listener)
    {
        this.listeners.add(listener);
    }

    /**
     * 保持する値を返す。
     * 
     * @return 保持する値
     */
    public T get()
    {
        return this.value;
    }

    /**
     * 保持する値を返す。値が null の場合はデフォルト値を返す。
     * 
     * @param defaultValue デフォルト値
     * @return 保持する値、またはデフォルト値
     */
    public T getOrDefault(T defaultValue)
    {
        return (value != null) ? value : defaultValue;
    }

    /**
     * 値を設定する。
     * 
     * @param value 値
     */
    public void set(T value)
    {
        // 値の変更がないかチェック
        boolean isNotEquals = !(Objects.equals(this.value, value));
        if(isNotEquals)
        {
            this.value = value;

            // 値の変更を通知
            fireOnValueChanged();
        }
    }

    /**
     * 値が null か否かを返す。
     * 
     * @return true: 値が空の場合
     */
    public boolean isNull()
    {
        return (value == null);
    }

    /**
     * 値が null でないか否かを返す。
     * 
     * @return true: 値が null でない場合
     */
    public boolean isNotNull()
    {
        return !isNull();
    }

    /**
     * 値に null を設定する。
     */
    public void setToNull()
    {
        set(null);
    }

    /**
     * 保持するリスナーに値変更を通知する。
     */
    private void fireOnValueChanged()
    {
        for(ValueChangeListener listener : listeners)
        {
            listener.onValueChanged();
        }
    }

    /**
     * 保持しているリソースを破棄する。
     */
    public void dispose()
    {
        listeners.clear();
        setToNull();
    }

    @Override
    public int hashCode()
    {
        return (value == null) ? 0 : value.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        UIValue<?> other = (UIValue<?>)obj;
        return Objects.equals(value, other.value);
    }

    @Override
    public String toString()
    {
        return (value == null ? "null" : value.toString());
    }
}
