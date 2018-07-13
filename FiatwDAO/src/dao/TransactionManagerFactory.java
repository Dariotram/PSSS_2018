package dao;


/**
 * 
 */
public class TransactionManagerFactory {

	private static String databasePath = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7246756";
    private static String databaseUsername = "sql7246756";
    private static String databasePassword = "AfHWlUpq6e";
    private static boolean initialized = false;
    
    public static void initializeTransactionManager(String databasePath, String databaseUsername, String databasePassword) {
        if (initialized) {
            throw new IllegalStateException("TransactionManager already initialized!");
        }
        TransactionManagerFactory.databasePath = databasePath;
        TransactionManagerFactory.databaseUsername = databaseUsername;
        TransactionManagerFactory.databasePassword = databasePassword;
        TransactionManagerFactory.initialized = true;
    }
    
    public static TransactionManager createTransactionManager() {
        return new TransactionManager(databasePath, databaseUsername, databasePassword);
    }

}