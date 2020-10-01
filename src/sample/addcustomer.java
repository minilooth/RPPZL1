package sample;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.Date;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.time.Instant;
        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.util.List;
        import java.util.Locale;
        import java.util.ResourceBundle;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.stage.Stage;
        import sample.models.*;

        import javax.imageio.IIOException;
        import javax.persistence.TypedQuery;
        import javax.persistence.criteria.CriteriaBuilder;
        import javax.persistence.criteria.CriteriaQuery;
        import javax.persistence.criteria.Root;
        import javax.swing.*;

public class addcustomer {

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
    private Button Button_back;

    @FXML
    private Button Button_addClient;

    @FXML
    private Button Button_clearForm;


    @FXML
    void initialize() {
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

        cityOfActualResidenceComboBox.getSelectionModel().selectFirst();
        marialStatusComboBox.getSelectionModel().selectFirst();
        citizenshipComboBox.getSelectionModel().selectFirst();
        disabilityComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void buttonClearOnAction() {
        firstnameTextField.setText("");
        lastnameTextField.setText("");
        patronymicTextField.setText("");
        dateOfBirthDatePicker.getEditor().clear();
        passportNumberTextField.setText("");
        passportSeriesTextField.setText("");
        passportIssuedByTextField.setText("");
        passportDateOfIssueDatePicker.getEditor().clear();
        idNumberTextField.setText("");
        placeOfBirthTextField.setText("");
        addressOfTheActualResidenceTextField.setText("");
        homePhoneTextField.setText("");
        mobilePhoneTextField.setText("");
        emailTextField.setText("");
        positionTextField.setText("");
        retireeCheckBox.setSelected(false);
        placeOfWorkTextField.setText("");
        monthlyIncomeTextField.setText("");
        cityOfActualResidenceComboBox.getSelectionModel().select(0);
        marialStatusComboBox.getSelectionModel().select(0);
        citizenshipComboBox.getSelectionModel().select(0);
        disabilityComboBox.getSelectionModel().select(0);
    }

    private void addClient() {
        Client client = new Client();
        Main.getSession().beginTransaction();


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
            client.setFirstname(firstnameTextField.getText());

            if (lastnameTextField.getText().length() == 0 ) {
                throw new IllegalArgumentException("Фамилия не должна быть пуста!");
            }
            if (!lastnameTextField.getText().matches("^[A-Za-zА-Яа-я]+$")) {
                throw new IllegalArgumentException("Фамилия должна состоять только из букв!");
            }
            if (lastnameTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Фамилия не должна превышать больше 50 символов!");
            }
            client.setSurname(lastnameTextField.getText());

            if (patronymicTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Отчество не должно быть пустым!");
            }
            if (!patronymicTextField.getText().matches("^[A-Za-zА-Яа-я]+$")) {
                throw new IllegalArgumentException("Отчество должно состоять только из букв!");
            }
            if (patronymicTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Отчество не должно превышать больше 50 символов!");
            }
            client.setPatronymic(patronymicTextField.getText());

            if (dateOfBirthDatePicker.getValue() == null) {
                throw new IllegalArgumentException("Дата рождения не выбрана!");
            }
            if (Date.valueOf(dateOfBirthDatePicker.getValue()).before(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1900")) ||
                Date.valueOf(dateOfBirthDatePicker.getValue()).after(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2002"))){
                throw new IllegalArgumentException("Дата рождения должна быть не больше 01.01.2002 и не меньше 01.01.1900!");
            }
            client.setDateOfBirth(Date.valueOf(dateOfBirthDatePicker.getValue()));

            if (passportSeriesTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Серия паспорта не должна быть пуста!");
            }
            if (!passportSeriesTextField.getText().matches("^[A-ZА-Я]+$")) {
                throw new IllegalArgumentException("Серия паспорта должна состоять только из заглавных букв!");
            }
            if (passportSeriesTextField.getText().length() > 2) {
                throw new IllegalArgumentException("Серия паспорта не должна превышать 2 символов!");
            }
            client.setPassportSeries(passportSeriesTextField.getText());

            if (passportNumberTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Номер паспорта не может быть пустым!");
            }
            if (!passportNumberTextField.getText().matches("^[0-9]+$")) {
                throw new IllegalArgumentException("Номер паспорта должен состоять только из цифр!");
            }
            if (passportNumberTextField.getText().length() > 7) {
                throw new IllegalArgumentException("Номер паспорта не должен превышать 7 символов!");
            }
            client.setPassportNumber(passportNumberTextField.getText());

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
            client.setPassportIssuedBy(passportIssuedByTextField.getText());

            if (passportDateOfIssueDatePicker.getValue() == null) {
                throw new IllegalArgumentException("Дата выдачи паспорта не выбрана!");
            }
            if (Date.valueOf(passportDateOfIssueDatePicker.getValue()).before(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1900")) ||
                Date.valueOf(passportDateOfIssueDatePicker.getValue()).after(Date.valueOf(LocalDate.now()))) {
                throw new IllegalArgumentException("Дата выдачи паспорта должна быть не больше 01.01.2002 и не меньше " + new SimpleDateFormat("dd.MM.yyyy").format(Date.valueOf(LocalDate.now())));
            }
            client.setPassportDateOfIssue(Date.valueOf(passportDateOfIssueDatePicker.getValue()));

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
            client.setIdNumber(idNumberTextField.getText());

            if (placeOfBirthTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Место рождения не может быть пустым!");
            }
            if (!placeOfBirthTextField.getText().matches("^[A-Za-zА-Яа-я. ]+$")) {
                throw new IllegalArgumentException("Место рождения должно состоять только из букв, символа . и пробела!");
            }
            if (placeOfBirthTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Место рождения не должно превышать 50 символов!");
            }
            client.setPlaceOfBirth(placeOfBirthTextField.getText());

            if (addressOfTheActualResidenceTextField.getText().length() == 0) {
                throw new IllegalArgumentException("Адрес фактического проживания не может быть пустым!");
            }
            if (!addressOfTheActualResidenceTextField.getText().matches("^[A-Za-zА-Яа-я0-9. ]+$")) {
                throw new IllegalArgumentException("Адрес фактического проживания должен состоять только из букв, цифр, символа . и пробела!");
            }
            if (addressOfTheActualResidenceTextField.getText().length() > 50) {
                throw new IllegalArgumentException("Адрес фактического проживания не должен превышать 50 символов!");
            }
            client.setAddressOfTheActualResidence(addressOfTheActualResidenceTextField.getText());

            if (homePhoneTextField.getText().length() != 0) {
                if (!homePhoneTextField.getText().matches("^[+]{1}[3]{1}[7]{1}[5]{1}[(]{1}[0-9]{2}[)]{1}[-\\s/0-9]{9}$")) {
                    throw new IllegalArgumentException("Домашний телефон должен быть вида +375(12)345-56-78!");
                }
                if (homePhoneTextField.getText().length() > 18) {
                    throw new IllegalArgumentException("Домашний телефон должен состоять из 18 символов!");
                }
                client.setHomePhone(homePhoneTextField.getText());
            }

            if (mobilePhoneTextField.getText().length() != 0) {
                if (!mobilePhoneTextField.getText().matches("^[+]{1}[3]{1}[7]{1}[5]{1}[(]{1}[0-9]{2}[)]{1}[-\\s/0-9]{9}$")) {
                    throw new IllegalArgumentException("Мобильный телефон должен быть вида +375(12)345-56-78!");
                }
                if (mobilePhoneTextField.getText().length() > 18) {
                    throw new IllegalArgumentException("Мобильный телефон должен состоять из 18 символов!");
                }
                client.setMobilePhone(mobilePhoneTextField.getText());
            }

            if (emailTextField.getText().length() != 0) {
                if (!emailTextField.getText().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    throw new IllegalArgumentException("Эмейл введен в неверном формате!");
                }
                if (emailTextField.getText().length() > 25) {
                    throw new IllegalArgumentException("Эмейл не должен превышать 25 символов!");
                }
                client.setEmail(emailTextField.getText());
            }

            if (placeOfWorkTextField.getText().length() != 0) {
                if (!placeOfWorkTextField.getText().matches("^[A-Za-zА-Яа-я0-9.]+$")) {
                    throw new IllegalArgumentException("Место работы должно состоять только из букв, символа . и пробела!");
                }
                if (placeOfWorkTextField.getText().length() > 50) {
                    throw new IllegalArgumentException("Место работы не должно превышать 50 символов!");
                }
                client.setPlaceOfWork(placeOfWorkTextField.getText());
            }

            if (positionTextField.getText().length() != 0) {
                if (!positionTextField.getText().matches("^[A-Za-zА-Яа-я0-9. ]+$")) {
                    throw new IllegalArgumentException("Должность должна состоять только из букв, цифр, символа . и пробела!");
                }
                if (positionTextField.getText().length() > 50) {
                    throw new IllegalArgumentException("Должность не должна превышать 50 символов!");
                }
                client.setPosition(positionTextField.getText());
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
            client.setPlaceOfResidence(placeOfResidenceTextField.getText());

            if (monthlyIncomeTextField.getText().length() != 0) {
                if (!monthlyIncomeTextField.getText().matches("^[0-9]+$")) {
                    throw new IllegalArgumentException("Месячный доход должен состоять только из цифр!");
                }
                client.setMonthlyIncome(Integer.parseInt(monthlyIncomeTextField.getText()));
            }

            if (cityOfActualResidenceComboBox.getSelectionModel().getSelectedIndex() == 0) {
                throw new IllegalArgumentException("Город фактического проживания не выбран!");
            }
            client.setCityOfActualResidenceId(cityOfActualResidenceComboBox.getSelectionModel().getSelectedIndex());

            if (marialStatusComboBox.getSelectionModel().getSelectedIndex() == 0) {
                throw new IllegalArgumentException("Семейное положение не выбрано!");
            }
            client.setMarialStatusId(marialStatusComboBox.getSelectionModel().getSelectedIndex());

            if (citizenshipComboBox.getSelectionModel().getSelectedIndex() == 0) {
                throw new IllegalArgumentException("Гражданство не выбрано!");
            }
            client.setCitizenshipId(citizenshipComboBox.getSelectionModel().getSelectedIndex());

            if (disabilityComboBox.getSelectionModel().getSelectedIndex() == 0) {
                throw new IllegalArgumentException("Инвалидность не выбрана!");
            }
            client.setDisabilityId(disabilityComboBox.getSelectionModel().getSelectedIndex());

            client.setRetiree(retireeCheckBox.isSelected() ? Byte.valueOf("1") : Byte.valueOf("0"));

            Main.getSession().clear();
            Main.getSession().save(client);
            Main.getSession().getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Клиент успешно добавлен!");
        }
        catch (IllegalArgumentException | ParseException ex) {
            Main.getSession().getTransaction().rollback();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @FXML
    private void buttonAddClientOnAction() {
        addClient();
    }

    @FXML
    private void buttonBackOnAction() {
        Button_back.getScene().getWindow().hide();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/sample.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private <T> List<T> getEntityList(Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = Main.getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        CriteriaQuery<T> all = criteriaQuery.select(root);

        TypedQuery<T> allQuery = Main.getSession().createQuery(all);
        return allQuery.getResultList();
    }
}

