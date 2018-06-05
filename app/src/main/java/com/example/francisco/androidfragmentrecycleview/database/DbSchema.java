package com.example.francisco.androidfragmentrecycleview.database;

public class DbSchema {

    public static final class ItemTable {

        public static final String NAME = "crimes";

        public static final class Columns {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";

        }

    }
}
