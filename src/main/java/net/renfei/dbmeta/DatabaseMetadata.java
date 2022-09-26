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
package net.renfei.dbmeta;

import net.renfei.dbmeta.entity.Table;
import net.renfei.dbmeta.entity.TableColum;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库元数据
 *
 * @author renfei
 */
public interface DatabaseMetadata {
    /**
     * 获取数据库产品名称
     *
     * @return
     * @throws SQLException
     */
    String getDatabaseProductName() throws SQLException;

    /**
     * 获取数据库产品版本号
     *
     * @return
     * @throws SQLException
     */
    String getDatabaseProductVersion() throws SQLException;

    /**
     * 获取元数据对象
     *
     * @return
     * @throws SQLException
     */
    DatabaseMetaData getMetaData() throws SQLException;

    /**
     * 获取数据库列表
     *
     * @return
     * @throws SQLException
     */
    List<String> getCatalogs() throws SQLException;

    /**
     * 获取模式列表
     *
     * @param catalog 数据库名
     * @return
     * @throws SQLException
     */
    List<String> getSchemas(String catalog) throws SQLException;

    /**
     * 获取用户表
     *
     * @param catalog 数据库名
     * @param schema  模式名
     * @return
     * @throws SQLException
     */
    List<Table> getTables(String catalog, String schema) throws SQLException;

    /**
     * 获取系统表
     *
     * @param catalog 数据库名
     * @param schema  模式名
     * @return
     * @throws SQLException
     */
    List<Table> getSystemTables(String catalog, String schema) throws SQLException;

    /**
     * 获取视图
     *
     * @param catalog 数据库名
     * @param schema  模式名
     * @return
     * @throws SQLException
     */
    List<Table> getViews(String catalog, String schema) throws SQLException;

    /**
     * 获取列
     *
     * @param catalog 数据库名
     * @param schema  模式名
     * @param table   表名
     * @return
     * @throws SQLException
     */
    List<TableColum> getColum(String catalog, String schema, String table) throws SQLException;
}
