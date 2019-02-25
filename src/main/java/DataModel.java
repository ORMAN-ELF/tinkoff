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

    private String [] getMassiveDate(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(
                        new File(path)), "Cp1251"));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines.toArray(new String[0]);
    }

    private String setName() throws IOException{
        String path = "src/main/resources/name.txt";
        String [] sName = getMassiveDate(path);
        int rnd = new Random().nextInt(sName.length);
        return sName[rnd];
    }

    String getName() {
        return name;
    }

    private String setSurname() throws IOException{
        String path = "src/main/resources/surname.txt";
        String [] sname = getMassiveDate(path);
        int rnd = new Random().nextInt(sname.length);
        return sname[rnd];
    }

    String getSurname() throws IOException {
        boolean forNameFemale = (name.endsWith("а"))
                || (name.endsWith("я"));
        boolean forNameMale = (!(name.endsWith("а")))
                && (!(name.endsWith("я")));
        boolean forSurnameFemale = surname.endsWith("ва");
        boolean forSurnameMale = !(surname.endsWith("а"));

        if (!((forNameFemale && forSurnameFemale)
                || (forNameMale && forSurnameMale))){
            surname = setSurname();
            getSurname();
        }
        return surname;
    }

    private String setMiddleName() throws IOException{
        String path = "src/main/resources/middlename.txt";
        String [] setMiddlename = getMassiveDate(path);
        int rnd = new Random().nextInt(setMiddlename.length);
        return setMiddlename[rnd];
    }

    String getMiddlename() throws IOException {
        boolean forSurnameFemale = surname.endsWith("ва");
        boolean forSurnameMale = !(surname.endsWith("а"));

        boolean forMiddleFemale = middlename.endsWith("вна");
        boolean forMiddleMale = middlename.endsWith("ч");

        if (!((forSurnameFemale && forMiddleFemale)
                || (forSurnameMale && forMiddleMale))){
            middlename = setMiddleName();
            getMiddlename();
        }
        return middlename;
    }

    String getCountry() throws IOException{
        String path = "src/main/resources/country.txt";
        String [] country = getMassiveDate(path);
        int rnd = new Random().nextInt(country.length);
        return country[rnd];
    }

    String getRegion() throws IOException{
        String path = "src/main/resources/region.txt";
        String [] region = getMassiveDate(path);
        int rnd = new Random().nextInt(region.length);
        return region[rnd];
    }

    String getCity() throws IOException{
        String path = "src/main/resources/city.txt";
        String [] city = getMassiveDate(path);
        int rnd = new Random().nextInt(city.length);
        return city[rnd];
    }

    String getStreet() throws IOException{
        String path = "src/main/resources/street.txt";
        String [] street = getMassiveDate(path);
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
