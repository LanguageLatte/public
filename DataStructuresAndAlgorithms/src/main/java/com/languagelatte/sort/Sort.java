package com.languagelatte.sort;

import com.languagelatte.side_effect.annotations.SideEffect;

public interface Sort<T extends Comparable<T>> {

  @SideEffect
  public void sort(T[] array);

  public T[] immutableSort(T[] array);
}
