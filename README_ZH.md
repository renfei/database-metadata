[[简体中文](./README_ZH.md)] | [[English](./README.md)]

# 使用JDBC提取数据库元数据

基于JDBC连接提取数据库的元数据，包括`Catalog`、`Schema`、`Table`、`Colum`等信息。

## 支持的数据库

- [x] MySQL
- [x] MariaDB
- [ ] PostgreSQL
- [ ] Oracle
- [ ] Microsoft SQL Server
- [ ] IBM DB2
- [ ] Sybase
- [ ] Informix
- [x] DMDBMS（武汉达梦数据库）
- [ ] KingbaseES（人大金仓数据库）
- [ ] OSCAR（神舟通用数据库）

## 安装

### Maven

```xml
<dependency>
    <groupId>net.renfei</groupId>
    <artifactId>database-metadata</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## 示例

```java
package net.renfei.dbmeta;

import org.junit.Test;

import java.sql.SQLException;

/**
 * @author renfei
 */
public class MySqlDatabaseMetadataTest {
    @Test
    public void test() throws SQLException, ClassNotFoundException {
        DatabaseMetadata databaseMetadata = DatabaseMetadataFactory.getDatabaseMetadata(
                "jdbc:mysql://localhost:3306/renfeid?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
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
```

> 注：MySQL使用`catalog`而不是`schema`。