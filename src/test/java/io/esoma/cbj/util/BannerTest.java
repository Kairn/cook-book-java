package io.esoma.cbj.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BannerTest {

  private static final String TEST_TITLE = "Test";

  @Test
  void testGetTitleBanner() {
    String testStr = "===== " + TEST_TITLE + " =====";
    assertEquals(testStr, Banner.getTitleBanner(TEST_TITLE, 5));
  }

  @Test
  void testGetSubBanner() {
    String testStr = "--- " + TEST_TITLE + " ---";
    assertEquals(testStr, Banner.getSubBanner(TEST_TITLE, 3));
  }

  @Test
  void testGetSpecialBanner() {
    String testStr = "******* " + TEST_TITLE + " *******";
    assertEquals(testStr, Banner.getSpecialBanner(TEST_TITLE, 7));
  }
}
