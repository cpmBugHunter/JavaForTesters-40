package ru.pft40.bugHunter.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String name;

    @Column(name = "middlename")
    private String middleName;

    @Expose
    @Column(name = "lastname")
    private String lastName;

    @Transient
    private String company;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Transient
    private String allPhones;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Transient
    private String allEmails;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String eMail;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String eMail2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String eMail3;

    @Transient
    private String group;


    @Transient
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        return (new File(photo));
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return  this;
    }

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

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return  this;
    }

    public String getMiddleName() {
        return middleName;
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

    public String getAddress() {
        return address;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return  this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return  this;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return  this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }
    public ContactData withMobilePhone(String phone) {
        this.mobilePhone = phone;
        return  this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withEmails(String emails) {
        this.allEmails = emails;
        return this;
    }

    public String getEmail() {
        return eMail;
    }

    public ContactData withEmail(String email) {
        this.eMail = email;
        return  this;
    }

    public String getEmail2() {
        return eMail2;
    }

    public ContactData withEmail2(String email) {
        this.eMail2 = email;
        return  this;
    }

    public String getEmail3() {
        return eMail3;
    }

    public ContactData withEmail3(String email) {
        this.eMail3 = email;
        return  this;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", eMail='" + eMail + '\'' +
                ", eMail2='" + eMail2 + '\'' +
                ", eMail3='" + eMail3 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (allEmails != null ? !allEmails.equals(that.allEmails) : that.allEmails != null) return false;
        if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null) return false;
        if (eMail2 != null ? !eMail2.equals(that.eMail2) : that.eMail2 != null) return false;
        return eMail3 != null ? eMail3.equals(that.eMail3) : that.eMail3 == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (eMail2 != null ? eMail2.hashCode() : 0);
        result = 31 * result + (eMail3 != null ? eMail3.hashCode() : 0);
        return result;
    }
}
