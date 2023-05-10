import com.permatabank.insert.swift.bank.updatequery.UpdateQuery;

import java.io.IOException;

public class MainUpdateSwiftCode {
    public static void main(String[] args) {
        UpdateQuery updateQuery = new UpdateQuery();

        try {
            updateQuery.updateDataSwiftBank();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
