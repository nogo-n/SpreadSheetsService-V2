# 1. ベースイメージを指定
FROM openjdk:17-jdk AS builder

# 2. アプリケーションをコピー
COPY target/ /app/SortApplication/

# 3. 作業ディレクトリを設定
WORKDIR /app/SortApplication

# 4. JARファイルを実行するコマンドを指定
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
