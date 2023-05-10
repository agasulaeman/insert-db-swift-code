package com.permatabank.insert.swift.bank.insertquery;

import com.permatabank.insert.swift.bank.connectdb.ConnectDb;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class InsertQuery {

    private ConnectDb conn = new ConnectDb();
    final static DateTimeFormatter dbTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSSSSS");
    public void insertDataListSwiftBank()  throws IOException, SQLException {

        String queryInsert = "INSERT INTO PMOB.SWIFT_BANK(CREATED_DATE,SWIFT_CODE,BRANCH_CODE,BANK_NAME,ADDRESS_LINE1,ADDRESS_LINE2,ADDRESS_LINE3,ADDRESS_LINE4,CITY_NAME,COUNTRY_CODE,COUNTRY_NAME) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        int count = 0;
        PreparedStatement pstmt = null;


        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            pstmt = conn.connectDatabase().prepareStatement(queryInsert);

            LocalDateTime dateTimeNow = LocalDateTime.now();
            FileInputStream fstream = new FileInputStream("/Users/agasulaeman/downloads/swift_master_202304.txt");
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
                    pstmt.setString(2,swiftCode);
                    pstmt.setString(3,branchCode);
                    pstmt.setString(4,bankName);
                    pstmt.setString(5,addressLine1);
                    pstmt.setString(6,addressLine2);
                    pstmt.setString(7,addressLine3);
                    pstmt.setString(8,addressLine4);
                    pstmt.setString(9,cityName);
                    pstmt.setString(10,countryCode);
                    pstmt.setString(11,countryName);
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
