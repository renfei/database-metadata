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

import net.renfei.dbmeta.impl.DaMengDatabaseMetadata;
import net.renfei.dbmeta.impl.MariaDbDatabaseMetadata;
import net.renfei.dbmeta.impl.MySqlDatabaseMetadata;

import java.sql.SQLException;

/**
 * @author renfei
 */
public class DatabaseMetadataFactory {
    private final static String URL_MYSQL = "jdbc:mysql://";
    private final static String URL_MARIADB = "jdbc:mariadb://";
    private final static String URL_DAMENG = "jdbc:dm://";

    private DatabaseMetadataFactory() {
        throw new RuntimeException("error.");
    }

    public static DatabaseMetadata getDatabaseMetadata(String url, String user, String password) throws SQLException, ClassNotFoundException {
        url = url.trim().toLowerCase();
        if (url.startsWith(URL_MYSQL)) {
            return new MySqlDatabaseMetadata(url, user, password);
        } else if (url.startsWith(URL_MARIADB)) {
            return new MariaDbDatabaseMetadata(url, user, password);
        } else if (url.startsWith(URL_DAMENG)) {
            return new DaMengDatabaseMetadata(url, user, password);
        }
        return null;
    }
}
