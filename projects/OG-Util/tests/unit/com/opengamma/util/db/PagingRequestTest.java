/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.util.db;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.testng.annotations.Test;

/**
 * Test PagingRequest.
 */
@Test
public final class PagingRequestTest {

  public void test_ALL() {
    PagingRequest test = PagingRequest.ALL;
    assertEquals(1, test.getPage());
    assertEquals(Integer.MAX_VALUE, test.getPagingSize());
  }

  public void test_FIRST_PAGE() {
    PagingRequest test = PagingRequest.FIRST_PAGE;
    assertEquals(1, test.getPage());
    assertEquals(20, test.getPagingSize());
  }

  public void test_ONE() {
    PagingRequest test = PagingRequest.ONE;
    assertEquals(1, test.getPage());
    assertEquals(1, test.getPagingSize());
  }

  public void test_NONE() {
    PagingRequest test = PagingRequest.NONE;
    assertEquals(1, test.getPage());
    assertEquals(0, test.getPagingSize());
  }

  //-------------------------------------------------------------------------
  public void test_ofPageDefaulted() {
    assertEquals(1, PagingRequest.ofPageDefaulted(1, 10).getPage());
    assertEquals(10, PagingRequest.ofPageDefaulted(1, 10).getPagingSize());
    
    assertEquals(1, PagingRequest.ofPageDefaulted(0, 10).getPage());
    assertEquals(10, PagingRequest.ofPageDefaulted(0, 10).getPagingSize());
    
    assertEquals(2, PagingRequest.ofPageDefaulted(2, 0).getPage());
    assertEquals(20, PagingRequest.ofPageDefaulted(2, 0).getPagingSize());
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void test_ofPageDefaulted_2ints_pageNegative() {
    PagingRequest.ofPageDefaulted(-1, 40);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void test_ofPageDefaulted_2ints_pagingSizeNegative() {
    PagingRequest.ofPageDefaulted(1, -1);
  }

  //-------------------------------------------------------------------------
  public void test_ofPage() {
    assertEquals(1, PagingRequest.ofPage(1, 10).getPage());
    assertEquals(10, PagingRequest.ofPage(1, 10).getPagingSize());
    
    assertEquals(2, PagingRequest.ofPage(2, 0).getPage());
    assertEquals(0, PagingRequest.ofPage(2, 0).getPagingSize());
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void test_ofPage_2ints_page0() {
    PagingRequest.ofPage(0, 1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void test_ofPage_2ints_pageNegative() {
    PagingRequest.ofPage(-1, 40);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void test_ofPage_2ints_pagingSizeNegative() {
    PagingRequest.ofPage(1, -1);
  }

  //-------------------------------------------------------------------------
  public void test_getItems_page1() {
    PagingRequest test = PagingRequest.ofPage(1, 20);
    assertEquals(1, test.getFirstItem());
    assertEquals(0, test.getFirstItemIndex());
    assertEquals(20, test.getLastItem());
    assertEquals(20, test.getLastItemIndex());
  }

  public void test_getItems_page2() {
    PagingRequest test = PagingRequest.ofPage(2, 20);
    assertEquals(21, test.getFirstItem());
    assertEquals(20, test.getFirstItemIndex());
    assertEquals(40, test.getLastItem());
    assertEquals(40, test.getLastItemIndex());
  }

  //-------------------------------------------------------------------------
  public void test_select_firstPage() {
    PagingRequest test = PagingRequest.ofPage(1, 2);
    Collection<String> coll = Arrays.asList("Hello", "World", "Test");
    List<String> result = test.select(coll);
    assertEquals(Arrays.asList("Hello", "World"), result);
  }

  public void test_select_lastPage() {
    PagingRequest test = PagingRequest.ofPage(2, 2);
    Collection<String> coll = Arrays.asList("Hello", "World", "Test");
    List<String> result = test.select(coll);
    assertEquals(Arrays.asList("Test"), result);
  }

  public void test_select_all() {
    PagingRequest test = PagingRequest.ofPage(1, 20);
    Collection<String> coll = Arrays.asList("Hello", "World", "Test");
    List<String> result = test.select(coll);
    assertEquals(coll, result);
  }

  //-------------------------------------------------------------------------
  public void test_equals_equal() {
    PagingRequest test1 = PagingRequest.ofPage(1, 20);
    PagingRequest test2 = PagingRequest.ofPage(1, 20);
    assertEquals(true, test1.equals(test1));
    assertEquals(true, test1.equals(test2));
    assertEquals(true, test2.equals(test1));
    assertEquals(true, test2.equals(test2));
  }

  public void test_equals_notEqualPage() {
    PagingRequest test1 = PagingRequest.ofPage(1, 20);
    PagingRequest test2 = PagingRequest.ofPage(2, 20);
    assertEquals(false, test1.equals(test2));
    assertEquals(false, test2.equals(test1));
  }

  public void test_equals_notEqualPagingSize() {
    PagingRequest test1 = PagingRequest.ofPage(1, 20);
    PagingRequest test2 = PagingRequest.ofPage(1, 30);
    assertEquals(false, test1.equals(test2));
    assertEquals(false, test2.equals(test1));
  }

  public void test_equals_other() {
    PagingRequest test = PagingRequest.ofPage(1, 20);
    assertEquals(false, test.equals(""));
    assertEquals(false, test.equals(null));
  }

  //-------------------------------------------------------------------------
  public void test_hashCode_equal() {
    PagingRequest test1 = PagingRequest.ofPage(2, 40);
    PagingRequest test2 = PagingRequest.ofPage(2, 40);
    assertEquals(test1.hashCode(), test2.hashCode());
  }

  //-------------------------------------------------------------------------
  public void test_toString() {
    PagingRequest test = PagingRequest.ofPage(2, 40);
    assertEquals("PagingRequest[page=2, pagingSize=40]", test.toString());
  }

}
