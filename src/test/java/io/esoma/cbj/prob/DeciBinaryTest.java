package io.esoma.cbj.prob;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.tinylog.Logger;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class DeciBinaryTest {

  @BeforeClass
  public static void setUpBeforeClass() {
    DeciBinary.initCache();
  }

  @Test
  @Parameters({
    "0, 0",
    "1, 1",
    "10, 2",
    "2016, 24",
    "2008, 24",
    "75, 19",
    "108, 12",
    "7422485, 661"
  })
  public void testDeciValue(int dbNum, int value) {
    int actual = DeciBinary.deciValue(dbNum);
    Logger.debug(actual);
    assertEquals(value, actual);
  }

  @Test
  @Parameters({
    "7, 4",
    "10, 100",
    "3, 2",
    "6, 11",
    "26, 111",
    "19, 102",
    "30, 32",
    "1104, 11111",
    "1963, 10406",
    "20, 110",
    "1, 0"
  })
  public void testDeciFromDb(long db, long value) {
    long actual = DeciBinary.deciFromDb(db);
    Logger.debug(actual);
    assertEquals(value, actual);
  }
}
