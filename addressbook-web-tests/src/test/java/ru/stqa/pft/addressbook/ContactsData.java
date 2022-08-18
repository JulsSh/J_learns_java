package ru.stqa.pft.addressbook;

public class ContactsData {
  private final String username;
  private final String middle;
  private final String lastname;
  private final String comp;
  private final String addrr;
  private final String phonenum1;
  private final String email1;

  public ContactsData(String username, String middle, String lastname, String comp, String addrr, String phonenum1, String email1) {
    this.username = username;
    this.middle = middle;
    this.lastname = lastname;
    this.comp = comp;
    this.addrr = addrr;
    this.phonenum1 = phonenum1;
    this.email1 = email1;
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
}
