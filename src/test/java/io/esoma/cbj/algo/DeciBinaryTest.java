package io.esoma.cbj.algo;

import io.esoma.cbj.util.Banner;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class DeciBinaryTest {

  private static final String START = "Decibinary Test Start";
  private static final String END = "Decibinary Test End";

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    DeciBinary.initCache();
    System.out.println(Banner.getTitleBanner(START, 3));
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    System.out.println(Banner.getTitleBanner(END, 3));
    System.out.println();
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
    System.out.println(actual);
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
    System.out.println(actual);
    assertEquals(value, actual);
  }
}
