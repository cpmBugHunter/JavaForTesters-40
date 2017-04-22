package ru.pft40.bugHunter.model;

public class ContactData {
    private final String name;
    private final String lastName;
    private final String company;
    private final String mobilePhone;
    private final String eMail;
    private String group;

    public ContactData(String name, String lastName, String company, String mobilePhone,
                       String eMail, String group) {
        this.name = name;
        this.lastName = lastName;
        this.company = company;
        this.mobilePhone = mobilePhone;
        this.eMail = eMail;
        this.group = group;
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
}
