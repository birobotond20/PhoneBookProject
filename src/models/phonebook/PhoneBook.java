package models.phonebook;

import exception.ContactException;
import models.contact.Contact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PhoneBook {

  private List<Contact> contacts;

  public PhoneBook() {
    this.contacts = new ArrayList<>();
  }

  public PhoneBook(List<Contact> contacts) {
    this();
    bulkCreate(contacts);
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  public boolean createContact(Contact newContact) {
    if (newContact == null) {
      throw new IllegalArgumentException("Method argument cannot be null!");
    }

    if (!contacts.contains(newContact)) {
      return contacts.add(newContact);
    }

    return false;
  }

  public boolean deleteContact(Contact contactToDelete) {
    if (contactToDelete == null) {
      throw new IllegalArgumentException("Method argument cannot be null!");
    }

    return contacts.remove(contactToDelete);
  }

  public Contact updateContact(int index, Contact updatedContact) {
    if (updatedContact == null) {
      throw new IllegalArgumentException("Method argument cannot be null!");
    }

    return contacts.set(index, updatedContact);
  }

  public boolean bulkCreate(Collection<Contact> contactsToAdd) {
    if (contactsToAdd == null) {
      throw new IllegalArgumentException("Method argument cannot be null!");
    }

    return contacts.addAll(contactsToAdd);
  }

  public boolean bulkRemove(Collection<Contact> contactsToRemove) {
    if (contactsToRemove == null) {
      throw new IllegalArgumentException("Method argument cannot be null!");
    }

    return contacts.removeAll(contactsToRemove);
  }
}
