package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ContactException;
import models.title.Title;
import models.address.Address;
import models.contact.Contact;
import models.phonebook.PhoneBook;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PhoneBookTests {

  private PhoneBook phoneBook;
  private Contact testContact1;
  private Contact testContact2;
  private Address testAddress1;
  private Address testAddress2;
  private List<String> testPhoneNumbers;
  private List<Address> testAddresses;
  private List<Contact> testContactList;

  @Before
  public void init() {
    String phoneNumber1 = "+36 30 123 4567";
    String phoneNumber2 = "+36 12 345 6789";
    testPhoneNumbers = new ArrayList<String>() {
      {
        add(phoneNumber1);
        add(phoneNumber2);
      }
    };

    testAddress1 = new Address("Hungary", "1000", "Budapest", "József körút");
    testAddress2 = new Address("Hungary", "1068", "Budapest", "Erzsébet körút");
    testAddresses = new ArrayList<Address>() {
      {
        add(testAddress1);
        add(testAddress2);
      }
    };

    phoneBook = new PhoneBook();
    phoneBook.setContacts(readContactsFromJson("phonebook_data.json"));
    testContact1 = new Contact("Peter", "Griffin", LocalDate.now(), testPhoneNumbers, testAddresses);
    testContact2 = new Contact(Title.MR, "Stewart", "Griffin", LocalDate.now(), testPhoneNumbers,
        testAddresses);

    testContactList = new ArrayList<Contact>() {
      {
        add(testContact1);
        add(testContact2);
      }
    };
  }

  @Test
  public void createContactTest_withValidContact_contactListSizeGrowsByOne() {
    assertEquals(12, phoneBook.getContacts().size());

    assertTrue(phoneBook.createContact(testContact1));
    assertEquals(13, phoneBook.getContacts().size());
  }

  @Test
  public void createContactTest_withValidContact_returnsCorrectContact() {
    phoneBook.createContact(testContact1);
    int lastContactsIndex = phoneBook.getContacts().size() - 1;
    Contact latestContactAdded = phoneBook.getContacts().get(lastContactsIndex);

    assertEquals(testContact1, latestContactAdded);
  }

  @Test
  public void deleteContactTest_withValidContact_contactListSizeDecreasesByOne() {
    assertEquals(12, phoneBook.getContacts().size());

    int lastContactsIndex = phoneBook.getContacts().size() - 1;
    Contact lastContactInPhoneBook = phoneBook.getContacts().get(lastContactsIndex);

    assertTrue(phoneBook.deleteContact(lastContactInPhoneBook));
    assertEquals(11, phoneBook.getContacts().size());
  }

  @Test
  public void updateContactTest_withValidContact_returnsPreviousContact() throws ContactException {
    int lastContactsIndex = phoneBook.getContacts().size() - 1;
    Contact lastContact = phoneBook.getContacts().get(lastContactsIndex);
    String lastContactsName = lastContact.getName();
    Contact updatedContact = new Contact(lastContact);
    updatedContact.setFirstName("Lois");
    assertEquals(lastContactsName, phoneBook.updateContact(lastContactsIndex, updatedContact).getName());
    assertNotEquals(lastContactsName, phoneBook.getContacts().get(lastContactsIndex).getName());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void updateContactTest_withInvalidIndex_throwsIndexOutOfBoundsException() {
    assertEquals("Lois", phoneBook.updateContact(60, testContact1).getFirstName());
  }

  @Test
  public void bulkCreateTest_withCollection_contactListSizeGrowsByCollectionSize() {
    assertEquals(12, phoneBook.getContacts().size());
    assertTrue(phoneBook.bulkCreate(testContactList));
    assertEquals(14, phoneBook.getContacts().size());
  }

  @Test
  public void bulkRemoveTest_removeFirstAndLastContact_contactListSizeDecreasesByCollectionSize() {
    int contactListSize = phoneBook.getContacts().size();
    Contact firstContact = phoneBook.getContacts().get(0);
    Contact lastContact = phoneBook.getContacts().get(contactListSize - 1);
    testContactList = new ArrayList<Contact>() {
      {
        add(firstContact);
        add(lastContact);
      }
    };

    assertTrue(phoneBook.bulkRemove(testContactList));
    assertEquals(10, phoneBook.getContacts().size());
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
