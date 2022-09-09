package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String username;
  private final String middle;
  private final String lastname;
  private final String comp;
  private String group;
  private final String addrr;
  private final String phonenum1;
  private final String email1;

  public ContactData(String username, String middle, String lastname, String comp, String group, String addrr, String phonenum1, String email1) {
    this.id=Integer.MAX_VALUE;
    this.username = username;
    this.middle = middle;
    this.lastname = lastname;
    this.comp = comp;
    this.group = group;
    this.addrr = addrr;
    this.phonenum1 = phonenum1;
    this.email1 = email1;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "username='" + username + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  public ContactData(int id, String username, String middle, String lastname, String comp, String group, String addrr, String phonenum1, String email1) {
    this.id=id;
    this.username = username;
    this.middle = middle;
    this.lastname = lastname;
    this.comp = comp;
    this.group = group;
    this.addrr = addrr;
    this.phonenum1 = phonenum1;
    this.email1 = email1;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(username, that.username) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, lastname);
  }

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getMiddle() {
    return middle;
  }

  public String getLastname() {
    return lastname;
  }

  public String getComp() {
    return comp;
  }

  public String getAddrr() {
    return addrr;
  }

  public String getPhonenum1() {
    return phonenum1;
  }

  public String getEmail1() {
    return email1;
  }

  public String getGroup() {
    return group;
  }
}
