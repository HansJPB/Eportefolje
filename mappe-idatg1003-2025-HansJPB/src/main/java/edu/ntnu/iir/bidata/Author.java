package edu.ntnu.iir.bidata;

/**
 * Creates the Author class with name and id as fields.
 */
public class Author {
  private String name;
  private int id;

  /**
   * Constructor for the Author class.
   *
   * @param name name of the author
   * @param id   the id of the author
   */
  public Author(String name, int id) {
    this.name = name;
    this.id = id;
  }

  /**
   * Method that gets the author's name.
   *
   * @return returns the author's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Method that gets the author's id.
   *
   * @return returns the author's id.
   */
  public int getId() {
    return id;
  }

  /**
   * Method that sets an author's name.
   *
   * @param name the chosen name.
   * @throws IllegalArgumentException if the chosen name is empty.
   */
  public void setName(String name) {
    if (name.equals("")) {
      throw new IllegalArgumentException("Name cannot be empty");
    } else
      this.name = name;
  }

  /**
   * Method that sets an author's ID.
   *
   * @param id the chosen id.
   * @throws IllegalArgumentException if the chosen id <= 0.
   */
  public void setId(int id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Id cannot be negative or zero");
    }
    this.id = id;
  }


}
