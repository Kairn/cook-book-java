package io.esoma.cbj.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BannerTest {

  private static final String TEST_TITLE = "Test";

  @Test
  public void testGetTitleBanner() throws Exception {
    String testStr = "===== " + TEST_TITLE + " =====";
    assertTrue(testStr.equals(Banner.getTitleBanner(TEST_TITLE, 5)));
  }

  @Test
  public void testGetSubBanner() throws Exception {
    String testStr = "--- " + TEST_TITLE + " ---";
    assertTrue(testStr.equals(Banner.getSubBanner(TEST_TITLE, 3)));
  }

  @Test
  public void testGetSpecialBanner() throws Exception {
    String testStr = "******* " + TEST_TITLE + " *******";
    assertTrue(testStr.equals(Banner.getSpecialBanner(TEST_TITLE, 7)));
  }
}
