import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * ExcelFile. Класс представляет собой созданный и заполненный данными excel документ.
 *
 * @version:   0.3 08 марта 2019
 * @Copyright  Наталья
 */

public class ExcelFile {


    public static void main(String[] args) throws Exception {

        GetJSON getJSON = new GetJSON();
        StringBuffer response = getJSON.getJson();
        System.out.println(response);


        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(String.valueOf(response));
        try {
            if (rootNode instanceof ArrayNode) {

                MyPojo[] objects = mapper.readValue(rootNode.toString(), MyPojo[].class);
                System.out.println(objects);

            } else if (rootNode instanceof JsonNode) {

                MyPojo object = mapper.readValue(rootNode.toString(), MyPojo.class);
                String title = object.getResults().iterator().next().getName().getTitle();
                String name = object.getResults().iterator().next().getName().getFirst();
                String lastname = object.getResults().iterator().next().getName().getLast();

                String street = object.getResults().iterator().next().getLocation().getStreet();
                String city = object.getResults().iterator().next().getLocation().getCity();
                String state = object.getResults().iterator().next().getName().getLast();

                System.out.println(title + " " + name + " " + lastname + " " + street + " " + city + " " + state);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        // создание самого excel файла в памяти
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("list_1");

        List<DataModel> dataList = fillData();

        int rowNum = 0;
        deleteFile();

        Row row = sheet.createRow(rowNum);
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

        File file = new File("src/main/1.xls");
        System.out.println("Файл создан. Путь: "+ file.getAbsolutePath());

    }


    // данными  из dataModel созданного в памяти Excel файла
    private static void createSheetHeader(HSSFSheet sheet, int rowNum, DataModel dataModel) throws IOException {
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(dataModel.getName());
        row.createCell(1).setCellValue(dataModel.getSurname());
        row.createCell(2).setCellValue(dataModel.getMiddlename());
		row.createCell(3).setCellValue(dataModel.getAge());
		row.createCell(4).setCellValue(dataModel.getGender());
		row.createCell(5).setCellValue(dataModel.getDate());
		row.createCell(6).setCellValue(dataModel.getInn());
		row.createCell(7).setCellValue(dataModel.getZip());
        row.createCell(8).setCellValue(dataModel.getCountry());
        row.createCell(9).setCellValue(dataModel.getRegion());
        row.createCell(10).setCellValue(dataModel.getCity());
        row.createCell(11).setCellValue(dataModel.getStreet());
		row.createCell(12).setCellValue(dataModel.getHouse());
		row.createCell(13).setCellValue(dataModel.getRoom());
    }

    private static List<DataModel> fillData() throws IOException {

        List<DataModel> dataModels = new ArrayList<>();

        for (int i = 0; i < DataModel.createRandomIntBetween(1, 100); i++) {
            dataModels.add(new DataModel());
        }
        return dataModels;
    }

    private static void deleteFile() {
        File file = new File("../tinkoff/src/main/1.xls");
        if(file.delete()){
            System.out.println("Старый файл удален");
        }else System.out.println("Файл для удаления не был найдет");
    }

}