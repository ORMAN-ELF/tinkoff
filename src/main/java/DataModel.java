import org.apache.commons.lang.RandomStringUtils;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * DataModel. Класс представляет собой подготовленные данные для создания файлов pdf, excel.
 *
 * @version:   0.1 25 февраля 2019
 * @Copyright  Наталья
 */


public class DataModel {

    private String name = setName();
    private String surname = setSurname();
    private String middlename = setMiddleName();
    private LocalDate date = getDateForAge();

    DataModel() throws IOException {}

    private String setName() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(
                        new File("src/main/resources/name.txt")), "Cp1251"));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        String [] sName = lines.toArray(new String[0]);
        reader.close();
        int rnd = new Random().nextInt(sName.length);
        return sName[rnd];
    }

    String getName() {
        return name;
    }

    private String setSurname() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(
                        new File("src/main/resources/surname.txt")),
                "Cp1251"));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        String [] sname = lines.toArray(new String[0]);
        reader.close();
        int rnd = new Random().nextInt(sname.length);
        return sname[rnd];
    }

    String getSurname() throws IOException {
        boolean forNameFimale = (name.endsWith("а"))
                || (name.endsWith("я"));
        boolean forNameMale = (!(name.endsWith("а")))
                && (!(name.endsWith("я")));

        boolean forSurnameFimale = surname.endsWith("ва");
        boolean forSurnameMale = !(surname.endsWith("а"));

        if (!((forNameFimale && forSurnameFimale)
                || (forNameMale && forSurnameMale))){
            surname = setSurname();
            getSurname();
        }
        return surname;
    }


    private String setMiddleName() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(
                        new File("src/main/resources/middlename.txt")),"Cp1251"));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        String [] mname = lines.toArray(new String[0]);
        reader.close();
        int rnd = new Random().nextInt(mname.length);
        return mname[rnd];
    }

    String getMiddlename() throws IOException {

        boolean forSurnameFimale = surname.endsWith("ва");
        boolean forSurnameMale = !(surname.endsWith("а"));

        boolean forMiddleFimale = middlename.endsWith("вна");
        boolean forMiddleMale = middlename.endsWith("ч");

        if (!((forSurnameFimale && forMiddleFimale)
                || (forSurnameMale && forMiddleMale))){
            middlename = setMiddleName();
            getMiddlename();
        }
        return middlename;
    }


    String getCountry() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(
                        new File("src/main/resources/country.txt")),"Cp1251"));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        String [] country = lines.toArray(new String[0]);
        reader.close();
        int rnd = new Random().nextInt(country.length);
        return country[rnd];
    }


    String getRegion() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(
                        new File("src/main/resources/region.txt")),"Cp1251"));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        String [] region = lines.toArray(new String[0]);
        reader.close();
        int rnd = new Random().nextInt(region.length);
        return region[rnd];
    }


    String getCity() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(
                        new File("src/main/resources/city.txt")),"Cp1251"));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        String [] city = lines.toArray(new String[0]);
        reader.close();
        int rnd = new Random().nextInt(city.length);
        return city[rnd];
    }


    String getStreet() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(
                        new File("src/main/resources/street.txt")),"Cp1251"));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        String [] street = lines.toArray(new String[0]);
        reader.close();
        int rnd = new Random().nextInt(street.length);
        return street[rnd];
    }


	String getGender(){
        if (middlename.endsWith("вна")){
            return "Ж";
        } else return "М";
    }

    private LocalDate getDateForAge() {

        LocalDate startDate = LocalDate.of(1990, 1, 1);
        long start = startDate.toEpochDay();

        LocalDate endDate = LocalDate.now();
        long end = endDate.toEpochDay();

        long randomEpochDay = ThreadLocalRandom.current()
                .longs(start, end).findAny().getAsLong();
        return LocalDate.ofEpochDay(randomEpochDay);
	}

    String getDate(){
        LocalDate forFormatter = LocalDate.from(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return forFormatter.format(formatter);
    }

    Integer getAge(){

        LocalDate today = LocalDate.now();
        LocalDate birthday = date;

        Period period = Period.between(birthday, today);
        return period.getYears();
    }


	String getInn(){
        String random = RandomStringUtils.random(10, false, true);
        return "77" + random;
	}

    private static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

	Integer getZip(){
        return createRandomIntBetween(100000, 200000);
	}
	
	Integer getHouse(){
        return createRandomIntBetween(1, 200);
	}
	
	Integer getRoom(){
        return createRandomIntBetween(1, 100);
	}

}
