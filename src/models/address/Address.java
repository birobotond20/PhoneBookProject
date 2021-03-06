package models.address;

public class Address {

  private String country;
  private String zipCode;
  private String city;
  private String street;

  public Address() {
  }

  public Address(String country, String zipCode, String city, String street) {
    this.country = country;
    this.zipCode = zipCode;
    this.city = city;
    this.street = street;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public boolean hasFieldMatch(String searchWord) {
    return
        this.country.equalsIgnoreCase(searchWord) ||
        this.zipCode.equalsIgnoreCase(searchWord) ||
        this.city.equalsIgnoreCase(searchWord) ||
        this.street.equalsIgnoreCase(searchWord);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Address)) {
      return false;
    }

    Address other = (Address) obj;
    return
        this.country.equals(other.getCountry()) &&
        this.zipCode.equals(other.getZipCode()) &&
        this.city.equals(other.getCity()) &&
        this.street.equals(other.getStreet());
  }
}
