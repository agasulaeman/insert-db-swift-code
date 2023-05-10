package com.permatabank.insert.swift.bank.deletequery;

import com.permatabank.insert.swift.bank.connectdb.ConnectDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteQuerySwiftBank {

    ConnectDb connectDb = new ConnectDb();

    public void deleteDatabySwiftCode(String swiftCode) throws SQLException {
        String DELETE_LIST_BANK_BY_SWIFT_CODE = "DELETE FROM PMOB.SWIFT_BANK WHERE CONCAT (SWIFT_CODE,BRANCH_CODE) =? ";

        try {
            PreparedStatement pstmt = connectDb.connectDatabase().prepareStatement(DELETE_LIST_BANK_BY_SWIFT_CODE);

            pstmt.setInt(1, Integer.parseInt(swiftCode));

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDataAll() {
        String DELETE_ALL_DATA_PMOB_SWIFT_BANK = "Truncate Table PMOB.SWIFT_BANK";

        try {
            PreparedStatement pstmt = connectDb.connectDatabase().prepareStatement(DELETE_ALL_DATA_PMOB_SWIFT_BANK);

            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
