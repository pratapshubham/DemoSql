package com.example.user.demosql;

public class Temp
{
    public static DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }

    public static void setDatabaseHandler(DatabaseHandler databaseHandler) {
        Temp.databaseHandler = databaseHandler;
    }

    public static DatabaseHandler databaseHandler;
}
