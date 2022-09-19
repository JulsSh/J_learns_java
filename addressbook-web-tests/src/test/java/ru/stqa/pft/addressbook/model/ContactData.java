package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id=Integer.MAX_VALUE;
  private String username;
  private String middle;
  private String lastname;
  private String comp;
  private String group;
  private String addrr;
  private String phonenum1;
  private String email1;

  @Override
  public String toString() {
    return "ContactData{" +
            "username='" + username + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
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

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(username, that.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username);
  }

  public ContactData withUsername(String username) {
    this.username = username;
    return this;
  }

  public ContactData withMiddle(String middle) {
    this.middle = middle;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withComp(String comp) {
    this.comp = comp;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withAddrr(String addrr) {
    this.addrr = addrr;
    return this;
  }

  public ContactData withPhonenum1(String phonenum1) {
    this.phonenum1 = phonenum1;
    return this;
  }

  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }
}

