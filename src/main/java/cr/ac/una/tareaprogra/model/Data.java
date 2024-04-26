package cr.ac.una.tareaprogra.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cr.ac.una.tareaprogra.util.AppContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PC
 */
public class Data {

    public Data() {
    }

    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private static final String JSON_FILE_PATH_Account = System.getProperty("user.dir")+"\\Cooperativa\\data\\account.json";
    private static final String JSON_FILE_PATH_AccountAssociate = System.getProperty("user.dir")+"\\Cooperativa\\data\\accountAssociate.json";
    private static final String JSON_FILE_PATH_MailBoxDeposit = System.getProperty("user.dir")+"\\Cooperativa\\data\\mailBoxDeposit.json";
    private static final String JSON_FILE_PATH_Movements = System.getProperty("user.dir")+"\\Cooperativa\\data\\movements.json";
    private static final String JSON_FILE_PATH_Associate = System.getProperty("user.dir")+"\\Cooperativa\\data\\associate.json";
    private static final String JSON_FILE_PATH_Cooperative = System.getProperty("user.dir")+"\\Cooperativa\\data\\cooperative.json";

    //Funcion para iniciar la carga de datos o creacion
    public void startData() throws IOException {
        String cooperativaPath =  System.getProperty("user.dir")+"\\Cooperativa";
        File cooperativaFolder = new File(cooperativaPath);

        if (!cooperativaFolder.exists()) {
            cooperativaFolder.mkdirs();
        }

        String cooperativaDataPath = System.getProperty("user.dir")+"\\Cooperativa\\data";
        File cooperativaData = new File(cooperativaDataPath);
        if (!cooperativaData.exists()) {
            cooperativaData.mkdirs();
        }

        File contextDataFile = new File(JSON_FILE_PATH_Associate);
        if (!contextDataFile.exists()) {

            createObservableLists();

        } else {
            chargeAppContext();
        }
    }

        //Funcion para cargar el appcontext con los datos de los archivos
    private void chargeAppContext() throws IOException {
        ArrayList<AccountAssociate> arrayAccountAssociateList = loadListToJson(JSON_FILE_PATH_AccountAssociate, AccountAssociate.class);
        ArrayList<Associate> arrayAssociateList = loadListToJson(JSON_FILE_PATH_Associate, Associate.class);
        ArrayList<Account> arrayAccountList = loadListToJson(JSON_FILE_PATH_Account, Account.class);
        ArrayList<Movements> arrayMovementsList = loadListToJson(JSON_FILE_PATH_Movements, Movements.class);
        ArrayList<MailBoxDeposit> arrayMailBoxDepositList = loadListToJson(JSON_FILE_PATH_MailBoxDeposit, MailBoxDeposit.class);
        ArrayList<Cooperative> arrayCooperativeList = loadListToJson(JSON_FILE_PATH_Cooperative, Cooperative.class);

        ObservableList<Account> accountList = (ObservableList<Account>) FXCollections.observableArrayList(arrayAccountList);
        ObservableList<AccountAssociate> accountAssociateList = (ObservableList<AccountAssociate>) FXCollections.observableArrayList(arrayAccountAssociateList);
        ObservableList<Associate> associateList = (ObservableList<Associate>) FXCollections.observableArrayList(arrayAssociateList);
        ObservableList<Movements> movementsList = (ObservableList<Movements>) FXCollections.observableArrayList(arrayMovementsList);
        ObservableList<MailBoxDeposit> mailBoxDepositList = (ObservableList<MailBoxDeposit>) FXCollections.observableArrayList(arrayMailBoxDepositList);
        ObservableList<Cooperative> cooperativeList = (ObservableList<Cooperative>) FXCollections.observableArrayList(arrayCooperativeList);

        AppContext.getInstance().set("newMailBoxDeposit", mailBoxDepositList);
        AppContext.getInstance().set("newAccount", accountList);
        AppContext.getInstance().set("newAccountAssociate", accountAssociateList);
        AppContext.getInstance().set("newAssociate", associateList);
        AppContext.getInstance().set("newMovement", movementsList);
        AppContext.getInstance().set("newCooperative", cooperativeList);
    }

