package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.title.Title;
import models.contact.Contact;
import models.phonebook.PhoneBookUtility;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PhoneBookUtilityTests {

  private List<Contact> contactList;

  @Before
  public void init() {
    contactList = new ArrayList<>();
    contactList.addAll(readContactsFromJson("phonebook_data.json"));
  }

  @Test
  public void filterByTitleTest_queryContactsWithTitleMr_returnsCorrectNumberOfResults() {
    assertEquals(3, PhoneBookUtility.filterByTitle(contactList, Title.MR).size());
  }

  @Test
  public void filterByTitleTest_queryContactsWithTitleMr_returnListOfContactsWithTitleMr() {
    List<Contact> contactsWithTitleMr = PhoneBookUtility.filterByTitle(contactList, Title.MR);
    for (Contact contact : contactsWithTitleMr) {
      assertEquals("Mr", contact.getTitle().toString());
    }
  }

  @Test
  public void filterByTitleTest_queryContactsWithTitleMrs_returnsCorrectNumberOfResults() {
    assertEquals(2, PhoneBookUtility.filterByTitle(contactList, Title.MRS).size());
  }

  @Test
  public void filterByTitleTest_queryContactsWithTitleMs_returnsCorrectNumberOfResults() {
    assertEquals(2, PhoneBookUtility.filterByTitle(contactList, Title.MS).size());
  }

  @Test
  public void filterByFirstNameTest_queryContactsWithFirstNameJohn_returnListOfJohns() {
    List<Contact> contactsWithFirstNameJoe = PhoneBookUtility.filterByFirstName(contactList,
        "John");
    for (Contact contact : contactsWithFirstNameJoe) {
      assertEquals("John", contact.getFirstName());
    }
  }

  @Test
  public void filterByLastNameTest_queryContactsWithLastNameDoe_returnListOfDoes() {
    List<Contact> contactsWithLastNameJoe = PhoneBookUtility.filterByLastName(contactList,
        "Doe");
    for (Contact contact : contactsWithLastNameJoe) {
      assertEquals("Doe", contact.getLastName());
    }
  }

  @Test
  public void filterByNameTest_queryContactsWithNameMamford_returnListOfMamfords() {
    List<Contact> contactsWithNameMamford = PhoneBookUtility.filterByName(contactList,
        "Mamford");
    for (Contact contact : contactsWithNameMamford) {
      assertTrue(contact.getName().contains("Mamford"));
    }
  }

  @Test
  public void filterByPhoneNumberTest_withPhoneNumberKnownAsPresent_returnNonEmptyList() {
    List<Contact> contactsWithMatchingPhoneNumbers = PhoneBookUtility.filterByPhoneNumber(contactList,
        "1-202-555-0118");
    for (Contact contact : contactsWithMatchingPhoneNumbers) {
      assertTrue(contact.getPhoneNumbers().contains("1-202-555-0118"));
    }
  }

  @Test
  public void filterByPhoneNumberTest_withPhoneNumberNotInData_returnEmptyList() {
    assertTrue(PhoneBookUtility.filterByPhoneNumber(contactList,"+36 01 234 5678").isEmpty());
  }

  @Test
  public void filterByDateTest_withValidRange_returnNonEmptyList() {
    List<Contact> contactsBornInNinetees = PhoneBookUtility.filterByDate(contactList,
        "1989-12-31", "2000-01-01");

    for (Contact contact : contactsBornInNinetees) {
      assertTrue(contact.isBirthdayWithinRange("1989-12-31", "2000-01-01"));
    }
  }

  @Test
  public void filterByAddress_withValidAddressField_returnNonEmptyList() {
    List<Contact> contactsWithMatchingZipCodes = PhoneBookUtility.filterByAddress(contactList,
        "51331");

    for (Contact contact : contactsWithMatchingZipCodes) {
      assertTrue(contact.hasMatchingAddressField("51331"));
    }
  }

  private List<Contact> readContactsFromJson(String path) {
    ObjectMapper objectMapper = new ObjectMapper();
    String json = "";

    {
      try {
        json = new String(Files.readAllBytes(Paths.get(path)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    List<Contact> contacts = new ArrayList<>();

    try {
      contacts = objectMapper.readValue(json, new TypeReference<List<Contact>>() {
      });
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return contacts;
  }
}
