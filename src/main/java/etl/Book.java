package etl;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {

  private Integer id;
  private Integer authorId;
  private String title;
  private String releaseDate;
  private String link;

  public Book() {
  }

  public Book(Integer id, Integer authorId, String title, String releaseDate, String link) {
    this.id = id;
    this.authorId = authorId;
    this.title = title;
    this.releaseDate = releaseDate;
    this.link = link;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Integer authorId) {
    this.authorId = authorId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(id, book.id) && Objects.equals(authorId, book.authorId) && Objects.equals(title, book.title) && Objects.equals(releaseDate, book.releaseDate) && Objects.equals(link, book.link);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, authorId, title, releaseDate, link);
  }

}
