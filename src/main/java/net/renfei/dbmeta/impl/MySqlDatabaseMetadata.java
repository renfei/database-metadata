/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.dbmeta.impl;

import net.renfei.dbmeta.DatabaseMetadata;
import net.renfei.dbmeta.entity.Table;
import net.renfei.dbmeta.entity.TableColum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author renfei
 */
public class MySqlDatabaseMetadata extends AbstractDatabaseMetadata implements DatabaseMetadata {
    public final static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";


    private MySqlDatabaseMetadata() throws ClassNotFoundException {
        super(null);
        throw new RuntimeException("error.");
    }

    public MySqlDatabaseMetadata(String url, String user, String password) throws ClassNotFoundException, SQLException {
        super(DriverManager.getConnection(url, user, password));
    }

    @Override
    void loadDriver() throws ClassNotFoundException {
        Class.forName(DRIVER_CLASS_NAME);
    }

    @Override
    public String getDatabaseProductName() throws SQLException {
        return this.getMetaData().getDatabaseProductName();
    }

    @Override
    public String getDatabaseProductVersion() throws SQLException {
        return this.getMetaData().getDatabaseProductVersion();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return connection.getMetaData();
    }

    @Override
    public List<String> getCatalogs() throws SQLException {
        try (ResultSet resultSet = this.getMetaData().getCatalogs()) {
            List<String> arrayList = new ArrayList<>();
            while (resultSet.next()) {
                arrayList.add(resultSet.getString("TABLE_CAT"));
            }
            return arrayList;
        }
    }

    @Override
    public List<String> getSchemas(String catalog) throws SQLException {
        return this.getCatalogs();
    }

    @Override
    public List<Table> getTables(String catalog, String schema) throws SQLException {
        return getTables(catalog, schema, new String[]{"TABLE"});
    }

    @Override
    public List<Table> getSystemTables(String catalog, String schema) throws SQLException {
        return getTables(catalog, schema, new String[]{"SYSTEM TABLE"});
    }

    @Override
    public List<Table> getViews(String catalog, String schema) throws SQLException {
        return getTables(catalog, schema, new String[]{"VIEW"});
    }

    @Override
    public List<TableColum> getColum(String catalog, String schema, String table) throws SQLException {
        try (ResultSet resultSet = this.getMetaData().getColumns(catalog, schema, table, null)) {
            return super.resultSetToTableColum(resultSet);
        }
    }

    private List<Table> getTables(String catalog, String schema, String[] types) throws SQLException {
        try (ResultSet resultSet = this.getMetaData().getTables(catalog, schema, null, types)) {
            return super.resultSetToTable(resultSet);
        }
    }
}
