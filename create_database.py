#pip install psycopg2-binary
import psycopg2
from psycopg2 import sql
import os
from getpass import getpass

def connect_to_postgres(host, port, user, password, database=None):
    """PostgreSQL'e bağlan."""
    try:
        conn = psycopg2.connect(
            host=host,
            port=port,
            user=user,
            password=password,
            database=database
        )
        conn.autocommit = True
        return conn
    except psycopg2.Error as e:
        print(f"Bağlantı hatası: {e}")
        return None

def create_user_if_not_exists(conn, username, password):
    """Kullanıcıyı oluştur, varsa hata verme."""
    try:
        with conn.cursor() as cur:
            cur.execute(
                sql.SQL("CREATE USER {} WITH PASSWORD %s").format(sql.Identifier(username)),
                [password]
            )
            print(f"Kullanıcı '{username}' oluşturuldu.")
    except psycopg2.errors.DuplicateObject:
        print(f"Kullanıcı '{username}' zaten var, oluşturulmadı.")
    except psycopg2.Error as e:
        print(f"Kullanıcı oluşturma hatası: {e}")

def create_database_if_not_exists(conn, db_name, owner):
    """Veritabanını oluştur, varsa hata verme."""
    try:
        with conn.cursor() as cur:
            cur.execute(
                sql.SQL("CREATE DATABASE {} OWNER {}").format(
                    sql.Identifier(db_name), sql.Identifier(owner)
                )
            )
            print(f"Veritabanı '{db_name}' oluşturuldu.")
            # 6. application.properties dosyasını oluştur
            create_application_properties(host, port, db_name, new_user, new_user_password)

    except psycopg2.errors.DuplicateDatabase:
        print(f"Veritabanı '{db_name}' zaten var, oluşturulmadı.")
    except psycopg2.Error as e:
        print(f"Veritabanı oluşturma hatası: {e}")

def run_sql_file(conn, sql_file):
    """SQL dosyasını çalıştır."""
    if not os.path.exists(sql_file):
        print(f"Hata: '{sql_file}' dosyası bulunamadı.")
        return
    try:
        with open(sql_file, 'r') as f:
            sql_commands = f.read()
        with conn.cursor() as cur:
            cur.execute(sql_commands)
        print(f"'{sql_file}' dosyası başarıyla çalıştırıldı.")
    except psycopg2.Error as e:
        print(f"SQL dosyası çalıştırma hatası: {e}")

def create_application_properties(host, port, db_name, username, password):
    """application.properties dosyası oluştur."""
    new_content = f"""spring.datasource.url=jdbc:postgresql://{host}:{port}/{db_name}
spring.datasource.username={username}
spring.datasource.password={password}
"""
    with open("./src/main/resources/application.properties", "r") as fr:
        file_content = fr.read()
        print("file_content**:"+file_content)
        fr.close()
        with open("./src/main/resources/application.properties", "w") as fw:
            fw.write(file_content + new_content)
            fw.close()
    print("application.properties dosyası oluşturuldu.")

def main():
    # PostgreSQL bağlantı bilgileri
    host = input("PostgreSQL host (varsayılan: localhost): ") or "localhost"
    port = input("PostgreSQL port (varsayılan: 5432): ") or "5432"
    admin_user = input("Yönetici kullanıcı adı (ör. postgres): ") or "postgres"
    admin_password = getpass("Yönetici parolası: ")

    # Sabit kullanıcı ve veritabanı bilgileri
    new_user = "genli"
    new_user_password = "genli123"
    db_name = "myth_busters_db"
    # sql_files = ["MythBustersBackend/src/main/resources/db/migration/V1__CREATE_TABEL.sql", 
    #              "MythBustersBackend/src/main/resources/db/migration/V2__INSERT_DATA.sql"]

    # 1. Yönetici hesabıyla bağlan
    conn = connect_to_postgres(host, port, admin_user, admin_password)
    if not conn:
        print("PostgreSQL'e bağlanılamadı. Lütfen bilgileri kontrol edin.")
        return

    # 2. Kullanıcıyı oluştur (varsa hata verme)
    create_user_if_not_exists(conn, new_user, new_user_password)

    # 3. Veritabanını oluştur (varsa hata verme)
    create_database_if_not_exists(conn, db_name, new_user)

    # 4. Yönetici bağlantısını kapat
    conn.close()

    # 5. Yeni veritabanına bağlan ve SQL dosyasını çalıştır (eğer belirtilmişse)
    # if sql_file:
    #     conn = connect_to_postgres(host, port, new_user, new_user_password, db_name)
    #     if conn:
    #         run_sql_file(conn, sql_file)
    #         conn.close()


if __name__ == "__main__":
    main()