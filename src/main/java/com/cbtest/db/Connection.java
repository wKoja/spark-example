package com.cbtest.db;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class Connection {

    public static Jdbi connect() throws Exception {

        Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/test");
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi;
    }
}
