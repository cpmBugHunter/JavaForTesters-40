package ru.pft40.bugHunter.model;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String name;
    private String lastName;
    private String company;
    private String mobilePhone;
    private String eMail;
    private String group;


    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return  this;
    }

    public String getName() {
        return name;
    }

    public ContactData withName(String name) {
        this.name = name;
        return  this;
    }

    public String getLastName() {
        return lastName;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return  this;
    }

    public String getCompany() {
        return company;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return  this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }
    public ContactData withMobilePhone(String phone) {
        this.mobilePhone = phone;
        return  this;
    }

    public String getEmail() {
        return eMail;
    }

    public ContactData withEmail(String email) {
        this.eMail = email;
        return  this;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" + "id=" + id + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

}
