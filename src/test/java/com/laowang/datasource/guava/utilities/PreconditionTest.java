package com.laowang.datasource.guava.utilities;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Objects;
import org.junit.Test;

public class PreconditionTest {

  @Test(expected = NullPointerException.class)
  public void testCheckNotNull() {
    checkNotNull(null);
  }

  @Test
  public void testCheckNotNullWithMessage() {
    try {
      checkNotNullWithMsg(null);
    } catch (NullPointerException e) {
      assertThat(e, is(NullPointerException.class));
      assertThat(e.getMessage(), equalTo("The list should not be null"));
    }
  }

  @Test
  public void testCheckNotNullWithFormatMessage() {
    try {
      checkNotNullWithFormatMessage(null);
    } catch (NullPointerException e) {
      assertThat(e, is(NullPointerException.class));
      assertThat(e.getMessage(), equalTo("The list should not be null and the size must be 2"));
    }
  }

  @Test
  public void testCheckArguments() {
    final String type = "A";
    try {
      Preconditions.checkArgument(type.equals("B"));
    } catch (IllegalArgumentException e) {
      assertThat(e, is(IllegalArgumentException.class));
    }
  }

  @Test
  public void testCheckState() {
    try {
      final String state = "A";
      Preconditions.checkState(state.equals("B"), "The state is illegal.");
      fail("should not process to here.");
    } catch (IllegalStateException e) {
      assertThat(e, is(IllegalStateException.class));
    }
  }

  @Test
  public void testCheckIndex() {
    try {
      List<String> list = ImmutableList.of();
      Preconditions.checkElementIndex(10, list.size());
    } catch (IndexOutOfBoundsException e) {
      assertThat(e, is(IndexOutOfBoundsException.class));
    }
  }

  @Test(expected = NullPointerException.class)
  public void testByObjects() {
    Objects.requireNonNull(null);
  }

  @Test(expected = AssertionError.class)
  public void testNull() {
    List<String> list = null;
    assert list != null;
  }

  @Test
  public void testNullWithMsg() {
    List<String> list = null;
    try {
      assert list != null:"The list can not be null.";
    } catch (AssertionError e) {
      assertThat(e, is(AssertionError.class));
      assertThat(e.getMessage(), equalTo("The list can not be null."));
    }
  }

  private void checkNotNull(final List<String> list) {
    Preconditions.checkNotNull(list);
  }

  private void checkNotNullWithMsg(final List<String> list) {
    Preconditions.checkNotNull(list, "The list should not be null.");
  }

  private void checkNotNullWithFormatMessage(final List<String> list) {
    Preconditions.checkNotNull(list, "The list should not be null and the size must be %s", 2);
  }
}
