package edu.ntnu.iir.bidata;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiaryRegisterTest {


  /**
   * Testing if the addEntry method works as intended.
   *
   */
  @Test
  void addEntryTest() {
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    DiaryRegister diaryRegister = new DiaryRegister();
    diaryRegister.addEntry(diaryEntry);
    assertEquals(1, diaryRegister.listAllEntriesByTime().size());
  }


  /**
   * Testing if the deleteEntry method works as intended.
   */
  @Test
  void deleteEntryTest() {
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    DiaryEntry diaryEntry1 = new DiaryEntry("Salmon", "One big salmon");
    DiaryRegister diaryRegister = new DiaryRegister();
    diaryRegister.addEntry(diaryEntry);
    // Requires two objects, due to an empty list causing an exception.
    diaryRegister.addEntry(diaryEntry1);
    // Requires that the addEntry method works as intended.
    diaryRegister.deleteEntry(diaryEntry);
    assertEquals(1, diaryRegister.listAllEntriesByTime().size());

  }


  /**
   * Testing if the searchForEntry method works as intended.
   */
  @Test
  void searchForEntryWithTimeTest() {
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    DiaryRegister diaryRegister = new DiaryRegister();
    diaryRegister.addEntry(diaryEntry);
    LocalDateTime date = diaryEntry.getDate();
    List<DiaryEntry> results = diaryRegister.searchForEntryWithTime(date);
    assertTrue(results.contains(diaryEntry));


  }


  /**
   * Testing if the listAlEntriesByTime method works as intended.
   */
  @Test
  void listAllEntriesByTimeTest() {
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    DiaryEntry diaryEntry1 = new DiaryEntry("Salmon", "One big salmon");
    DiaryRegister diaryRegister = new DiaryRegister();
    diaryRegister.addEntry(diaryEntry);
    diaryRegister.addEntry(diaryEntry1);
    List<DiaryEntry> results = diaryRegister.listAllEntriesByTime();
    assertTrue(results.contains(diaryEntry));


  }

  /**
   * Tests if the listAllEntriesByTimeTitle works as intended.
   */
  @Test
  void listAllEntriesByTimeTitleTest() {
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    DiaryEntry diaryEntry1 = new DiaryEntry("Salmon", "One big salmon");
    DiaryRegister diaryRegister = new DiaryRegister();
    diaryRegister.addEntry(diaryEntry);
    diaryRegister.addEntry(diaryEntry1);
    List<String> results = diaryRegister.listAllEntriesByTimeTitle();
    assertTrue(results.contains(diaryEntry.getTitle()));
    assertTrue(results.contains(diaryEntry1.getTitle()));
  }


  /**
   * Testing is the clearAllEntries method works as intended.
   */
  @Test
  void clearAllEntriesTest() {
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    DiaryEntry diaryEntry1 = new DiaryEntry("Salmon", "One big salmon");
    DiaryEntry diaryEntry2 = new DiaryEntry("Cod", "A tiny cod");
    DiaryRegister diaryRegister = new DiaryRegister();
    diaryRegister.addEntry(diaryEntry);
    diaryRegister.addEntry(diaryEntry1);
    diaryRegister.clearAllEntries();
    // Requires another entry to not have an empty list.
    diaryRegister.addEntry(diaryEntry2);
    assertEquals(1, diaryRegister.listAllEntriesByTime().size());


  }

  /**
   * Negative test: Testing adding the same entry
   */
  @Test
  void addEntryUsingExistingEntryTest() {
    DiaryRegister diaryRegister = new DiaryRegister();
    DiaryEntry diaryEntry = new DiaryEntry("Fish", "2 kilograms of fish");
    DiaryEntry diaryEntry1 = new DiaryEntry("Fish", "2 kilograms of fish");
    diaryRegister.addEntry(diaryEntry);
    assertThrows(IllegalArgumentException.class, () -> diaryRegister.addEntry(diaryEntry1));
    assertFalse(diaryRegister.listAllEntriesByTime().isEmpty());


  }

  /**
   * Negative test: Testing the deleteEntry method if the register is empty.
   */
  @Test
  void deleteEntryIfRegisterIsEmptyTest() {
    DiaryRegister diaryRegister = new DiaryRegister();
    DiaryEntry entry = new DiaryEntry("Fish", "2 kilograms of fish");
    assertThrows(IllegalArgumentException.class, () -> diaryRegister.deleteEntry(entry));

  }

  /**
   * Negative test: Testing the deleteEntry method.
   * If it attempts to delete an entry which is not in the register.
   */
  @Test
  void deleteEntryWithNonExistentEntryTest() {
    DiaryRegister diaryRegister = new DiaryRegister();
    DiaryEntry entry = new DiaryEntry("Fish", "2 kilograms of fish");
    DiaryEntry entry1 = new DiaryEntry("Big salmon", "I went night fishing");
    diaryRegister.addEntry(entry);
    assertThrows(IllegalArgumentException.class, () -> diaryRegister.deleteEntry(entry1));

  }

  /**
   * Negative test: Testing the searchForEntryWithTime if result is null.
   */
  @Test
  void searchForEntryWithTimeReturnNullTest() {
    DiaryEntry diary = new DiaryEntry("Fish", "2 kilograms of fish");
    DiaryRegister diaryRegister = new DiaryRegister();
    diaryRegister.addEntry(diary);
    assertThrows(IllegalArgumentException.class, () -> diaryRegister.searchForEntryWithTime(null));
  }
  /**
   * Negative test: Testing the listALlEntriesByTime if the list is empty.
   */
  @Test
  void listALlEntriesByTimeEmptyListTest(){
    DiaryRegister diaryRegister = new DiaryRegister();
    assertThrows(NullPointerException.class, () -> diaryRegister.listAllEntriesByTime());
  }
  /**
   * Negative test: Testing the listAllEntriesByTimeTitle with empty list.
   */
  @Test
  void lstAlEntriesByTimeTitleEmptyListTest(){
    DiaryRegister diaryRegister = new DiaryRegister();
    assertThrows(NullPointerException.class, () -> diaryRegister.listAllEntriesByTimeTitle());

  }
  /**
   * Negative test: Testing the clearAllEntries if the list is empty.
   */
  @Test
  void clearAllEntriesWithEmptyList(){
    DiaryRegister diaryRegister = new DiaryRegister();
    assertThrows(IllegalArgumentException.class, () -> diaryRegister.clearAllEntries());
  }
}
