package edu.ntnu.iir.bidata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Creates a test class for the Author class.
 */
class AuthorTest {
  /**
   * Tests if the getName method works as intended.
   */
  @Test
  void getNameTest() {
    Author author = new Author("Roger", 2);
    assertEquals("Roger", author.getName());
  }

  /**
   * Tests if the getId method works as intended.
   */
  @Test
  void getIdTest() {
    Author author = new Author("Roger", 2);
    assertEquals(2, author.getId());
  }

  /**
   * Tests if the setName method works as inteded.
   */
  @Test
  void setNameTest() {
    Author author = new Author("Roger", 2);
    author.setName("Martin");
    assertEquals("Martin", author.getName());

  }

  /**
   * A method that tests if the setId method works as intended.
   */
  @Test
  void setIdTest() {
    Author author = new Author("Roger", 2);
    author.setId(4);
    assertEquals(4, author.getId());
  }

}