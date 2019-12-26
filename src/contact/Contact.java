package contact;

import address.Address;

import java.util.List;

public class Contact {

  private String title;
  private String firstName;
  private String lastName;
  private String date;
  private List<String> phoneNumbers;
  private List<Address> addresses;

  public Contact() {
  }

  public Contact(String firstName, String lastName, String date, List<String> phoneNumbers,
                 List<Address> addresses) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.date = date;
    this.phoneNumbers = phoneNumbers;
    this.addresses = addresses;
  }

  public Contact(String title, String firstName, String lastName, String date,
                 List<String> phoneNumbers, List<Address> addresses) {
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.date = date;
    this.phoneNumbers = phoneNumbers;
    this.addresses = addresses;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getName() {
    return this.firstName + " " + this.lastName;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public List<String> getPhoneNumbers() {
    return this.phoneNumbers;
  }

  public void setPhoneNumbers(List<String> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public List<Address> getAddresses() {
    return this.addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Contact)) {
      return false;
    }

    Contact other = (Contact) obj;
    return this.getName().equalsIgnoreCase(other.getName());
  }
}
