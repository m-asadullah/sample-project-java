
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Main Started");
        //Check if database exist then copy database to other oldData folder with time millis in name
        checkDatabaseExistence();
    }

    private static void checkDatabaseExistence() {
        long longTimestamp = System.currentTimeMillis();
        File fileDatabase = new File(DatabaseHelper.DATABASE_PATH_FILE + DatabaseHelper.DATABASE_TYPE);
        File fileDatabaseBackup = new File(DatabaseHelper.DATABASE_PATH_FILE_BACKUP + "_" + longTimestamp + DatabaseHelper.DATABASE_TYPE);
        if (fileDatabase.exists()) {
            if (fileDatabase.isFile()) {
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    try {
                        inputStream = new FileInputStream(fileDatabase);
                        outputStream = new FileOutputStream(fileDatabaseBackup);
                        byte[] byteBuffer = new byte[1024];
                        int intLength;
                        while ((intLength = inputStream.read(byteBuffer)) > 0) {
                            outputStream.write(byteBuffer, 0, intLength);
                        }
                        if (fileDatabase.exists()) {
                            fileDatabase.delete();
                            System.out.println("Old file existed & backup created, now deleted that file");
                            mainBigTask();
                        }
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    }
                } catch (IOException ioException) {
                    System.out.println("Error copying file: " + ioException.getMessage());
                }
            }
        } else {
            mainBigTask();
        }
    }

    public static void mainBigTask() {

        try {
            Connection connection = DriverManager.getConnection(DatabaseHelper.DATABASE_PATH);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName() + " & A new database has been created");
                DatabaseHelper.createNewTable(DatabaseHelper.DATABASE_PATH);
            } else {
                System.out.println("Database Connection is not available");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        int numThreads = 1;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        Runnable runnable = () -> {
            int intStart = 700001;
            int intEnd = 700005;
            int intLimit = 955050;
            System.out.println("Task begin from " + intStart + " to " + intLimit);
            for (int i = intStart; i < intLimit; i++) {
                System.out.println(i);
                //
                try {
                    //PMC Result scrape data & save into SQL.db or .csv
                    String stringRollNumber = String.valueOf(i);
                    String[] stringRollPartSplits = stringRollNumber.split("", 6);
                    String stringRollPart1 = stringRollPartSplits[0];
                    String stringRollPart2 = stringRollPartSplits[1];
                    String stringRollPart3 = stringRollPartSplits[2];
                    String stringRollPart4 = stringRollPartSplits[3];
                    String stringRollPart5 = stringRollPartSplits[4];
                    String stringRollPart6 = stringRollPartSplits[5];

                    String stringRollPart1Encode = stringA(Integer.parseInt(stringRollPart1));
                    String stringRollPart2Encode = stringB(Integer.parseInt(stringRollPart2));
                    String stringRollPart3Encode = stringC(Integer.parseInt(stringRollPart3));
                    String stringRollPart4Encode = stringA(Integer.parseInt(stringRollPart4));
                    String stringRollPart5Encode = stringB(Integer.parseInt(stringRollPart5));
                    String stringRollPart6Encode = stringC(Integer.parseInt(stringRollPart6));
                    String stringRollEncode = stringRollPart1Encode + stringRollPart2Encode + stringRollPart3Encode + stringRollPart4Encode + stringRollPart5Encode + stringRollPart6Encode;

                    String stringURLFine = "" + stringRollEncode;
                    //System.out.println("URL for " + stringRollEncode + " is ready");

                    try {
                        URL url = new URL(stringURLFine);
                        URLConnection connection = url.openConnection();
                        connection.connect();
                        //System.out.println("Internet is connected");

                        Document document = Jsoup.connect(stringURLFine).get();
                        //System.out.println("Document Title: " + document.title());

                        Elements elementsTitle = document.select("table > tbody > tr:nth-child(1) > td > h3");
                        //System.out.println("Task Main: " + elementsTitle.text());

                        Elements elementsRollNo = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(1) > tbody > tr > td > table > tbody > tr:nth-child(1) > td.auto-style2 > strong > span");
                        Elements elementsRegistrationNo = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(1) > tbody > tr > td > table > tbody > tr:nth-child(1) > td:nth-child(4) > strong > span");
                        String[] stringRegistration = elementsRegistrationNo.text().split("App", 2);
                        Elements elementsAppType = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(1) > tbody > tr > td > table > tbody > tr:nth-child(1) > td:nth-child(4) > strong > span");
                        String[] stringAppType = elementsAppType.text().split(":", 2);
                        Elements elementsStudentName = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(1) > tbody > tr > td > table > tbody > tr:nth-child(2) > td.auto-style2 > strong > span");
                        Elements elementsFatherName = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(1) > tbody > tr > td > table > tbody > tr:nth-child(2) > td:nth-child(4) > strong > span");
                        Elements elementsInstitution = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(1) > tbody > tr > td > table > tbody > tr:nth-child(3) > td.auto-style2 > strong > span");
                        String elementsSession = "null";
                        Elements elementsSubject1A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(3) > td:nth-child(1)");
                        Elements elementsSubject2A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(4) > td:nth-child(1)");
                        Elements elementsSubject3A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(5) > td:nth-child(1)");
                        Elements elementsSubject4A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(6) > td:nth-child(1)");
                        Elements elementsSubject5A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(7) > td:nth-child(1)");
                        Elements elementsSubject6A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(8) > td:nth-child(1)");
                        Elements elementsSubject7A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(9) > td:nth-child(1)");
                        Elements elementsSubject8A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(10) > td:nth-child(1)");

                        Elements elementsMarks1A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(3) > td:nth-child(2)");
                        Elements elementsMarks2A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(4) > td:nth-child(2)");
                        Elements elementsMarks3A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(5) > td:nth-child(2)");
                        Elements elementsMarks4A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(6) > td:nth-child(2)");
                        Elements elementsMarks5A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(7) > td:nth-child(2)");
                        Elements elementsMarks6A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(8) > td:nth-child(2)");
                        Elements elementsMarks7A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(9) > td:nth-child(2)");
                        Elements elementsMarks8A = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(10) > td:nth-child(2)");

                        Elements elementsSubject1B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(3) > td:nth-child(3)");
                        Elements elementsSubject2B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(4) > td:nth-child(3)");
                        Elements elementsSubject3B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(5) > td:nth-child(3)");
                        Elements elementsSubject4B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(6) > td:nth-child(3)");
                        Elements elementsSubject5B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(7) > td:nth-child(3)");
                        Elements elementsSubject6B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(8) > td:nth-child(3)");
                        Elements elementsSubject7B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(9) > td:nth-child(3)");
                        Elements elementsSubject8B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(10) > td:nth-child(3)");

                        Elements elementsMarks1B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(3) > td:nth-child(4)");
                        Elements elementsMarks2B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(4) > td:nth-child(4)");
                        Elements elementsMarks3B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(5) > td:nth-child(4)");
                        Elements elementsMarks4B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(6) > td:nth-child(4)");
                        Elements elementsMarks5B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(7) > td:nth-child(4)");
                        Elements elementsMarks6B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(8) > td:nth-child(4)");
                        Elements elementsMarks7B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(9) > td:nth-child(4)");
                        Elements elementsMarks8B = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(10) > td:nth-child(4)");

                        Elements elementsPractical1 = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(3) > td:nth-child(5)");
                        Elements elementsPractical2 = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(4) > td:nth-child(5)");
                        Elements elementsPractical3 = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(5) > td:nth-child(5)");
                        Elements elementsPractical4 = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(6) > td:nth-child(5)");
                        Elements elementsPractical5 = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(7) > td:nth-child(5)");
                        Elements elementsPractical6 = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(8) > td:nth-child(5)");
                        Elements elementsPractical7 = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(9) > td:nth-child(5)");
                        Elements elementsPractical8 = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(10) > td:nth-child(5)");

                        Elements elementsResult = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(2) > td:nth-child(2) > div > strong");
                        Elements elementsStatus = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(3) > td:nth-child(2) > div > strong");
                        Elements elementsMarksInWords = document.select("table > tbody > tr:nth-child(2) > td > table:nth-child(2) > tbody > tr:nth-child(4) > td");

                        //System.out.println("Registration: " + Arrays.toString(stringRegistration) + "; " + Arrays.toString(stringAppType));
                        DatabaseHelper databaseHelper = new DatabaseHelper();
                        databaseHelper.insertNewData(elementsRollNo.text(), stringRegistration[0], stringAppType[1], elementsStudentName.text(), elementsFatherName.text(), elementsInstitution.text(), elementsSession,
                                elementsSubject1A.text(), elementsSubject2A.text(), elementsSubject3A.text(), elementsSubject4A.text(), elementsSubject5A.text(), elementsSubject6A.text(), elementsSubject7A.text(), elementsSubject8A.text(),
                                elementsMarks1A.text(), elementsMarks2A.text(), elementsMarks3A.text(), elementsMarks4A.text(), elementsMarks5A.text(), elementsMarks6A.text(), elementsMarks7A.text(), elementsMarks8A.text(),
                                elementsSubject1B.text(), elementsSubject2B.text(), elementsSubject3B.text(), elementsSubject4B.text(), elementsSubject5B.text(), elementsSubject6B.text(), elementsSubject7B.text(), elementsSubject8B.text(),
                                elementsMarks1B.text(), elementsMarks2B.text(), elementsMarks3B.text(), elementsMarks4B.text(), elementsMarks5B.text(), elementsMarks6B.text(), elementsMarks7B.text(), elementsMarks8B.text(),
                                elementsPractical1.text(), elementsPractical2.text(), elementsPractical3.text(), elementsPractical4.text(), elementsPractical5.text(), elementsPractical6.text(), elementsPractical7.text(), elementsPractical8.text(),
                                elementsResult.text(), elementsStatus.text(), elementsMarksInWords.text()
                        );
                    } catch (IOException ioException) {
                        System.out.println("Internet is not connected; " + ioException.getMessage());
                    }
                } catch (Exception exception) {
                    System.out.println("Error; " + exception.getMessage());
                }
                executor.shutdown();
                System.out.println("Finished the background task for " + i);
            }
        };

        //System.out.println("About to submit the background task");
        executor.execute(runnable);
        System.out.println("Submitted the background task");
    }

    @Contract(pure = true)
    private static @NotNull String stringA(int number) {

        if (number <= 9) {
            if (number == 0) {
                return "MD";
            } else if (number == 1) {
                return "MT";
            } else if (number == 2) {
                return "Mj";
            } else if (number == 3) {
                return "Mz";
            } else if (number == 4) {
                return "ND";
            } else if (number == 5) {
                return "NT";
            } else if (number == 6) {
                return "Nj";
            } else if (number == 7) {
                return "Nz";
            } else if (number == 8) {
                return "OD";
            } else if (number == 9) {
                return "OT";
            } else {
                return "?";
            }
        } else {
            return String.valueOf(number);
        }
    }

    @Contract(pure = true)
    private static @NotNull String stringB(int number) {

        if (number <= 9) {
            if (number == 0) {
                return "A";
            } else if (number == 1) {
                return "E";
            } else if (number == 2) {
                return "I";
            } else if (number == 3) {
                return "M";
            } else if (number == 4) {
                return "Q";
            } else if (number == 5) {
                return "U";
            } else if (number == 6) {
                return "Y";
            } else if (number == 7) {
                return "c";
            } else if (number == 8) {
                return "g";
            } else if (number == 9) {
                return "k";
            } else {
                return "?";
            }
        } else {
            return String.valueOf(number);
        }
    }

    @Contract(pure = true)
    private static @NotNull String stringC(int number) {

        if (number <= 9) {
            if (number == 0) {
                return "w";
            } else if (number == 1) {
                return "x";
            } else if (number == 2) {
                return "y";
            } else if (number == 3) {
                return "z";
            } else if (number == 4) {
                return "0";
            } else if (number == 5) {
                return "1";
            } else if (number == 6) {
                return "2";
            } else if (number == 7) {
                return "3";
            } else if (number == 8) {
                return "4";
            } else if (number == 9) {
                return "5";
            } else {
                return "?";
            }
        } else {
            return String.valueOf(number);
        }
    }
}