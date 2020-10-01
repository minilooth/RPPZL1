package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.models.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EditCustomer implements Initializable {
    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField patronymicTextField;

    @FXML
    private DatePicker dateOfBirthDatePicker;

    @FXML
    private TextField passportSeriesTextField;

    @FXML
    private TextField passportNumberTextField;

    @FXML
    private TextField passportIssuedByTextField;

    @FXML
    private DatePicker passportDateOfIssueDatePicker;

    @FXML
    private TextField idNumberTextField;

    @FXML
    private TextField placeOfBirthTextField;

    @FXML
    private TextField addressOfTheActualResidenceTextField;

    @FXML
    private TextField homePhoneTextField;

    @FXML
    private TextField mobilePhoneTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField placeOfWorkTextField;

    @FXML
    private TextField positionTextField;

    @FXML
    private TextField placeOfResidenceTextField;

    @FXML
    private CheckBox retireeCheckBox;

    @FXML
    private TextField monthlyIncomeTextField;

    @FXML
    private ComboBox<String> cityOfActualResidenceComboBox;

    @FXML
    private ComboBox<String> marialStatusComboBox;

    @FXML
    private ComboBox<String> citizenshipComboBox;

    @FXML
    private ComboBox<String> disabilityComboBox;

    @FXML
    private Button Button_cancel;

    @FXML
    private Button Button_editCustomer;

    private Client client;
    private boolean isUpdated;

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean getIsUpdated() {
        return this.isUpdated;
    }

    public Client getClient() {
        return this.client;
    }

    @FXML
    void initialize() {
        isUpdated = false;

        ObservableList<String> cityOfActualResidenceList = FXCollections.observableArrayList("Выберите из списка");
        for (var cityOfActualResidence : getEntityList(Cityofactualresidence.class)) {
            cityOfActualResidenceList.add(cityOfActualResidence.getCityName());
        }

        ObservableList<String> marialStatusList = FXCollections.observableArrayList("Выберите из списка");
        for(var marialStatus : getEntityList(Marialstatus.class)) {
            marialStatusList.add(marialStatus.getStatusName());
        }

        ObservableList<String> citizenshipList = FXCollections.observableArrayList("Выберите из списка");
        for(var citizenship : getEntityList(Citizenship.class)) {
            citizenshipList.add(citizenship.getCitizenshipName());
        }

        ObservableList<String> disabilityList = FXCollections.observableArrayList("Выберите из списка");
        for(var disability : getEntityList(Disability.class)) {
            disabilityList.add(disability.getDisabilityName());
        }

        cityOfActualResidenceComboBox.setItems(cityOfActualResidenceList);
        marialStatusComboBox.setItems(marialStatusList);
        citizenshipComboBox.setItems(citizenshipList);
        disabilityComboBox.setItems(disabilityList);

        firstnameTextField.setText(client.getFirstname());
        lastnameTextField.setText(client.getSurname());
        patronymicTextField.setText(client.getFirstname());
        dateOfBirthDatePicker.setValue(client.getDateOfBirth().toLocalDate());
        passportSeriesTextField.setText(client.getPassportSeries());
        passportNumberTextField.setText(client.getPassportNumber());
        passportIssuedByTextField.setText(client.getPassportIssuedBy());
        passportDateOfIssueDatePicker.setValue(client.getPassportDateOfIssue().toLocalDate());
        idNumberTextField.setText(client.getIdNumber());
        placeOfBirthTextField.setText(client.getPlaceOfBirth());
        addressOfTheActualResidenceTextField.setText(client.getAddressOfTheActualResidence());
        homePhoneTextField.setText(client.getHomePhone());
        mobilePhoneTextField.setText(client.getMobilePhone());
        emailTextField.setText(client.getEmail());
        placeOfWorkTextField.setText(client.getPlaceOfWork());
        positionTextField.setText(client.getPosition());
        placeOfResidenceTextField.setText(client.getPlaceOfResidence());
        retireeCheckBox.setSelected(Integer.valueOf(Integer.parseInt(Byte.valueOf(client.getRetiree()).toString())).equals(1));
        monthlyIncomeTextField.setText(client.getMonthlyIncome() == null ? "" : client.getMonthlyIncome().toString());
        cityOfActualResidenceComboBox.getSelectionModel().select(client.getCityOfActualResidenceId());
        marialStatusComboBox.getSelectionModel().select(client.getMarialStatusId());
        citizenshipComboBox.getSelectionModel().select(client.getCitizenshipId());
        disabilityComboBox.getSelectionModel().select(client.getDisabilityId());
    }

    private <T> List<T> getEntityList(Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = Main.getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        CriteriaQuery<T> all = criteriaQuery.select(root);

        TypedQuery<T> allQuery = Main.getSession().createQuery(all);
        return allQuery.getResultList();
    }

    @FXML
    private void buttonCancelOnAction() {
        Stage stage = (Stage) Button_cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void buttonEditClientOnAction() {
        if (editCustomer()) {
            isUpdated = true;
            Stage stage = (Stage) Button_cancel.getScene().getWindow();
            stage.close();
        }
    }

    private boolean editCustomer() {
        Client editClient = new Client();

        try {
            if (firstnameTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Имя не должно быть пустым!");
            }
            if (!firstnameTextField.getText().matches("^[A-Za-zА-Яа-я]+$")) {
                throw new IllegalArgumentException("Имя должно состоять только из букв!");
            }
            if (firstnameTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Имя не должно превышать больше 50 символов!");
            }
            editClient.setFirstname(firstnameTextField.getText());

            if (lastnameTextField.getText().length() == 0 ) {
                throw new IllegalArgumentException("Фамилия не должна быть пуста!");
            }
            if (!lastnameTextField.getText().matches("^[A-Za-zА-Яа-я]+$")) {
                throw new IllegalArgumentException("Фамилия должна состоять только из букв!");
            }
            if (lastnameTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Фамилия не должна превышать больше 50 символов!");
            }
            editClient.setSurname(lastnameTextField.getText());

            if (patronymicTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Отчество не должно быть пустым!");
            }
            if (!patronymicTextField.getText().matches("^[A-Za-zА-Яа-я]+$")) {
                throw new IllegalArgumentException("Отчество должно состоять только из букв!");
            }
            if (patronymicTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Отчество не должно превышать больше 50 символов!");
            }
            editClient.setPatronymic(patronymicTextField.getText());

            if (dateOfBirthDatePicker.getValue() == null) {
                throw new IllegalArgumentException("Дата рождения не выбрана!");
            }
            if (Date.valueOf(dateOfBirthDatePicker.getValue()).before(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1900")) ||
                    Date.valueOf(dateOfBirthDatePicker.getValue()).after(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2002"))){
                throw new IllegalArgumentException("Дата рождения должна быть не больше 01.01.2002 и не меньше 01.01.1900!");
            }
            editClient.setDateOfBirth(Date.valueOf(dateOfBirthDatePicker.getValue()));

            if (passportSeriesTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Серия паспорта не должна быть пуста!");
            }
            if (!passportSeriesTextField.getText().matches("^[A-ZА-Я]+$")) {
                throw new IllegalArgumentException("Серия паспорта должна состоять только из заглавных букв!");
            }
            if (passportSeriesTextField.getText().length() > 2) {
                throw new IllegalArgumentException("Серия паспорта не должна превышать 2 символов!");
            }
            editClient.setPassportSeries(passportSeriesTextField.getText());

            if (passportNumberTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Номер паспорта не может быть пустым!");
            }
            if (!passportNumberTextField.getText().matches("^[0-9]+$")) {
                throw new IllegalArgumentException("Номер паспорта должен состоять только из цифр!");
            }
            if (passportNumberTextField.getText().length() > 7) {
                throw new IllegalArgumentException("Номер паспорта не должен превышать 7 символов!");
            }
            editClient.setPassportNumber(passportNumberTextField.getText());

            if (getEntityList(Client.class).stream().filter(item -> (item.getPassportSeries() + item.getPassportNumber().toString()).equals(passportSeriesTextField.getText() + passportNumberTextField.getText())).findAny().orElse(null) != null) {
                throw new IllegalArgumentException("Клиент с таким паспортом уже существует!");
            }

            if (passportIssuedByTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Поле кем выдан паспорт не должно быть пустым!");
            }
            if (!passportIssuedByTextField.getText().matches("^[A-Za-zА-Яа-я. ]+$")) {
                throw new IllegalArgumentException("Поле кем выдан паспорт должно состоять только из букв символа . и пробела!");
            }
            if (passportIssuedByTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Поле кем выдан паспорт не должно превышать 50 символов!");
            }
            editClient.setPassportIssuedBy(passportIssuedByTextField.getText());

            if (passportDateOfIssueDatePicker.getValue() == null) {
                throw new IllegalArgumentException("Дата выдачи паспорта не выбрана!");
            }
            if (Date.valueOf(passportDateOfIssueDatePicker.getValue()).before(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1900")) ||
                    Date.valueOf(passportDateOfIssueDatePicker.getValue()).after(Date.valueOf(LocalDate.now()))) {
                throw new IllegalArgumentException("Дата выдачи паспорта должна быть не больше 01.01.2002 и не меньше " + new SimpleDateFormat("dd.MM.yyyy").format(Date.valueOf(LocalDate.now())));
            }
            editClient.setPassportDateOfIssue(Date.valueOf(passportDateOfIssueDatePicker.getValue()));

            if (idNumberTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Идентификационный номер не может быть пустым!");
            }
            if (!idNumberTextField.getText().matches("^[A-Z0-9]+$")) {
                throw new IllegalArgumentException("Идентификационный номер должен состоять только из заглавных букв и цифр!");
            }
            if (idNumberTextField.getText().length() > 18) {
                throw new IllegalArgumentException("Идентификационный номер не должен превышать 18 символов!");
            }
            if (getEntityList(Client.class).stream().filter(item -> item.getIdNumber().toString().equals(idNumberTextField.getText())).findAny().orElse(null) != null ) {
                throw new IllegalArgumentException("Клиент с таким идентификационным номером уже существует!");
            }
            editClient.setIdNumber(idNumberTextField.getText());

            if (placeOfBirthTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Место рождения не может быть пустым!");
            }
            if (!placeOfBirthTextField.getText().matches("^[A-Za-zА-Яа-я. ]+$")) {
                throw new IllegalArgumentException("Место рождения должно состоять только из букв, символа . и пробела!");
            }
            if (placeOfBirthTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Место рождения не должно превышать 50 символов!");
            }
            editClient.setPlaceOfBirth(placeOfBirthTextField.getText());

            if (addressOfTheActualResidenceTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Адрес фактического проживания не может быть пустым!");
            }
            if (!addressOfTheActualResidenceTextField.getText().matches("^[A-Za-zА-Яа-я0-9. ]+$")) {
                throw new IllegalArgumentException("Адрес фактического проживания должен состоять только из букв, символа . и пробела!");
            }
            if (addressOfTheActualResidenceTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Адрес фактического проживания не должен превышать 50 символов!");
            }
            editClient.setAddressOfTheActualResidence(addressOfTheActualResidenceTextField.getText());

            if (homePhoneTextField.getText() != null && homePhoneTextField.getText().length() != 0) {
                if (!homePhoneTextField.getText().matches("^[+]{1}[3]{1}[7]{1}[5]{1}[(]{1}[0-9]{2}[)]{1}[-\\s/0-9]{9}$")) {
                    throw new IllegalArgumentException("Домашний телефон должен быть вида +375(12)345-56-78!");
                }
                if (homePhoneTextField.getText().length() > 18) {
                    throw new IllegalArgumentException("Домашний телефон должен состоять из 18 символов!");
                }
                editClient.setHomePhone(homePhoneTextField.getText());
            }

            if (mobilePhoneTextField.getText() != null && mobilePhoneTextField.getText().length() != 0) {
                if (!mobilePhoneTextField.getText().matches("^[+]{1}[3]{1}[7]{1}[5]{1}[(]{1}[0-9]{2}[)]{1}[-\\s/0-9]{9}$")) {
                    throw new IllegalArgumentException("Мобильный телефон должен быть вида +375(12)345-56-78!");
                }
                if (mobilePhoneTextField.getText().length() > 18) {
                    throw new IllegalArgumentException("Мобильный телефон должен состоять из 18 символов!");
                }
                editClient.setMobilePhone(mobilePhoneTextField.getText());
            }

            if (emailTextField.getText() != null && emailTextField.getText().length() != 0) {
                if (!emailTextField.getText().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    throw new IllegalArgumentException("Эмейл введен в неверном формате!");
                }
                if (emailTextField.getText().length() > 25) {
                    throw new IllegalArgumentException("Эмейл не должен превышать 25 символов!");
                }
                editClient.setEmail(emailTextField.getText());
            }

            if (placeOfWorkTextField.getText() != null && placeOfWorkTextField.getText().length() != 0) {
                if (!placeOfWorkTextField.getText().matches("^[A-Za-zА-Яа-я0-9.]+$")) {
                    throw new IllegalArgumentException("Место работы должно состоять только из букв, символа . и пробела!");
                }
                if (placeOfWorkTextField.getText().length() > 50) {
                    throw new IllegalArgumentException("Место работы не должно превышать 50 символов!");
                }
                editClient.setPlaceOfWork(placeOfWorkTextField.getText());
            }

            if (positionTextField.getText() != null && positionTextField.getText().length() != 0) {
                if (!positionTextField.getText().matches("^[A-Za-zА-Яа-я0-9. ]+$")) {
                    throw new IllegalArgumentException("Должность должна состоять только из букв, цифр, символа . и пробела!");
                }
                if (positionTextField.getText().length() > 50) {
                    throw new IllegalArgumentException("Должность не должна превышать 50 символов!");
                }
                editClient.setPosition(positionTextField.getText());
            }

            if (placeOfResidenceTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Адрес прописки не должен быть пустым!");
            }
            if (!placeOfResidenceTextField.getText().matches("^[A-Za-zА-Яа-я0-9. ]+$")) {
                throw new IllegalArgumentException("Адрес прописки должен состоять только из букв, цифр, символа . и пробела!");
            }
            if (placeOfResidenceTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Адрес прописки не должен превышать 50 символов!");
            }
            editClient.setPlaceOfResidence(placeOfResidenceTextField.getText());

            if (monthlyIncomeTextField.getText().length() != 0) {
                if (!monthlyIncomeTextField.getText().matches("^[0-9]+$")) {
                    throw new IllegalArgumentException("Месячный доход должен состоять только из цифр!");
                }
                editClient.setMonthlyIncome(Integer.parseInt(monthlyIncomeTextField.getText()));
            }

            if (cityOfActualResidenceComboBox.getSelectionModel().getSelectedIndex() == 0) {
                throw new IllegalArgumentException("Город фактического проживания не выбран!");
            }
            editClient.setCityOfActualResidenceId(cityOfActualResidenceComboBox.getSelectionModel().getSelectedIndex());

            if (marialStatusComboBox.getSelectionModel().getSelectedIndex() == 0) {
                throw new IllegalArgumentException("Семейное положение не выбрано!");
            }
            editClient.setMarialStatusId(marialStatusComboBox.getSelectionModel().getSelectedIndex());

            if (citizenshipComboBox.getSelectionModel().getSelectedIndex() == 0) {
                throw new IllegalArgumentException("Гражданство не выбрано!");
            }
            editClient.setCitizenshipId(citizenshipComboBox.getSelectionModel().getSelectedIndex());

            if (disabilityComboBox.getSelectionModel().getSelectedIndex() == 0) {
                throw new IllegalArgumentException("Инвалидность не выбрана!");
            }
            editClient.setDisabilityId(disabilityComboBox.getSelectionModel().getSelectedIndex());

            editClient.setRetiree(retireeCheckBox.isSelected() ? Byte.valueOf("1") : Byte.valueOf("0"));
            editClient.setId(client.getId());

            Main.getSession().evict(client);
//            Main.getSession().beginTransaction();
            Main.getSession().update(editClient);
//            Main.getSession().getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Клиент успешно обновлен!");
            return true;
        }
        catch (IllegalArgumentException | ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
