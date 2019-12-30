package models.contact;

import models.Title;
import models.address.Address;

import java.time.LocalDate;
import java.util.List;

public class Contact {

  private Title title;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private List<String> phoneNumbers;
  private List<Address> addresses;

  public Contact() {
  }

  public Contact(String firstName, String lastName, LocalDate dateOfBirth, List<String> phoneNumbers,
                 List<Address> addresses) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumbers = phoneNumbers;
    this.addresses = addresses;
  }

  public Contact(Title title, String firstName, String lastName, LocalDate dateOfBirth,
                 List<String> phoneNumbers, List<Address> addresses) {
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumbers = phoneNumbers;
    this.addresses = addresses;
  }

  public Contact(Contact contact) {
    this.title = contact.title;
    this.firstName = contact.firstName;
    this.lastName = contact.lastName;
    this.dateOfBirth = contact.dateOfBirth;
    this.phoneNumbers = contact.phoneNumbers;
    this.addresses = contact.addresses;
  }

  public Title getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
      this.title = (title == null) ? Title.DEFAULT : convertToTitle(title);
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

  public LocalDate getDateOfBirth() {
    return this.dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = convertToLocalDate(dateOfBirth);
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

  public static LocalDate convertToLocalDate(String date) {
    String formattedDate = date.substring(0,4) + "-" +
        date.substring(4,6) + "-" +
        date.substring(6);
    return LocalDate.parse(formattedDate);
  }

  public boolean isBirthdayWithinRange(String startDate, String endDate) {
    boolean isAfter = dateOfBirth.isAfter(convertToLocalDate(startDate));
    boolean isBefore = dateOfBirth.isBefore(convertToLocalDate(endDate));
    return isAfter && isBefore;
  }

  public static Title convertToTitle(String title) {
    return Title.getValueFrom(title);
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

  @Override
  public String toString() {
    return "Contact{" +
        "title=" + title +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", phoneNumbers=" + phoneNumbers +
        ", addresses=" + addresses +
        '}';
  }
}
