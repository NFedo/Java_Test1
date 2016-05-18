package javaCourse.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String nickName;
  private final String company;
  private final String address;
  private final String phoneHome;
  private final String phoneMobile;
  private final String email;
  // private final int iDay;
  // private final int iMonth;
  // private final String year;
  // private final int iGroup;
  private String iGroup;

  public ContactData(String firstName, String lastName, String nickName, String company, String address,
                     String phoneHome, String phoneMobile, String email ,/* int iDay, int iMonth, String year,*/ String iGroup) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.company = company;
    this.address = address;
    this.phoneHome = phoneHome;
    this.phoneMobile = phoneMobile;
    this.email = email;
   // this.iDay = iDay + 2; // чтобы задать число
   // this.iMonth = iMonth + 1; // чтобы задать месяц
   // this.year = year;
    this.iGroup = iGroup;
  }

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
}
