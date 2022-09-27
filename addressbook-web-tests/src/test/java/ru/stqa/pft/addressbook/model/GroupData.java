package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
@XStreamAlias("group")
@Entity(name="group_list")

public class GroupData {
  @XStreamOmitField
  @Id
  @Column(name="group_id")

  private  int id=Integer.MAX_VALUE;
  @Expose
  @Column(name="group_name")
  @Type(type="text")
  private  String groupName;
  @Expose
  @Column(name="group_header")
  @Type(type="text")
  private  String groupHeader;

  @Expose
  @Column(name="group_footer")
  @Type(type="text")
  private  String groupFooter;

  public GroupData withId(int id) {
    this.id=id;
    return this;
  }

  public GroupData withName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public GroupData withHeader(String groupHeader) {
    this.groupHeader = groupHeader;
     return this;
  }
  public GroupData withFooter(String groupFooter) {
    this.groupFooter = groupFooter;
     return this;
  }


  public int getId() {
    return id;
  }

  public String getGroupName() {
    return groupName;
  }

  public String getGroupHeader() {
    return groupHeader;
  }

  public String getGroupFooter() {
    return groupFooter;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id && Objects.equals(groupName, groupData.groupName);
  }
  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", groupName='" + groupName + '\'' +
            '}';
  }
  @Override
  public int hashCode() {
    return Objects.hash(id, groupName);
  }
}

