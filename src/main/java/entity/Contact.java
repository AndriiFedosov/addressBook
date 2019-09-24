package entity;


import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

public class Contact implements Comparable<Contact> {
    private int id;
    private String name;
    private String lastName;
    private int age;
    private String phoneNumber;
    private boolean married;
    private LocalDateTime createDate;
    private LocalDateTime updateTime;

    public Contact(String name, String lastName, int age, String phoneNumber,
                   boolean married, LocalDateTime createDate, LocalDateTime updateTime) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.married = married;
        this.createDate = createDate;
        this.updateTime = updateTime;
    }

    public Contact() {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id &&
                age == contact.age &&
                phoneNumber.equals(contact.phoneNumber) &&
                married == contact.married &&
                Objects.equals(name, contact.name) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(createDate, contact.createDate) &&
                Objects.equals(updateTime, contact.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, age, phoneNumber, married, createDate, updateTime);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", married=" + married +
                ", createDate=" + createDate +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public int compareTo(Contact o) {
        return this.name.compareTo(o.getName());
    }
}
