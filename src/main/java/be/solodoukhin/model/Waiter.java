package be.solodoukhin.model;

import java.util.Optional;

/**
 * @author SOLODOUKHIN VIKTOR
 *
 * date 16/06/18
 *
 * Classe qui représente la table TSERVEUR
 */
public class Waiter {
    /*
        ID = CODE_SER
     */
    private final String code;
    /*
        NOM_SER
     */
    private String lastName;
    /*
        PRENOM_SER
     */
    private String firstName;
    /*
        ????
     */
    private String phone;
    /*
        ????
     */
    private String mobilePhone;
    /*
        EMAIL_SER
     */
    private String email;

    public Waiter(String code, String lastName, String firstName) {
        this.code = code;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Waiter(String code, String lastName, String firstName, String phone, String mobilePhone, String email) {
        this.code = code;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Waiter waiter = (Waiter) o;

        if (code != null ? !code.equals(waiter.code) : waiter.code != null) return false;
        if (lastName != null ? !lastName.equals(waiter.lastName) : waiter.lastName != null) return false;
        if (firstName != null ? !firstName.equals(waiter.firstName) : waiter.firstName != null) return false;
        if (phone != null ? !phone.equals(waiter.phone) : waiter.phone != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(waiter.mobilePhone) : waiter.mobilePhone != null) return false;
        return email != null ? email.equals(waiter.email) : waiter.email == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "code='" + code + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phone='" + phone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
