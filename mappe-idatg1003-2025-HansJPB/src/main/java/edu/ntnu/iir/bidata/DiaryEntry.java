package edu.ntnu.iir.bidata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creating a class called DiaryEntry, which contains the fields author, title, and the date.
 */
public class DiaryEntry {
  private String title;
  private String text;
  private final LocalDateTime date;

  /**
   * Creating a constructor for the DiaryEntry.
   *
   * @param title title of the entry
   * @param text  the entry itself
   */
  public DiaryEntry(String title, String text) {
    this.title = title;
    this.text = text;
    this.date = LocalDateTime.now();

  }

  /**
   * Creates a method to get the title of the entry.
   *
   * @return returns the title of the entry
   */
  public String getTitle() {
    return title;
  }

  /**
   * Creates a method to get the actual content of the entry.
   *
   * @return returns the content of the entry.
   */
  public String getText() {
    return text;
  }

  /**
   * Creates a method that gets the date the entry was made.
   *
   * @return the date of the entry.
   */
  public LocalDateTime getDate() {
    return date;
  }

  /**
   * Creates a method to get the time, formatted into year, month, day, hour and minute.
   *
   * @return returns the formatted date
   */
  public String getFormattedDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    return date.format(formatter);
  }

  /**
   * Creates a method to add / change the title of an entry.
   * use throws
   *
   * @param title the new / changed title
   * @throws IllegalArgumentException if the input for the title is empty
   *
   */
  public void setTitle(String title) {
    if (title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be empty");
    }
    this.title = title;
  }

  /**
   * Creates a method to add / change the content of an entry.
   *
   * @param text the new / changed entry
   * @throws IllegalArgumentException if the input for the content is empty.
   */
  public void setText(String text) {
    if (text.isBlank()) {
      throw new IllegalArgumentException("Text cannot be empty");
    }
    this.text = text;
  }


}
