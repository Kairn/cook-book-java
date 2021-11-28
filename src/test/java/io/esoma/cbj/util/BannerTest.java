package io.esoma.cbj.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BannerTest {

  private static final String TEST_TITLE = "Test";

  @Test
  public void testGetTitleBanner() throws Exception {
    String testStr = "===== " + TEST_TITLE + " =====";
    assertEquals(testStr, Banner.getTitleBanner(TEST_TITLE, 5));
  }

  @Test
  public void testGetSubBanner() throws Exception {
    String testStr = "--- " + TEST_TITLE + " ---";
    assertEquals(testStr, Banner.getSubBanner(TEST_TITLE, 3));
  }

  @Test
  public void testGetSpecialBanner() throws Exception {
    String testStr = "******* " + TEST_TITLE + " *******";
    assertEquals(testStr, Banner.getSpecialBanner(TEST_TITLE, 7));
  }
}
