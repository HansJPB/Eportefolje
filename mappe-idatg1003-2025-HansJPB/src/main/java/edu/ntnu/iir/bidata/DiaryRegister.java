package edu.ntnu.iir.bidata;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Creating a class for the DiaryRegister.
 */
public class DiaryRegister {
  private final List<DiaryEntry> diaryEntries;

  /**
   * Constructor for the DiaryRegister Class.
   */
  public DiaryRegister() {
    diaryEntries = new ArrayList<>();

  }

  /**
   * A method which is able to add an entry to the DiaryRegister.
   *
   * @param entry the entry chosen to be added to the list.
   * @throws IllegalArgumentException if the entry already exists in the register.
   */

  public void addEntry(DiaryEntry entry) {
    Iterator<DiaryEntry> diaryRegisterIterator = diaryEntries.iterator();
    while (diaryRegisterIterator.hasNext()) {
      DiaryEntry diaryEntryCompared = diaryRegisterIterator.next();
      if (diaryEntryCompared.getTitle().equalsIgnoreCase(entry.getTitle())
          && diaryEntryCompared.getText().equalsIgnoreCase(entry.getText())) {
        throw new IllegalArgumentException("Entry already exists");
      }
    }
    diaryEntries.add(entry);
  }

  /**
   * A method which is able to delete a chosen entry.
   * The method
   *
   * @param entry the chosen entry to be deleted.
   * @throws IllegalArgumentException if either the entry is null or does not exist.
   *
   */
  public void deleteEntry(DiaryEntry entry) {
    if (entry == null) {
      throw new IllegalArgumentException("Entry cannot be null");

    } else if (!diaryEntries.contains(entry)) {
      throw new IllegalArgumentException("Entry not found");
    } else {
      Iterator<DiaryEntry> deleteIterator = diaryEntries.iterator();
      while (deleteIterator.hasNext()) {
        if (deleteIterator.next().equals(entry)) {
          deleteIterator.remove();
        }
      }
    }
  }

  /**
   * A method allowing the user to search for an entry at a chosen time.
   *
   * @param date the chosen date to use as a search point.
   * @return returns the entry/entries written on the chosen date.
   * @throws IllegalArgumentException if there are no entries found at the specific date.
   */
  public List<DiaryEntry> searchForEntryWithTime(LocalDateTime date) {
    List<DiaryEntry> searchResult = new ArrayList<>();
    for (DiaryEntry entry : diaryEntries) {
      if (entry.getDate().equals(date)) {
        searchResult.add(entry);
      }
    }
    if (searchResult.isEmpty()) {
      throw new IllegalArgumentException("No entries were found at this date");
    }
    return searchResult;
  }

  /**
   * A method which creates a list that places the entries in an order of recency.
   *
   * @return returns the original list sorted by time.
   * @throws NullPointerException if the original register is empty.
   *
   */
  public List<DiaryEntry> listAllEntriesByTime() {
    if (diaryEntries.isEmpty()) {
      throw new NullPointerException("There are no entries in the list");
    } else {
      List<DiaryEntry> timeSortedList = new ArrayList<>(diaryEntries);
      timeSortedList.sort((entry1, entry2) -> entry2.getDate().compareTo(entry1.getDate()));

      return timeSortedList;
    }
  }

  /**
   * A method which lists all entries by recency.
   *
   * @return returns the titles of the entries instead of the entire class.
   */
  public List<String> listAllEntriesByTimeTitle() {
    if (diaryEntries.isEmpty()) {
      throw new NullPointerException("There are no entries in the list");
    } else {
      List<DiaryEntry> timeSortedList = new ArrayList<>(diaryEntries);
      timeSortedList.sort((entry1, entry2) -> entry2.getDate().compareTo(entry1.getDate()));

      List<String> titles = new ArrayList<>();
      for (DiaryEntry entry : timeSortedList) {
        titles.add(entry.getTitle());
      }
      return titles;
    }

  }

  /**
   * A method that clears all entries in the register.
   *
   * @throws IllegalArgumentException if the list is empty of diary registers.
   */
  public void clearAllEntries() {
    if (diaryEntries.isEmpty()) {
      throw new IllegalArgumentException("There are no entries in the list");
    } else {
      diaryEntries.clear();
    }
  }
}