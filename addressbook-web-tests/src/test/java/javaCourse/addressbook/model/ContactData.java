package javaCourse.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstName;

  @Expose
  @Column(name = " middlename")
  private String middleName;

  @Expose
  @Column(name = "lastname")
  private String lastName;

  @Column(name = "nickname")
  private String nickName;

  @Expose
  @Column(name = "company")
  private String company;

  @Expose
  @Column(name = "title")
  private String title;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String phoneHome;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String phoneMobile;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String phoneWork;

  @Transient
  private String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String allEmails;

  @Transient
  @Column(name = "homepage")
  @Type(type = "text")
  private String homePage;

  @Expose
  @Transient
  private int iDay;

  @Expose
  @Transient
  private int iMonth;

  @Transient
  private String strMonth;

  @Expose
  @Transient
  private String year;

  // private int iGroup;
  @Expose
  @Transient
  private String cGroup;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @Expose
  @Transient
  private String photoPath;

  public int getId() { return id;  }

  public String getFirstName() { return firstName; }

  public String getMiddleName() { return middleName; }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getCompany() {
    return company;
  }

  public String getTitle() {
    return title;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneHome() {
    return phoneHome;
  }

  public String getPhoneMobile() {
    return phoneMobile;
  }

  public String getPhoneWork() {
    return phoneWork;
  }

  public String getAllPhones() { return allPhones;  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() { return allEmails; }

  public String getHomePage() {
    return homePage;
  }

  public int getiDay() { return iDay; }

  public int getiMonth() { return iMonth; }

  public String getStrMonth() { return strMonth; }

  public String getYear() { return year; }

  public String getcGroup() { return cGroup; }

  public File getPhoto() {
    File result = null;
    if (photo != null) { result = new File(photo); }
    return result;
  }

  public String getPhotoPath() { return photoPath; }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  public ContactData withId (int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withNickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withPhoneHome(String phoneHome) {
    this.phoneHome = phoneHome;
    return this;
  }

  public ContactData withPhoneMobile(String phoneMobile) {
    this.phoneMobile = phoneMobile;
    return this;
  }

  public ContactData withPhoneWork(String phoneWork) {
    this.phoneWork = phoneWork;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
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

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withHomePage(String homePage) {
    this.homePage = homePage;
    return this;
  }

  public ContactData withiDay(int iDay) {
    this.iDay = iDay; // чтобы задать число;
    return this;
  }

  public ContactData withiMonth(int iMonth) {
    this.iMonth = iMonth; // чтобы задать месяц цифрами;
    return this;
  }

  public ContactData withMonthStr(String strMonth) {
    this.strMonth = strMonth; // чтобы задать месяц;
    return this;
  }

  public ContactData withYear(String year) {
    this.year = year;
    return this;
  }

  public ContactData withcGroup(String cGroup) {
    this.cGroup = cGroup;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withPhotoPath(String photoPath) {
    this.photoPath = photoPath;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}
