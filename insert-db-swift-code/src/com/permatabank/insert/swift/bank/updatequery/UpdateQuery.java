package com.permatabank.insert.swift.bank.updatequery;

import com.permatabank.insert.swift.bank.connectdb.ConnectDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UpdateQuery {

    ConnectDb connectDb = new ConnectDb();
    private void updateDataSwiftBank
            (String swiftCode,
             String bankName,
             String addressLine1,
             String addressLine2,
             String addressLine3,
             String addressLine4,
             String cityName,
             String countryCode,
             String countryName) {
        String UPDATE_LIST_BANK_BY_SWIFT_CODE = "UPDATE PMOB.SWIFT_BANK SET BANK_NAME=?,ADDRESS_LINE1=?,ADDRESS_LINE2=?,ADDRESS_LINE3=?,ADDRESS_LINE4=?,CITY_NAME=?,COUNTRY_CODE=?,COUNTRY_NAME=? WHERE SWIFT_CODE =? ";

        try {
     PreparedStatement pstmt = connectDb.connectDatabase().prepareStatement(UPDATE_LIST_BANK_BY_SWIFT_CODE);

            pstmt.setString(1, bankName);
            pstmt.setString(2, addressLine1);
            pstmt.setString(3, addressLine2);
            pstmt.setString(4, addressLine3);
            pstmt.setString(5, addressLine4);
            pstmt.setString(6, cityName);
            pstmt.setString(7, countryCode);
            pstmt.setString(8, countryName);
            pstmt.setString(9, swiftCode);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(e.getMessage());
        }
    }
}
