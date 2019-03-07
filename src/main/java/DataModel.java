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


class DataModel {

    private String name = randomName();
    private String surname = randomSurname();
    private String middlename = randomMiddleName();
    private LocalDate date = getDateForAge();

    DataModel() throws IOException {}


    private String [] getMassiveData(String path) throws IOException {
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

     private Integer getRandomFromMassive(String path) throws IOException {
         return new Random().nextInt(getMassiveData(path).length);
    }

    private String randomName() throws IOException{
        String path = "src/main/resources/name.txt";
        return getMassiveData(path)[getRandomFromMassive(path)];
    }

    String getName() {
        return name;
    }

    private String randomSurname() throws IOException{
        String path = "src/main/resources/surname.txt";
        return getMassiveData(path)[getRandomFromMassive(path)];
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
            surname = randomSurname();
            getSurname();
        }
        return surname;
    }

    private String randomMiddleName() throws IOException{
        String path = "src/main/resources/middlename.txt";
        return getMassiveData(path)[getRandomFromMassive(path)];
    }

    String getMiddlename() throws IOException {
        boolean forSurnameFemale = surname.endsWith("ва");
        boolean forSurnameMale = !(surname.endsWith("а"));

        boolean forMiddleFemale = middlename.endsWith("вна");
        boolean forMiddleMale = middlename.endsWith("ч");

        if (!((forSurnameFemale && forMiddleFemale)
                || (forSurnameMale && forMiddleMale))){
            middlename = randomMiddleName();
            getMiddlename();
        }
        return middlename;
    }

    String getCountry() throws IOException{
        String path = "src/main/resources/country.txt";
        return getMassiveData(path)[getRandomFromMassive(path)];
    }

    String getRegion() throws IOException{
        String path = "src/main/resources/region.txt";
        return getMassiveData(path)[getRandomFromMassive(path)];
    }

    String getCity() throws IOException{
        String path = "src/main/resources/city.txt";
        return getMassiveData(path)[getRandomFromMassive(path)];
    }

    String getStreet() throws IOException{
        String path = "src/main/resources/street.txt";
        return getMassiveData(path)[getRandomFromMassive(path)];
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
        int[] IFNS= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27,
                28, 29, 30, 31, 33, 34, 35, 36, 43, 45, 46, 47, 48, 49, 50, 51};
        int randomIFNS = new Random().nextInt(IFNS.length);
        String intIFNS = Integer.toString(IFNS[randomIFNS]);
        if (Integer.parseInt(intIFNS) < 10){
            intIFNS = "0" + intIFNS;
        }
        String random = RandomStringUtils.random(6, false, true);

        String a = "77" + intIFNS + random;
        String strArr[] = a.split("");
        int nINN[] = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            nINN[i] = Integer.parseInt(strArr[i]);
        }

        int n1 = (nINN[0] * 7 + nINN[1] * 2 + nINN[2] * 4 + nINN[3] * 10
                + nINN[4] * 3 + nINN[5] * 5 + nINN[6] * 9 + nINN[7] * 4
                + nINN[8] * 6 + nINN[9] * 8) % 11;

        int n2 = (nINN[0] * 3 + nINN[1] * 7 + nINN[2] * 2 + nINN[3] * 4
                + nINN[4] * 10 + nINN[5] * 3 + nINN[6] * 5 + nINN[7] * 9
                + nINN[8] * 4 + nINN[9] * 6 + n1 * 8) % 11;
        if (n1 == 10) { n1 = 0;}
        if (n2 == 10) { n2 = 0;}

        return "77" + intIFNS + random + n1 + n2;
	}

    static int createRandomIntBetween(int start, int end) {
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
