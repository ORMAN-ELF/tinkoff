package com.app;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.app.ConfigDB.*;


/**
 * ExcelFile. Класс представляет собой созданный и заполненный данными excel документ.
 *
 * @version:   0.3 10 марта 2019
 * @Copyright  Наталья
 */

public class ExcelFile {

    private static Boolean internet;
    private static Integer id = 0;

    public static void main(String[] args) throws Exception {


        JSON getJSON = new JSON();
        internet = getJSON.getJson() != null;

        //создание самого excel файла в памяти
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("list_1");

            List<DataModel> dataList = fillData();

            int rowNum = 0;
            deleteFile();

            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Имя");
            row.createCell(1).setCellValue("Фамилия");
            row.createCell(2).setCellValue("Отчество");
            row.createCell(3).setCellValue("Возраст");
            row.createCell(4).setCellValue("Пол");
            row.createCell(5).setCellValue("Дата рождения");
            row.createCell(6).setCellValue("ИНН");
            row.createCell(7).setCellValue("Почтовый индекс");
            row.createCell(8).setCellValue("Страна");
            row.createCell(9).setCellValue("Область");
            row.createCell(10).setCellValue("Город");
            row.createCell(11).setCellValue("Улица");
            row.createCell(12).setCellValue("Дом");
            row.createCell(13).setCellValue("Квартира");

            // заполняем лист данными
            for (DataModel dataModel : dataList) {
                createSheetHeader(sheet, ++rowNum, dataModel);
            }

            try (FileOutputStream out = new FileOutputStream(new File("../tinkoff/src/main/1.xls"))) {
                workbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file = new File("src/main/1.xls");
        System.out.println("Файл создан. Путь: "+ file.getAbsolutePath());
    }


    // данными  из dataModel созданного в памяти Excel файла
    private static void createSheetHeader(HSSFSheet sheet, int rowNum, DataModel dataModel) throws Exception {
        var row = sheet.createRow(rowNum);

        if (internet){

            DataAPI dataAPI = new DataAPI();
            dataAPI.getDataAPI();

            row.createCell(0).setCellValue(dataAPI.nameAPI);
            row.createCell(1).setCellValue(dataAPI.surnameAPI);
            row.createCell(2).setCellValue(dataAPI.middlenameAPI);
            row.createCell(4).setCellValue(dataAPI.genderAPI);
            row.createCell(6).setCellValue(dataAPI.inn);
            row.createCell(7).setCellValue(dataAPI.zipAPI);
            row.createCell(8).setCellValue(dataAPI.country);
            row.createCell(9).setCellValue(dataAPI.region);
            row.createCell(10).setCellValue(dataAPI.cityAPI);
            row.createCell(11).setCellValue(dataAPI.streetAPI);
            row.createCell(12).setCellValue(dataAPI.house);
            row.createCell(13).setCellValue(dataAPI.room);

        } else {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String dataBD ="SELECT * FROM persons";

            try (Statement stmt = conn.createStatement()){

                try (ResultSet rs = stmt.executeQuery(dataBD)) {
                    rs.next();
                    System.out.println(rs.getObject(1, Boolean.class));


                    if (rs.wasNull()){
                        row.createCell(0).setCellValue(dataModel.getName());
                        row.createCell(1).setCellValue(dataModel.getSurname());
                        row.createCell(2).setCellValue(dataModel.getMiddlename());
                        row.createCell(3).setCellValue(dataModel.getAge());
                        row.createCell(4).setCellValue(dataModel.getGender());
                        row.createCell(5).setCellValue(dataModel.getDate());
                        row.createCell(6).setCellValue(dataModel.getInn());
                        row.createCell(7).setCellValue(dataModel.getZip());
                        row.createCell(8).setCellValue(dataModel.getCountryFromTxt());
                        row.createCell(9).setCellValue(dataModel.getRegionFromTxt());
                        row.createCell(10).setCellValue(dataModel.getCityFromTxt());
                        row.createCell(11).setCellValue(dataModel.getStreetFromTxt());
                        row.createCell(12).setCellValue(dataModel.getHouse());
                        row.createCell(13).setCellValue(dataModel.getRoom());
                    } else {

                        DataModelDB dataModelDB = new DataModelDB();

                        id++;

                        row.createCell(0).setCellValue(dataModelDB.getNameFromDB(id));
                        row.createCell(1).setCellValue(dataModelDB.getSurnameFromDB(id));
                        row.createCell(2).setCellValue(dataModelDB.getMiddlenameFromDB(id));
                        //row.createCell(3).setCellValue(dataModelDB.getAgeFromDB(id));
                        row.createCell(4).setCellValue(dataModelDB.getGenderFromDB(id));
                        row.createCell(5).setCellValue(dataModelDB.getDateFromDB(id));
                        row.createCell(6).setCellValue(dataModelDB.getInnFromDB(id));
                        row.createCell(7).setCellValue(dataModelDB.getZipFromDB(id));
                        row.createCell(8).setCellValue(dataModelDB.getCountryFromDB(id));
                        row.createCell(9).setCellValue(dataModelDB.getRegionFromDB(id));
                        row.createCell(10).setCellValue(dataModelDB.getCityFromDB(id));
                        row.createCell(11).setCellValue(dataModelDB.getStreetFromDB(id));
                        row.createCell(12).setCellValue(dataModelDB.getHouseFromDB(id));
                        row.createCell(13).setCellValue(dataModelDB.getRoomFromDB(id));
                    }
                }
            } catch (SQLException e) {
                System.out.println("Отсутствует сооединение с интернетом. Данные будут взяты из БД");
                e.printStackTrace();
            }


            /*if (rs.wasNull()) {

            } else {  // если нет интернета и нет записей в бд
                row.createCell(0).setCellValue(dataModel.getName());
                row.createCell(1).setCellValue(dataModel.getSurname());
                row.createCell(2).setCellValue(dataModel.getMiddlename());
                row.createCell(3).setCellValue(dataModel.getAge());
                row.createCell(4).setCellValue(dataModel.getGender());
                row.createCell(5).setCellValue(dataModel.getDate());
                row.createCell(6).setCellValue(dataModel.getInn());
                row.createCell(7).setCellValue(dataModel.getZip());
                row.createCell(8).setCellValue(dataModel.getCountryFromTxt());
                row.createCell(9).setCellValue(dataModel.getRegionFromTxt());
                row.createCell(10).setCellValue(dataModel.getCityFromTxt());
                row.createCell(11).setCellValue(dataModel.getStreetFromTxt());
                row.createCell(12).setCellValue(dataModel.getHouse());
                row.createCell(13).setCellValue(dataModel.getRoom());
            }*/
        }
        row.createCell(3).setCellValue(dataModel.getAge());
        row.createCell(5).setCellValue(dataModel.getDate());
    }

    private static List<DataModel> fillData() throws IOException {

        List<DataModel> dataModels = new ArrayList<>();

        for (int i = 0; i < DataModel.createRandomIntBetween(1, 30); i++) {
            dataModels.add(new DataModel());
        }
        return dataModels;
    }

    private static void deleteFile() {
        File file = new File("../tinkoff/src/main/1.xls");
        if(file.delete()){
            System.out.println("Старый файл удален");
        }else System.out.println("Файл для удаления не был найден");
    }
}