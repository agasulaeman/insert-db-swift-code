package com.permatabank.insert.swift.bank.updatequery;

import com.permatabank.insert.swift.bank.connectdb.ConnectDb;

import java.io.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;


public class UpdateQuery {

    private ConnectDb conn = new ConnectDb();
    final static DateTimeFormatter dbTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS");
    public void updateDataSwiftBank
            () throws IOException, ClassNotFoundException {

        String UPDATE_LIST_BANK_BY_SWIFT_CODE = "UPDATE PMOB.SWIFT_BANK SET CREATED_DATE=? ,BANK_NAME=?,ADDRESS_LINE1=?,ADDRESS_LINE2=?,ADDRESS_LINE3=?,ADDRESS_LINE4=?,CITY_NAME=?,COUNTRY_CODE=?,COUNTRY_NAME=? WHERE SWIFT_CODE =? ";
        LocalDateTime dateTimeNow = LocalDateTime.now();

        int count = 0;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            pstmt = conn.connectDatabase().prepareStatement(UPDATE_LIST_BANK_BY_SWIFT_CODE);
            LocalDateTime updateDateTimeDb = LocalDateTime.now();
            FileInputStream fstream = new FileInputStream("/Users/agasulaeman/downloads/swift_master_202304_v2.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            ArrayList list = new ArrayList();

            while ((strLine = br.readLine()) != null) {
                list.add(strLine);
            }
            Iterator iterator;
            for (iterator = list.iterator(); iterator.hasNext(); ) {
                String str = iterator.next().toString();
                String[] splitSt = str.split("\t");
                System.out.println(str);


                String
                        createdDate = dateTimeNow.format(dbTimeFormatter),
                        swiftCode = "",
                        branchCode = "",
                        bankName = "",
                        addressLine1 = "",
                        addressLine2 = "",
                        addressLine3 = "",
                        addressLine4 = "",
                        cityName = "",
                        countryCode = "",
                        countryName = "";

                for (int i = 1; i < splitSt.length; i++) {

                    createdDate = createdDate;
                    swiftCode = splitSt[2];
                    branchCode = splitSt[3];
                    bankName = splitSt[4];
                    addressLine1 = splitSt[10];
                    addressLine2 = splitSt[11];
                    addressLine3 = splitSt[12];
                    addressLine4 = splitSt[13];
                    cityName = splitSt[6];
                    countryCode = swiftCode.substring(4, 6);
                    countryName = splitSt[15];

                    pstmt.setString(1,createdDate);
                    pstmt.setString(2,bankName);
                    pstmt.setString(3,addressLine1);
                    pstmt.setString(4,addressLine2);
                    pstmt.setString(5,addressLine3);
                    pstmt.setString(6,addressLine4);
                    pstmt.setString(7,cityName);
                    pstmt.setString(8,countryCode);
                    pstmt.setString(9,countryName);
                    pstmt.setString(10,swiftCode);
                     pstmt.addBatch();
                }

                boolean insert = pstmt.execute();
            }
            conn.connectDatabase().close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
