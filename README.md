SpringBoot3でMyBatisを動作させるデモです。

以下のDDLでMySQLを作ることで動作させることができます。

```SQL
CREATE TABLE customer (
    id       INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    name     TEXT,
    post     TEXT,
    address  TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS file_manager (
    id          INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    file_name   TEXT,
    file_data   MEDIUMBLOB,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY(id)
);
```

Oracleも利用可能にする予定。

接続情報を編集または環境変数を登録してください。
```
spring.datasource.url=${DATASOURCE_URL:jdbc:mysql://localhost:3306/demo}
spring.datasource.username=${DATASOURCE_USERNAME:demo}
spring.datasource.password=${DATASOURCE_PASSWORD:demo}
```

Googleの認証を利用しているので、Google Cloud でClientIdとSecretを取得して
以下の環境変数に設定する必要があります。

```
spring.security.oauth2.client.registration.google.client-id=${OAUTH2_GOOGLE_CLIENT_ID}
spring.security.oauth2.client.registration.google.client-secret=${OAUTH2_GOOGLE_CLIENT_SECRET}
```


また、以下のコマンドでdockerを利用して環境を構築することも可能
```
docker-compose up -d
```

Dockerで作成した場合、ポート番号は`9090`に設定しているので
`http://localhost:9090/demo`でアクセス可能です。

変更したい場合は、`docker-compose.yaml`の`ports`を変更ください