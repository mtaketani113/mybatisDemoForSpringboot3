SpringBoot3でMyBatisを動作させるデモです。

以下のDDLでMySQLを作ることで動作させることができます。

```SQL
CREATE TABLE customer (
    id       INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    name     TEXT,
    post     TEXT,
    address  TEXT,

    PRIMARY KEY(id)
);
```
接続情報を編集または環境変数を登録してください。
```
spring.datasource.url=${DATASOURCE_URL:jdbc:mysql://localhost:3306/demo}
spring.datasource.username=${DATASOURCE_USERNAME:demo}
spring.datasource.password=${DATASOURCE_PASSWORD:demo}
```