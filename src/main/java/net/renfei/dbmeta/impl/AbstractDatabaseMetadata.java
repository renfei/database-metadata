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

import dm.jdbc.driver.DMException;
import net.renfei.dbmeta.entity.Table;
import net.renfei.dbmeta.entity.TableColum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author renfei
 */
public abstract class AbstractDatabaseMetadata {
    protected final Connection connection;

    protected AbstractDatabaseMetadata(Connection connection) throws ClassNotFoundException {
        loadDriver();
        this.connection = connection;
    }

    /**
     * 加载驱动类
     *
     * @throws ClassNotFoundException
     */
    abstract void loadDriver() throws ClassNotFoundException;

    protected List<Table> resultSetToTable(ResultSet resultSet) throws SQLException {
        List<Table> tables = new ArrayList<>();
        while (resultSet.next()) {
            Table table = new Table();
            table.setTableCat(resultSet.getString("TABLE_CAT"));
            table.setTableSchema(resultSet.getString("TABLE_SCHEM"));
            table.setTableName(resultSet.getString("TABLE_NAME"));
            table.setTableType(resultSet.getString("TABLE_TYPE"));
            table.setRemarks(resultSet.getString("REMARKS"));
            table.setTypeCat(resultSet.getString("TYPE_CAT"));
            table.setTypeSchema(resultSet.getString("TYPE_SCHEM"));
            table.setTypeName(resultSet.getString("TYPE_NAME"));
            table.setSelfReferencingColName(resultSet.getString("SELF_REFERENCING_COL_NAME"));
            table.setRefGeneration(resultSet.getString("REF_GENERATION"));
            tables.add(table);
        }
        return tables;
    }

    protected List<TableColum> resultSetToTableColum(ResultSet resultSet) throws SQLException {
        List<TableColum> colums = new ArrayList<>();
        while (resultSet.next()) {
            TableColum colum = new TableColum();
            colum.setTableCat(resultSet.getString("TABLE_CAT"));
            colum.setTableSchem(resultSet.getString("TABLE_SCHEM"));
            colum.setTableName(resultSet.getString("TABLE_NAME"));
            colum.setColumnName(resultSet.getString("COLUMN_NAME"));
            colum.setDataType(resultSet.getInt("DATA_TYPE"));
            colum.setTypeName(resultSet.getString("TYPE_NAME"));
            colum.setColumnSize(resultSet.getInt("COLUMN_SIZE"));
            colum.setDecimalDigits(resultSet.getInt("DECIMAL_DIGITS"));
            colum.setNumPrecRadix(resultSet.getInt("NUM_PREC_RADIX"));
            colum.setNullable(resultSet.getInt("NULLABLE"));
            colum.setRemarks(resultSet.getString("REMARKS"));
            colum.setColumnDef(resultSet.getString("COLUMN_DEF"));
            colum.setSqlDataType(resultSet.getInt("SQL_DATA_TYPE"));
            colum.setSqlDatetimeSub(resultSet.getInt("SQL_DATETIME_SUB"));
            colum.setCharOctetLength(resultSet.getInt("CHAR_OCTET_LENGTH"));
            colum.setOrdinalPosition(resultSet.getInt("ORDINAL_POSITION"));
            colum.setIsNullable(resultSet.getString("IS_NULLABLE"));
            try {
                colum.setScopeCatalog(resultSet.getString("SCOPE_CATALOG"));
            } catch (DMException e) {
                colum.setScopeCatalog(null);
            }
            colum.setScopeSchema(resultSet.getString("SCOPE_SCHEMA"));
            colum.setScopeTable(resultSet.getString("SCOPE_TABLE"));
            colum.setSourceDataType(resultSet.getShort("SOURCE_DATA_TYPE"));
            colum.setIsAutoincrement(resultSet.getString("IS_AUTOINCREMENT"));
            colum.setIsGeneratedcolumn(resultSet.getString("IS_GENERATEDCOLUMN"));
            colums.add(colum);
        }
        return colums;
    }
}
