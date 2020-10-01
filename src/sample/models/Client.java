package sample.models;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import java.sql.*;

@Entity
@Table(name = "client")
public class Client {
    private int id;
    private String surname;
    private String firstname;
    private String patronymic;
    private Date dateOfBirth;
    private String passportSeries;
    private String passportNumber;
    private String passportIssuedBy;
    private Date passportDateOfIssue;
    private String idNumber;
    private String placeOfBirth;
    private String addressOfTheActualResidence;
    private String homePhone;
    private String mobilePhone;
    private String email;
    private String placeOfWork;
    private String position;
    private String placeOfResidence;
    private byte retiree;
    private Integer monthlyIncome;

    private Integer cityOfActualResidenceId;
    private Integer marialStatusId;
    private Integer citizenshipId;
    private Integer disabilityId;

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Surname", nullable = false, length = 50)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "Firstname", nullable = false, length = 50)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "Patronymic", nullable = false, length = 50)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "DateOfBirth", nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "PassportSeries", nullable = false, length = 2)
    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    @Basic
    @Column(name = "PassportNumber", nullable = false, length = 8)
    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Basic
    @Column(name = "PassportIssuedBy", nullable = false, length = 50)
    public String getPassportIssuedBy() {
        return passportIssuedBy;
    }

    public void setPassportIssuedBy(String passportIssuedBy) {
        this.passportIssuedBy = passportIssuedBy;
    }

    @Basic
    @Column(name = "PassportDateOfIssue", nullable = false)
    public Date getPassportDateOfIssue() {
        return passportDateOfIssue;
    }

    public void setPassportDateOfIssue(Date passportDateOfIssue) {
        this.passportDateOfIssue = passportDateOfIssue;
    }

    @Basic
    @Column(name = "IdNumber", nullable = false, length = 18)
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "PlaceOfBirth", nullable = false, length = 50)
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @Basic
    @Column(name = "AddressOfTheActualResidence", nullable = false, length = 50)
    public String getAddressOfTheActualResidence() {
        return addressOfTheActualResidence;
    }

    public void setAddressOfTheActualResidence(String addressOfTheActualResidence) {
        this.addressOfTheActualResidence = addressOfTheActualResidence;
    }

    @Basic
    @Column(name = "HomePhone", nullable = true, length = 18)
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Basic
    @Column(name = "MobilePhone", nullable = true, length = 18)
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 25)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PlaceOfWork", nullable = true, length = 50)
    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    @Basic
    @Column(name = "Position", nullable = true, length = 50)
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "PlaceOfResidence", nullable = false, length = 50)
    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    @Basic
    @Column(name = "Retiree", nullable = false)
    public byte getRetiree() {
        return retiree;
    }

    public void setRetiree(byte retiree) {
        this.retiree = retiree;
    }

    @Basic
    @Column(name = "MonthlyIncome", nullable = true, precision = 0)
    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }


    @Column(name = "CityOfActualResidenceId")
    @ManyToOne(targetEntity = Cityofactualresidence.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cityOfActualResidenceId")
    public Integer getCityOfActualResidenceId() { return cityOfActualResidenceId; }

    public void setCityOfActualResidenceId(Integer cityOfActualResidenceId) {
        this.cityOfActualResidenceId = cityOfActualResidenceId;
    }

    @Column(name = "MarialStatusId")
    @ManyToOne(targetEntity = Marialstatus.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "marialStatusId")
    public Integer getMarialStatusId() {return marialStatusId;}

    public void setMarialStatusId(Integer marialStatusId) {
        this.marialStatusId = marialStatusId;
    }

    @Column(name = "CitizenshipId")
    @ManyToOne(targetEntity = Citizenship.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "citizenshipId")
    public Integer getCitizenshipId() {return citizenshipId;}

    public void setCitizenshipId(Integer citizenshipId) {
        this.citizenshipId = citizenshipId;
    }

    @Column(name = "DisabilityId")
    @ManyToOne(targetEntity = Disability.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "disabilityId")
    public Integer getDisabilityId() { return disabilityId; }

    public void setDisabilityId(Integer disabilityId) {
        this.disabilityId = disabilityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                retiree == client.retiree &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(firstname, client.firstname) &&
                Objects.equals(patronymic, client.patronymic) &&
                Objects.equals(dateOfBirth, client.dateOfBirth) &&
                Objects.equals(passportSeries, client.passportSeries) &&
                Objects.equals(passportNumber, client.passportNumber) &&
                Objects.equals(passportIssuedBy, client.passportIssuedBy) &&
                Objects.equals(passportDateOfIssue, client.passportDateOfIssue) &&
                Objects.equals(idNumber, client.idNumber) &&
                Objects.equals(placeOfBirth, client.placeOfBirth) &&
                Objects.equals(addressOfTheActualResidence, client.addressOfTheActualResidence) &&
                Objects.equals(homePhone, client.homePhone) &&
                Objects.equals(mobilePhone, client.mobilePhone) &&
                Objects.equals(email, client.email) &&
                Objects.equals(placeOfWork, client.placeOfWork) &&
                Objects.equals(position, client.position) &&
                Objects.equals(placeOfResidence, client.placeOfResidence) &&
                Objects.equals(monthlyIncome, client.monthlyIncome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, firstname, patronymic, dateOfBirth, passportSeries, passportNumber, passportIssuedBy, passportDateOfIssue, idNumber, placeOfBirth, addressOfTheActualResidence, homePhone, mobilePhone, email, placeOfWork, position, placeOfResidence, retiree, monthlyIncome);
    }
}
