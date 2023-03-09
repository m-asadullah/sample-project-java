import java.sql.*;

public class DatabaseHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TYPE = ".db";
    public static final String DATABASE_NAME = "database";
    public static final String DATABASE_TABLE_NAME = "table_name";
    public static final String DATABASE_PATH = "jdbc:sqlite:C:\\Users\\HP-EliteBook\\Downloads\\devTestFolder\\" + DATABASE_NAME + DATABASE_TYPE;
    public static final String DATABASE_PATH_FILE = "C:\\Users\\HP-EliteBook\\Downloads\\devTestFolder\\" + DATABASE_NAME;
    public static final String DATABASE_PATH_FILE_BACKUP = "C:\\Users\\HP-EliteBook\\Documents\\devTestFolderBackup\\" + DATABASE_NAME;

    public static final String DATA_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    public static final String DATA_TABLE_START = "(";
    public static final String DATA_TABLE_END = ")";
    public static final String DATA_TABLE_COMMA = ", ";
    public static final String DATA_TABLE_BREAK_LINE = "\n";
    public static final String DATA_TABLE_SEMICOLON = "; ";
    public static final String DATA_INT_PRIMARY_KEY = " integer PRIMARY KEY";
    public static final String DATA_TEXT = " TEXT";
    public static final String DATA_TEXT_LONG = " TEXT LONG";
    public static final String DATA_ID = "id";
    public static final String DATA_ROLL_NO = "roll_number";
    public static final String DATA_REGISTRATION = "registration";
    public static final String DATA_APP_TYPE = "app_type";
    public static final String DATA_STUDENT_NAME = "student_name";
    public static final String DATA_FATHER_NAME = "father_name";
    public static final String DATA_INSTITUTION = "institution";
    public static final String DATA_SESSION = "session";
    public static final String DATA_SUBJECT_I_1 = "subject_i_1";
    public static final String DATA_SUBJECT_I_2 = "subject_i_2";
    public static final String DATA_SUBJECT_I_3 = "subject_i_3";
    public static final String DATA_SUBJECT_I_4 = "subject_i_4";
    public static final String DATA_SUBJECT_I_5 = "subject_i_5";
    public static final String DATA_SUBJECT_I_6 = "subject_i_6";
    public static final String DATA_SUBJECT_I_7 = "subject_i_7";
    public static final String DATA_SUBJECT_I_8 = "subject_i_8";
    public static final String DATA_MARKS_I_1 = "marks_i_1";
    public static final String DATA_MARKS_I_2 = "marks_i_2";
    public static final String DATA_MARKS_I_3 = "marks_i_3";
    public static final String DATA_MARKS_I_4 = "marks_i_4";
    public static final String DATA_MARKS_I_5 = "marks_i_5";
    public static final String DATA_MARKS_I_6 = "marks_i_6";
    public static final String DATA_MARKS_I_7 = "marks_i_7";
    public static final String DATA_MARKS_I_8 = "marks_i_8";
    public static final String DATA_SUBJECT_II_1 = "subject_ii_1";
    public static final String DATA_SUBJECT_II_2 = "subject_ii_2";
    public static final String DATA_SUBJECT_II_3 = "subject_ii_3";
    public static final String DATA_SUBJECT_II_4 = "subject_ii_4";
    public static final String DATA_SUBJECT_II_5 = "subject_ii_5";
    public static final String DATA_SUBJECT_II_6 = "subject_ii_6";
    public static final String DATA_SUBJECT_II_7 = "subject_ii_7";
    public static final String DATA_SUBJECT_II_8 = "subject_ii_8";
    public static final String DATA_MARKS_II_1 = "marks_ii_1";
    public static final String DATA_MARKS_II_2 = "marks_ii_2";
    public static final String DATA_MARKS_II_3 = "marks_ii_3";
    public static final String DATA_MARKS_II_4 = "marks_ii_4";
    public static final String DATA_MARKS_II_5 = "marks_ii_5";
    public static final String DATA_MARKS_II_6 = "marks_ii_6";
    public static final String DATA_MARKS_II_7 = "marks_ii_7";
    public static final String DATA_MARKS_II_8 = "marks_ii_8";
    public static final String DATA_PRACTICAL_1 = "practical_1";
    public static final String DATA_PRACTICAL_2 = "practical_2";
    public static final String DATA_PRACTICAL_3 = "practical_3";
    public static final String DATA_PRACTICAL_4 = "practical_4";
    public static final String DATA_PRACTICAL_5 = "practical_5";
    public static final String DATA_PRACTICAL_6 = "practical_6";
    public static final String DATA_PRACTICAL_7 = "practical_7";
    public static final String DATA_PRACTICAL_8 = "practical_8";
    public static final String DATA_RESULT = "result";
    public static final String DATA_STATUS = "status";
    public static final String DATA_MARKS_IN_WORDS = "marks_in_words";

    public void main(String[] args) {
        System.out.println(this.getClass().getName() + " Begins");

        try {
            Connection connection = DriverManager.getConnection(DATABASE_PATH);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName() + " & A new database has been created");
                createNewTable(DATABASE_PATH);
            } else {
                System.out.println("Database Connection is not available");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable(String databasePath) {
        // SQL statement for creating a new table
        String stringCreateTable = DATA_CREATE_TABLE + DATABASE_TABLE_NAME + DATA_TABLE_START + DATA_ID + DATA_INT_PRIMARY_KEY + DATA_TABLE_COMMA + DATA_ROLL_NO + DATA_TEXT + DATA_TABLE_COMMA + DATA_REGISTRATION + DATA_TEXT + DATA_TABLE_COMMA + DATA_APP_TYPE + DATA_TEXT + DATA_TABLE_COMMA + DATA_STUDENT_NAME + DATA_TEXT + DATA_TABLE_COMMA + DATA_FATHER_NAME + DATA_TEXT + DATA_TABLE_COMMA + DATA_INSTITUTION + DATA_TEXT + DATA_TABLE_COMMA + DATA_SESSION + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_I_1 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_I_2 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_I_3 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_I_4 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_I_5 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_I_6 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_I_7 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_I_8 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_I_1 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_I_2 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_I_3 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_I_4 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_I_5 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_I_6 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_I_7 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_I_8 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_II_1 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_II_2 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_II_3 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_II_4 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_II_5 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_II_6 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_II_7 + DATA_TEXT + DATA_TABLE_COMMA + DATA_SUBJECT_II_8 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_II_1 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_II_2 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_II_3 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_II_4 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_II_5 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_II_6 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_II_7 + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_II_8 + DATA_TEXT + DATA_TABLE_COMMA + DATA_PRACTICAL_1 + DATA_TEXT + DATA_TABLE_COMMA + DATA_PRACTICAL_2 + DATA_TEXT + DATA_TABLE_COMMA + DATA_PRACTICAL_3 + DATA_TEXT + DATA_TABLE_COMMA + DATA_PRACTICAL_4 + DATA_TEXT + DATA_TABLE_COMMA + DATA_PRACTICAL_5 + DATA_TEXT + DATA_TABLE_COMMA + DATA_PRACTICAL_6 + DATA_TEXT + DATA_TABLE_COMMA + DATA_PRACTICAL_7 + DATA_TEXT + DATA_TABLE_COMMA + DATA_PRACTICAL_8 + DATA_TEXT + DATA_TABLE_COMMA + DATA_RESULT + DATA_TEXT + DATA_TABLE_COMMA + DATA_STATUS + DATA_TEXT + DATA_TABLE_COMMA + DATA_MARKS_IN_WORDS + DATA_TEXT_LONG + DATA_TABLE_END + DATA_TABLE_SEMICOLON;
        //System.out.println(stringCreateTable);
        try {
            Connection connection = DriverManager.getConnection(databasePath);
            Statement statement = connection.createStatement();
            statement.execute(stringCreateTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_PATH);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insertNewData(String rollNo, String registration, String appType, String studentName, String fatherName, String institution, String session, String subjectI1, String subjectI2, String subjectI3, String subjectI4, String subjectI5, String subjectI6, String subjectI7, String subjectI8, String marksI1, String marksI2, String marksI3, String marksI4, String marksI5, String marksI6, String marksI7, String marksI8, String subjectII1, String subjectII2, String subjectII3, String subjectII4, String subjectII5, String subjectII6, String subjectII7, String subjectII8, String marksII1, String marksII2, String marksII3, String marksII4, String marksII5, String marksII6, String marksII7, String marksII8, String practical1, String practical2, String practical3, String practical4, String practical5, String practical6, String practical7, String practical8, String result, String status, String marksInWords) {
        //String stringInsertInTable = "INSERT INTO table_name(name, capacity) VALUES(?,?)";
        String stringInsertInTable = "INSERT INTO " + DATABASE_TABLE_NAME + DATA_TABLE_START + DATA_ROLL_NO + DATA_TABLE_COMMA + DATA_REGISTRATION + DATA_TABLE_COMMA + DATA_APP_TYPE + DATA_TABLE_COMMA + DATA_STUDENT_NAME + DATA_TABLE_COMMA + DATA_FATHER_NAME + DATA_TABLE_COMMA + DATA_INSTITUTION + DATA_TABLE_COMMA + DATA_SESSION + DATA_TABLE_COMMA

                + DATA_SUBJECT_I_1 + DATA_TABLE_COMMA + DATA_SUBJECT_I_2 + DATA_TABLE_COMMA + DATA_SUBJECT_I_3 + DATA_TABLE_COMMA + DATA_SUBJECT_I_4 + DATA_TABLE_COMMA + DATA_SUBJECT_I_5 + DATA_TABLE_COMMA + DATA_SUBJECT_I_6 + DATA_TABLE_COMMA + DATA_SUBJECT_I_7 + DATA_TABLE_COMMA + DATA_SUBJECT_I_8 + DATA_TABLE_COMMA

                + DATA_MARKS_I_1 + DATA_TABLE_COMMA + DATA_MARKS_I_2 + DATA_TABLE_COMMA + DATA_MARKS_I_3 + DATA_TABLE_COMMA + DATA_MARKS_I_4 + DATA_TABLE_COMMA + DATA_MARKS_I_5 + DATA_TABLE_COMMA + DATA_MARKS_I_6 + DATA_TABLE_COMMA + DATA_MARKS_I_7 + DATA_TABLE_COMMA + DATA_MARKS_I_8 + DATA_TABLE_COMMA

                + DATA_SUBJECT_II_1 + DATA_TABLE_COMMA + DATA_SUBJECT_II_2 + DATA_TABLE_COMMA + DATA_SUBJECT_II_3 + DATA_TABLE_COMMA + DATA_SUBJECT_II_4 + DATA_TABLE_COMMA + DATA_SUBJECT_II_5 + DATA_TABLE_COMMA + DATA_SUBJECT_II_6 + DATA_TABLE_COMMA + DATA_SUBJECT_II_7 + DATA_TABLE_COMMA + DATA_SUBJECT_II_8 + DATA_TABLE_COMMA

                + DATA_MARKS_II_1 + DATA_TABLE_COMMA + DATA_MARKS_II_2 + DATA_TABLE_COMMA + DATA_MARKS_II_3 + DATA_TABLE_COMMA + DATA_MARKS_II_4 + DATA_TABLE_COMMA + DATA_MARKS_II_5 + DATA_TABLE_COMMA + DATA_MARKS_II_6 + DATA_TABLE_COMMA + DATA_MARKS_II_7 + DATA_TABLE_COMMA + DATA_MARKS_II_8 + DATA_TABLE_COMMA

                + DATA_PRACTICAL_1 + DATA_TABLE_COMMA + DATA_PRACTICAL_2 + DATA_TABLE_COMMA + DATA_PRACTICAL_3 + DATA_TABLE_COMMA + DATA_PRACTICAL_4 + DATA_TABLE_COMMA + DATA_PRACTICAL_5 + DATA_TABLE_COMMA + DATA_PRACTICAL_6 + DATA_TABLE_COMMA + DATA_PRACTICAL_7 + DATA_TABLE_COMMA + DATA_PRACTICAL_8 + DATA_TABLE_COMMA

                + DATA_RESULT + DATA_TABLE_COMMA + DATA_STATUS + DATA_TABLE_COMMA + DATA_MARKS_IN_WORDS + DATA_TABLE_END + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection connection = this.connect();
            if (connection != null) {
                PreparedStatement prepareStatement = connection.prepareStatement(stringInsertInTable);
                prepareStatement.setString(1, rollNo);
                prepareStatement.setString(2, registration);
                prepareStatement.setString(3, appType);
                prepareStatement.setString(4, studentName);
                prepareStatement.setString(5, fatherName);
                prepareStatement.setString(6, institution);
                prepareStatement.setString(7, session);
                prepareStatement.setString(8, subjectI1);
                prepareStatement.setString(9, subjectI2);
                prepareStatement.setString(10, subjectI3);
                prepareStatement.setString(11, subjectI4);
                prepareStatement.setString(12, subjectI5);
                prepareStatement.setString(13, subjectI6);
                prepareStatement.setString(14, subjectI7);
                prepareStatement.setString(15, subjectI8);
                prepareStatement.setString(16, marksI1);
                prepareStatement.setString(17, marksI2);
                prepareStatement.setString(18, marksI3);
                prepareStatement.setString(19, marksI4);
                prepareStatement.setString(20, marksI5);
                prepareStatement.setString(21, marksI6);
                prepareStatement.setString(22, marksI7);
                prepareStatement.setString(23, marksI8);
                prepareStatement.setString(24, subjectII1);
                prepareStatement.setString(25, subjectII2);
                prepareStatement.setString(26, subjectII3);
                prepareStatement.setString(27, subjectII4);
                prepareStatement.setString(28, subjectII5);
                prepareStatement.setString(29, subjectII6);
                prepareStatement.setString(30, subjectII7);
                prepareStatement.setString(31, subjectII8);
                prepareStatement.setString(32, marksII1);
                prepareStatement.setString(33, marksII2);
                prepareStatement.setString(34, marksII3);
                prepareStatement.setString(35, marksII4);
                prepareStatement.setString(36, marksII5);
                prepareStatement.setString(37, marksII6);
                prepareStatement.setString(38, marksII7);
                prepareStatement.setString(39, marksII8);
                prepareStatement.setString(40, practical1);
                prepareStatement.setString(41, practical2);
                prepareStatement.setString(42, practical3);
                prepareStatement.setString(43, practical4);
                prepareStatement.setString(44, practical5);
                prepareStatement.setString(45, practical6);
                prepareStatement.setString(46, practical7);
                prepareStatement.setString(47, practical8);
                prepareStatement.setString(48, result);
                prepareStatement.setString(49, status);
                prepareStatement.setString(50, marksInWords);
                prepareStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error at insertNewData: " + e.getMessage());
        }
    }
}
