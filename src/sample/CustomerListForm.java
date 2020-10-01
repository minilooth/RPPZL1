package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import sample.models.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.*;

public class CustomerListForm {

    private ObservableList<Client> clientsData = FXCollections.observableArrayList();

    @FXML
    private TableView<Client> customersTableView;

    @FXML
    private TableColumn<Client, Integer> idColumn;

    @FXML
    private TableColumn<Client, String> firstnameColumn;

    @FXML
    private TableColumn<Client, String> surnameColumn;

    @FXML
    private TableColumn<Client, String> patronymicColumn;

    @FXML
    private TableColumn<Client, Date> dateOfBirthColumn;

    @FXML
    private TableColumn<Client, String> passportSeriesColumn;

    @FXML
    private TableColumn<Client, String> passportNumberColumn;

    @FXML
    private TableColumn<Client, String> passportIssuedByColumn;

    @FXML
    private TableColumn<Client, String> passportDateOfIssueColumn;

    @FXML
    private TableColumn<Client, String> idNumberColumn;

    @FXML
    private TableColumn<Client, String> placeOfBirthColumn;

    @FXML
    private TableColumn<Client, String> addressOfTheActualResidenceColumn;

    @FXML
    private TableColumn<Client, String> homePhoneColumn;

    @FXML
    private TableColumn<Client, String> mobilePhoneColumn;

    @FXML
    private TableColumn<Client, String> emailColumn;

    @FXML
    private TableColumn<Client, String> placeOfWorkColumn;

    @FXML
    private TableColumn<Client, String> positionColumn;

    @FXML
    private TableColumn<Client, String> placeOfResidenceColumn;

    @FXML
    private TableColumn<Client, String> retireeColumn;

    @FXML
    private TableColumn<Client, String> monthlyIncomeColumn;

    @FXML
    private TableColumn<Client, String> cityOfActualResidenceColumn;

    @FXML
    private TableColumn<Client, String> marialStatusColumn;

    @FXML
    private TableColumn<Client, String> citizenshipColumn;

    @FXML
    private TableColumn<Client, String> disabilityColumn;

    @FXML
    private TableColumn<Client, String> deleteColumn;

    @FXML
    private TableColumn<Client, String> editColumn;

