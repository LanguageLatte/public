package com.languagelatte.sort;

import com.languagelatte.side_effect.annotations.SideEffect;

public class InsertionSort<T extends Comparable<T>> implements Sort<T> {

  @SuppressWarnings("unchecked")
  @Override
  public T[] immutableSort(T[] array) {
    T[] copy = (T[]) new Comparable[array.length];

    for (int x = 0; x < copy.length; x++) {
      for (int y = x; y > 0; y--) {
        if (less(copy[y], copy[y - 1])) {
          T swap = copy[y];
          copy[y] = copy[y - 1];
          copy[y - 1] = swap;
        } else {
          break;
        }
      }
    }

    return copy;
  }

  @Override
  @SideEffect
  public void sort(T[] array) {
    for (int x = 0; x < array.length; x++) {
      for (int y = x; y > 0; y--) {
        if (less(array[y], array[y - 1])) {
          swap(array, y, y - 1);
        } else {
          break;
        }
      }
    }
  }

  private boolean less(T a, T b) {
    return a.compareTo(b) < 0;
  }

  @SideEffect
  private void swap(T[] array, int a, int b) {
    T swap = array[a];
    array[a] = array[b];
    array[b] = swap;
  }
}
