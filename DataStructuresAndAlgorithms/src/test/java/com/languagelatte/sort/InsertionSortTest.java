package com.languagelatte.sort;

import org.junit.jupiter.api.Test;

public class InsertionSortTest {

  @Test
  public void TestSort() {
    Sort<String> sorter = new InsertionSort<String>();
    var array = new String[] {"Z", "B", "A"};
    sorter.sort(array);

    var x = 10;
  }
}
