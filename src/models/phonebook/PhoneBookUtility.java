package models.phonebook;

import models.Title;
import models.address.Address;
import models.contact.Contact;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneBookUtility {

  public static List<Contact> filterByTitle(List<Contact> contacts, Title title) {
    return contacts.stream()
        .filter(contact -> contact.getTitle().equals(title))
        .collect(Collectors.toList());
  }

  public static List<Contact> filterByFirstName(List<Contact> contacts, String firstName) {
    return contacts.stream()
        .filter(contact -> contact.getFirstName().equals(firstName))
        .collect(Collectors.toList());
  }

  public static List<Contact> filterByLastName(List<Contact> contacts, String lastName) {
    return contacts.stream()
        .filter(contact -> contact.getLastName().equals(lastName))
        .collect(Collectors.toList());
  }

  public static List<Contact> filterByName(List<Contact> contacts, String name) {
    return contacts.stream()
        .filter(contact -> (contact.getFirstName().equals(name) || contact.getLastName().equals(name)))
        .collect(Collectors.toList());
  }

  public static List<Contact> filterByPhoneNumber(List<Contact> contacts, String phoneNumber) {
    return contacts.stream()
        .filter(contact -> contact.getPhoneNumbers().contains(phoneNumber))
        .collect(Collectors.toList());
  }

  public static List<Contact> filterByDate(List<Contact> contacts, String fromDate, String toDate) {
    return contacts.stream()
        .filter(contact -> contact.isBirthdayWithinRange(fromDate, toDate))
        .collect(Collectors.toList());
  }

  public static List<Contact> filterByAddress(List<Contact> contacts, String query) {

    return contacts.stream()
        .filter(contact -> contact.hasMatchingAddressField(query))
        .collect(Collectors.toList());
  }
}
