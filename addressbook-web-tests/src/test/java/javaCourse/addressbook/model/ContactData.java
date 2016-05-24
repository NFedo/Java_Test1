package javaCourse.addressbook.model;

public class ContactData {
  private String firstName;
  private String lastName;
  private String nickName;
  private String company;
  private String address;
  private String phoneHome;
  private String phoneMobile;
  private String email;
  // private int iDay;
  // private int iMonth;
  // private String year;
  // private int iGroup;
  private String iGroup;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getCompany() {
    return company;
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

  public String getEmail() {
    return email;
  }

  // public int getiDay() {    return iDay;  }

  // public int getiMonth() {    return iMonth;  }

  // public String getYear() {    return year;  }

  public String getiGroup() { return iGroup; }

  public String getFullName() { return lastName + firstName; }


  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
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

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

 /* public ContactData withiDay(int iDay) {
    this.iDay = iDay + 2; // чтобы задать число;
    return this;
  }

  public ContactData withiMonth(int iMonth) {
    this.iMonth = iMonth + 1; // чтобы задать месяц;
    return this;
  }

  public ContactData withYear(String year) {
    this.year = year;
    return this;
  }*/

  public ContactData withiGroup(String iGroup) {
    this.iGroup = iGroup;
    return this;
  }
}
