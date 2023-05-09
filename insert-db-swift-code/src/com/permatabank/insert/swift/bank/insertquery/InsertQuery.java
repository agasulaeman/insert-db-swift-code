package com.permatabank.insert.swift.bank.insertquery;

import com.permatabank.insert.swift.bank.connectdb.ConnectDb;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class InsertQuery {


    private ConnectDb conn = new ConnectDb();
    public void insertDataListSwiftBank()  throws IOException, SQLException {

        String queryInsert = "INSERT INTO PMOB.SWIFT_BANK(REC_ID,CREATED_DATE,SWIFT_CODE,BRANCH_CODE,BANK_NAME,ADDRESS_LINE1,ADDRESS_LINE2,ADDRESS_LINE3,ADDRESS_LINE4,CITY_NAME,COUNTRY_CODE,COUNTRY_NAME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        int count = 0;
        PreparedStatement pstmt = null;


        try {
            pstmt = conn.connectDatabase().prepareStatement(queryInsert);

            FileInputStream fstream = new FileInputStream("/Users/agasulaeman/downloads/swift_master_202304.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            ArrayList list = new ArrayList();
            String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
            while ((strLine = br.readLine()) != null) {

                list.add(strLine);
            }

            Iterator iterator;
            for (iterator = list.iterator(); iterator.hasNext(); ) {
                String str = iterator.next().toString();
                String[] splitSt = str.split("\t");
                System.out.println(str);

                String
                        //recId = "",
                        createdDate = "",
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

                for (int i = 5; i < splitSt.length; i++) {
//                  recId = splitSt[0];
                    createdDate = splitSt[1];
                    swiftCode = splitSt[2];
                    branchCode = splitSt[3];
                    bankName = splitSt[4];
                    addressLine1 = splitSt[10];
                    addressLine2 = splitSt[11];
                    addressLine3 = splitSt[12];
                    addressLine4 = splitSt[13];
                    cityName = splitSt[14];
                    countryCode = swiftCode.substring(4, 6);
                    countryName = splitSt[15];

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
