package ru.pft40.bugHunter.model;

public class ContactData {
    private final String name;
    private final String lastName;
    private final String nickName;
    private final String company;
    private final String mobilePhone;
    private final String eMail;

    public ContactData(String name, String lastName, String nickName, String company, String mobilePhone,
                       String eMail) {
        this.name = name;
        this.lastName = lastName;
        this.nickName = nickName;
        this.company = company;
        this.mobilePhone = mobilePhone;
        this.eMail = eMail;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
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
}
