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

import org.junit.Test;

import java.sql.SQLException;

/**
 * @author renfei
 */
public class MariaDbDatabaseMetadataTest {
    @Test
    public void test() throws SQLException, ClassNotFoundException {
        DatabaseMetadata databaseMetadata = DatabaseMetadataFactory.getDatabaseMetadata(
                "jdbc:mariadb://localhost:3306/renfeid?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "root");
        assert databaseMetadata != null;
        System.out.println(databaseMetadata.getDatabaseProductName());
        System.out.println(databaseMetadata.getDatabaseProductVersion());
        System.out.println(databaseMetadata.getCatalogs());
        System.out.println(databaseMetadata.getSchemas("renfeid"));
        System.out.println(databaseMetadata.getTables("renfeid", null));
        System.out.println(databaseMetadata.getColum("renfeid", null, "kitbox_icp_cache"));
    }
}
