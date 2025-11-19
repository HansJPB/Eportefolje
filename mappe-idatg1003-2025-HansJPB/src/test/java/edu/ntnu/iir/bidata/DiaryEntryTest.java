package edu.ntnu.iir.bidata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Creates a test class for the DiaryEntry class.
 */
class DiaryEntryTest {

  /**
   * Tests if the getTitle method gives expected results.
   */
  @Test
  void getTitleTest() {
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    assertEquals("Fish", diaryEntry.getTitle());
  }

  /**
   * Tests if the getText method gives expected results.
   */
  @Test
  void getTextTest() {
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    assertEquals("2 kilograms of fish", diaryEntry.getText());
  }

  /**
   * Tests if the setTitle method gives expected results.
   */
  @Test
  void setTitleTest() {
    DiaryEntry diaryEntry = new DiaryEntry("", "");
    diaryEntry.setTitle("Fish");
    Assertions.assertEquals("Fish", diaryEntry.getTitle());
  }

  /**
   * Tests if the setText method gives expected results.
   */
  @Test
  void setTextTest() {
    DiaryEntry diaryEntry = new DiaryEntry("", "");
    diaryEntry.setText("Fish");
    Assertions.assertEquals("Fish", diaryEntry.getText());
  }

  /**
   * Testing if the constructor works as intended.
   */
  @Test
  void testConstructorFields() {
    DiaryEntry entry = new DiaryEntry("", "");
    entry.setTitle("Fish");
    entry.setText("Cod and salmon");
    LocalDateTime date = entry.getDate();
    assertEquals("Fish", entry.getTitle());
    assertEquals("Cod and salmon", entry.getText());
    assertEquals(date, entry.getDate());
  }

  /**
   * Negative test: Testing setTitle without a title (title as "").
   */
  @Test
  void setTitleAsEmptyTest() {
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    assertThrows(IllegalArgumentException.class, () -> diaryEntry.setTitle(""));
    // Testing that the title has not been changed.
    assertEquals("Fish", diaryEntry.getTitle());
  }

  /**
   * Negative test: Testing setText without text (text as "").
   */
  @Test
  void setTextAsEmptyTest() {
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    assertThrows(IllegalArgumentException.class, () -> diaryEntry.setText(""));
    // Testing that the text has not been changed.
    assertEquals("2 kilograms of fish", diaryEntry.getText());
  }
}
