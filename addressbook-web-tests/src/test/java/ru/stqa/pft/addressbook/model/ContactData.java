package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")

public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name="firstname")
  private String username;
  @Column(name="middlename")
  private String middle;

  @Column(name="lastname")
  private String lastname;

  @Column(name ="company")
  @Type(type ="text")
  private String comp;

  @Transient
  private String group;
  @Column(name ="address")
  @Type(type ="text")

  private String addrr;

  @Column(name ="home")
  @Type(type ="text")
  private String phonenum1;

  @Column(name="mobile")
  @Type(type="text")
  private String phonenum2;

  @Column(name="work")
  @Type(type="text")
  private String phonenum3;
  @Transient
  private String sec_phonehone4;
  @Transient

  private String allPhones;

  @Expose
  @Column(name="email1")
  @Type(type="text")
  private String email1;
  @Expose
  @Type(type="text")
  @Column(name="email2")
  private String email2;
  @Expose
  @Type(type="text")
  @Column(name="email3")
  private String email3;

  @Transient
  private String allEmails;

  @Column(name="photo")
  @Type(type ="text")
  private String photo;

  public File getPhoto() {
    return new File(photo);
  }
 public String getAllPhones() {
    return allPhones;
  }
  public String getAllEmails() {
    return allEmails;
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

  public String getEmail1() {    return email1;  }

  public String getEmail2() {    return email2;  }

  public String getEmail3() {    return email3;  }

  public String getGroup() {    return group;  }

  public String getPhonenum2() {    return phonenum2;  }

  public String getPhonenum3() {    return phonenum3;  }

  public String getSec_phonehone4() {    return sec_phonehone4;  }


  public ContactData withId(int id) {
    this.id = id;
    return this;
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

  public ContactData withPhonenum2(String phonenum2) {
    this.phonenum2 = phonenum2;
    return this;
  }

  public ContactData withPhonenum3(String phonenum3) {
    this.phonenum3 = phonenum3;
    return this;
  }

  public ContactData withSec_phone4(String sec_phone4) {
    this.sec_phonehone4 = sec_phone4;
    return this;
  }

  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }
  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", middle='" + middle + '\'' +
            ", lastname='" + lastname + '\'' +
            ", comp='" + comp + '\'' +
            ", group='" + group + '\'' +
            ", addrr='" + addrr + '\'' +
            ", phonenum1='" + phonenum1 + '\'' +
            ", phonenum2='" + phonenum2 + '\'' +
            ", phonenum3='" + phonenum3 + '\'' +
            ", sec_phonehone4='" + sec_phonehone4 + '\'' +
            ", email1='" + email1 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            '}';
  }

  public ContactData setAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(username, that.username) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, lastname);
  }
}


