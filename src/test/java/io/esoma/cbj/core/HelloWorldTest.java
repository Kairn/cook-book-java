package io.esoma.cbj.core;

import io.esoma.cbj.util.Banner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HelloWorldTest {

  private static final String START = "Hello World Test Start";
  private static final String END = "Hello World Test End";
  private static final String TEST_STR = "Hello world from Cook Book Java.";

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    System.out.println(Banner.getTitleBanner(START, 3));
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    System.out.println(Banner.getTitleBanner(END, 3));
    System.out.println();
  }

  @Test
  public void testRunTest() throws Exception {
    String testStr = HelloWorld.runTest();
    assertTrue("Hello world test failed, please check test strings.", testStr.equals(TEST_STR));
  }
}
