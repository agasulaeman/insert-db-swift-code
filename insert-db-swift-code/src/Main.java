import com.permatabank.insert.swift.bank.insertquery.InsertQuery;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        InsertQuery insertQuery = new InsertQuery();
        try {
            insertQuery.insertDataListSwiftBank();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}