    @FXML
    void initialize() {
        initializeData();

        idColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstname"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("surname"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("patronymic"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Client, Date>("dateOfBirth"));
        passportSeriesColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("passportSeries"));
        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("passportNumber"));
        passportIssuedByColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("passportIssuedBy"));
        passportDateOfIssueColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("passportDateOfIssue"));
        idNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("idNumber"));
        placeOfBirthColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("placeOfBirth"));
        addressOfTheActualResidenceColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("addressOfTheActualResidence"));
        placeOfResidenceColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("placeOfResidence"));

        placeOfWorkColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return client.getPlaceOfWork() == null || client.getPlaceOfWork().equals("") ? "Не указано" : client.getPlaceOfWork();
                    }
            );
        });

        positionColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return client.getPosition() == null || client.getPosition().equals("") ? "Не указано" : client.getPosition();
                    }
            );
        });

        homePhoneColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return client.getHomePhone() == null || client.getHomePhone().equals("") ? "Не указано" : client.getHomePhone();
                    }
            );
        });

        mobilePhoneColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return client.getMobilePhone() == null || client.getMobilePhone().equals("") ? "Не указано" : client.getMobilePhone();
                    }
            );
        });

        emailColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return client.getEmail() == null || client.getEmail().equals("") ? "Не указано" : client.getEmail();
                    }
            );
        });

        monthlyIncomeColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return client.getMonthlyIncome() == null || client.getMonthlyIncome().toString().equals("") ? "Не указано" : client.getMonthlyIncome().toString();
                    }
            );
        });

        retireeColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return client.getRetiree() == Byte.parseByte("1") ? "Да" : "Нет";
                    }
            );
        });

        cityOfActualResidenceColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return Objects.requireNonNull(getEntityList(Cityofactualresidence.class).stream()
                                .filter(cityOfActualResidence -> client.getCityOfActualResidenceId().equals(cityOfActualResidence.getCityId()))
                                .findAny()
                                .orElse(null)).getCityName();
                    }
            );
        });

        marialStatusColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return Objects.requireNonNull(getEntityList(Marialstatus.class).stream()
                                .filter(marialStatus -> client.getMarialStatusId().equals(marialStatus.getStatusId()))
                                .findAny()
                                .orElse(null)).getStatusName();
                    }
            );
        });

        citizenshipColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return Objects.requireNonNull(getEntityList(Citizenship.class).stream()
                                .filter(citizenship -> client.getCitizenshipId().equals(citizenship.getCitizenshipId()))
                                .findAny()
                                .orElse(null)).getCitizenshipName();
                    }
            );
        });

        disabilityColumn.setCellValueFactory(cellData -> {
            Client client = cellData.getValue();
            return Bindings.createStringBinding(
                    () -> {
                        return Objects.requireNonNull(getEntityList(Disability.class).stream()
                                .filter(disability -> client.getDisabilityId().equals(disability.getDisabilityId()))
                                .findAny()
                                .orElse(null)).getDisabilityName();
                    }
            );
        });

        Callback<TableColumn<Client, String>, TableCell<Client, String>> editCellFactory = new Callback<TableColumn<Client, String>, TableCell<Client, String>>() {
            @Override
            public TableCell<Client, String> call(final TableColumn<Client, String> param) {
                final TableCell<Client, String> cell = new TableCell<Client, String>() {
                    final Button button = new Button("Редактировать");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        }
                        else {
                            button.setOnAction(event -> {
                                Client client = getTableView().getItems().get(getIndex());
                                try {
                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("/sample/EditCustomer.fxml"));
                                    Parent root = (Parent) loader.load();

                                    EditCustomer editCustomer = loader.getController();
                                    editCustomer.setClient(client);
                                    editCustomer.initialize();

                                    Stage newWindow = new Stage();
                                    newWindow.setScene(new Scene(root));
                                    newWindow.initModality(Modality.APPLICATION_MODAL);

                                    newWindow.showAndWait();

                                    editCustomer = loader.getController();

                                    if (editCustomer.getIsUpdated()) {
                                        customersTableView.getItems().removeAll(customersTableView.getItems());
                                        initializeData();
                                        customersTableView.setItems(clientsData);
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            setGraphic(button);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        Callback<TableColumn<Client, String>, TableCell<Client, String>> deleteCellFactory = new Callback<TableColumn<Client, String>, TableCell<Client, String>>() {
            @Override
            public TableCell<Client, String> call(final TableColumn<Client, String> param) {
                final TableCell<Client, String> cell = new TableCell<Client, String>() {
                    final Button button = new Button("Удалить");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        }
                        else {
                            button.setOnAction(event -> {
                                Client client = getTableView().getItems().get(getIndex());
                                Main.getSession().beginTransaction();
                                Main.getSession().delete(client);
                                Main.getSession().getTransaction().commit();

                                customersTableView.getItems().removeAll(customersTableView.getItems());
                                initializeData();
                                customersTableView.setItems(clientsData);

                                JOptionPane.showMessageDialog(null, "Клиент успешно удален!");
                            });
                            button.setPrefWidth(100);
                            setGraphic(button);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        deleteColumn.setCellFactory(deleteCellFactory);
        editColumn.setCellFactory(editCellFactory);

        customersTableView.setItems(clientsData);
    }

    private <T> List<T> getEntityList(Class<T> tClass) {
        CriteriaBuilder criteriaBuilder = Main.getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        CriteriaQuery<T> all = criteriaQuery.select(root);

        TypedQuery<T> allQuery = Main.getSession().createQuery(all);
        return allQuery.getResultList();
    }

    private void initializeData() {
        clientsData.addAll(getEntityList(Client.class));
    }
}