    //Funcion para leer los datos del .json y pasarlo a una arraylist
    private <T> ArrayList<T> loadListToJson(String route, Class<T> classObject) throws IOException {
             File file = new File(route);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try {
            ArrayList<T> list = objectMapper.readValue(new File(route), objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, classObject));
            return list;
        } catch (Exception e) {
            throw new IOException("Error al cargar las listas desde JSON: " + e.getMessage(), e);
        }
    }

    //Funcion para guardar las listas en arrayList
    public void safeLists() {

        ObservableList<Account> accountList = (ObservableList<Account>) AppContext.getInstance().get("newAccount");
        ArrayList<Account> arrayAccountList = new ArrayList<>(accountList);
        safeArrayList(JSON_FILE_PATH_Account, arrayAccountList);

        ObservableList<AccountAssociate> accountAssociateList = (ObservableList<AccountAssociate>) AppContext.getInstance().get("newAccountAssociate");
        ArrayList<AccountAssociate> arrayAccountAssociateList = new ArrayList<>(accountAssociateList);
        safeArrayList(JSON_FILE_PATH_AccountAssociate, arrayAccountAssociateList);

        ObservableList<Associate> associateList = (ObservableList<Associate>) AppContext.getInstance().get("newAssociate");
        ArrayList<Associate> arrayAssociateList = new ArrayList<>(associateList);
        safeArrayList(JSON_FILE_PATH_Associate, arrayAssociateList);

        ObservableList<Movements> movementsList = (ObservableList<Movements>) AppContext.getInstance().get("newMovement");
        ArrayList<Movements> arrayMovementsList = new ArrayList<>(movementsList);
        safeArrayList(JSON_FILE_PATH_Movements, arrayMovementsList);

        ObservableList<MailBoxDeposit> mailBoxDepositList = (ObservableList<MailBoxDeposit>) AppContext.getInstance().get("newMailBoxDeposit");
        ArrayList<MailBoxDeposit> arrayMailBoxDepositList = new ArrayList<>(mailBoxDepositList);
        safeArrayList(JSON_FILE_PATH_MailBoxDeposit, arrayMailBoxDepositList);

        ObservableList<Cooperative> cooperativeList = (ObservableList<Cooperative>) AppContext.getInstance().get("newCooperative");
        ArrayList<Cooperative> arrayCooperativeList = new ArrayList<>(cooperativeList);
        safeArrayList(JSON_FILE_PATH_Cooperative, arrayCooperativeList);
    }
    //En caso de que falle por alguna razon el cargar los datos esta funcion las crea
    private void createObservableLists() {
        ObservableList<Account> accountList = FXCollections.observableArrayList();
        ObservableList<AccountAssociate> accountAssociateList = FXCollections.observableArrayList();
        ObservableList<Associate> associateList = FXCollections.observableArrayList();
        ObservableList<Movements> movementsList = FXCollections.observableArrayList();
        ObservableList<MailBoxDeposit> mailBoxDepositList = FXCollections.observableArrayList();
        ObservableList<Cooperative> cooperativeList = FXCollections.observableArrayList();

        Cooperative cooperative = new Cooperative("COOPETOY", "cr/ac/una/tareaprogra/resources/logo.png");
        cooperativeList.add(cooperative);

        AppContext.getInstance().set("newMailBoxDeposit", mailBoxDepositList);
        AppContext.getInstance().set("newAccount", accountList);
        AppContext.getInstance().set("newAccountAssociate", accountAssociateList);
        AppContext.getInstance().set("newAssociate", associateList);
        AppContext.getInstance().set("newMovement", movementsList);
        AppContext.getInstance().set("newCooperative", cooperativeList);
    }

        //Funcion para guardar los arrayList en .json
    private void safeArrayList(String Route, ArrayList<?> list) {
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.writeValue(new File(Route), list);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al guardar" + Route);
        }
    }

}
