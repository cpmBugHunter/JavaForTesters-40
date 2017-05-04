package ru.pft40.bugHunter.model;

public class ContactData {
    private int id;
    private final String name;
    private final String lastName;
    private final String company;
    private final String mobilePhone;
    private final String eMail;
    private String group;

    public ContactData(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.company = null;
        this.eMail = null;
        this.mobilePhone = null;
    }

    public ContactData(String name, String lastName, String company, String mobilePhone,
                       String eMail, String group) {
        this.id = 0;
        this.name = name;
        this.lastName = lastName;
        this.company = company;
        this.mobilePhone = mobilePhone;
        this.eMail = eMail;
        this.group = group;
    }

    public ContactData(String name, String lastName, String company, String mobilePhone,
                       String eMail) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.lastName = lastName;
        this.company = company;
        this.mobilePhone = mobilePhone;
        this.eMail = eMail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String geteMail() {
        return eMail;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" + "id=" + id + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", company='" + company + '\'' + ", mobilePhone='" + mobilePhone + '\'' + ", eMail='" + eMail + '\'' + ", group='" + group + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
