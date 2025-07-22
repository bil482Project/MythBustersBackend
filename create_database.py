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
            database=database,
            connect_timeout=5
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
                sql.SQL("CREATE USER {} WITH ENCRYPTED PASSWORD %s").format(
                    sql.Identifier(username)
                ),
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
    except psycopg2.errors.DuplicateDatabase:
        print(f"Veritabanı '{db_name}' zaten var, oluşturulmadı.")
    except psycopg2.Error as e:
        print(f"Veritabanı oluşturma hatası: {e}")

def create_application_properties(host, port, db_name, username, password):
    """application.properties dosyası oluştur veya güncelle."""
    properties_file = "./src/main/resources/application.properties"
    new_content = f"""spring.datasource.url=jdbc:postgresql://{host}:{port}/{db_name}
spring.datasource.username={username}
spring.datasource.password={password}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
"""
    try:
        os.makedirs(os.path.dirname(properties_file), exist_ok=True)
        if os.path.exists(properties_file):
            with open(properties_file, "r") as fr:
                existing_content = fr.read()
            if new_content.strip() not in existing_content:
                with open(properties_file, "a") as fw:
                    fw.write("\n" + new_content)
                print("application.properties dosyası güncellendi.")
            else:
                print("application.properties zaten güncel, değişiklik yapılmadı.")
        else:
            with open(properties_file, "w") as fw:
                fw.write(new_content)
            print("application.properties dosyası oluşturuldu.")
    except IOError as e:
        print(f"application.properties oluşturma/güncelleme hatası: {e}")

def main():
    """Ana fonksiyon: PostgreSQL kurulumunu gerçekleştir."""
    # PostgreSQL bağlantı bilgileri
    host = os.getenv("PG_HOST", "localhost")
    port = os.getenv("PG_PORT", "5432")
    admin_user = os.getenv("PG_ADMIN_USER", "postgres")
    admin_password = getpass("Yönetici parolası (varsayılan 'postgres' için boş bırakın): ") or "postgres"
    
    new_user = input("Yeni kullanıcı adı (varsayılan 'app_user' için boş bırakın): ") or "app_user"
    new_user_password = getpass(f"{new_user} için parola: ")
    db_name = "myth_busters_db"
    # sql_files = ["MythBustersBackend/src/main/resources/db/migration/V1__CREATE_TABEL.sql", 
    #              "MythBustersBackend/src/main/resources/db/migration/V2__INSERT_DATA.sql"]

    # 1. Yönetici hesabıyla bağlan
    conn = connect_to_postgres(host, port, admin_user, admin_password)
    if not conn:
        print("PostgreSQL'e bağlanılamadı. Lütfen bilgileri kontrol edin.")
        return

    try:
        # 2. Kullanıcıyı oluştur
        create_user_if_not_exists(conn, new_user, new_user_password)

        # 3. Veritabanını oluştur ve application.properties'i güncelle
        create_database_if_not_exists(conn, db_name, new_user)
        
        # 4. Yeni kullanıcıyla bağlan ve application.properties oluştur
        create_application_properties(host, port, db_name, new_user, new_user_password)
        
    finally:
        # 5. Bağlantıyı kapat
        conn.close()
        print("PostgreSQL bağlantısı kapatıldı.")

if __name__ == "__main__":
    main()