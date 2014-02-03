MHP3 Miner Ranking Site
=========

Monster Hunter Portable 3rd (MHP3) is a major game on PSP.
The player mine for rare items in the game.

This site provides Miner Ranking that obtained rare items.

- Manage obtained rare items and display your rank
- Public / Private your rank

Requirement
--------
- Java 7 (Probably work on Java 6)
- Tomcat 7.x (Probably work on Tomcat 6.x or other application servers)
- PostgreSQL 9.x (Probably work on PostgreSQL 8.x or lower)
    - or MySQL 5.x (Probably work on MySQL 4.x or lower)

Installation
--------
1. Git clone latest source
1. Create database

    * PostgreSQL

    ```bash
$ createuser -U postgres -P mh
(Default password "hunter" in context.xml)
$ createdb -U postgres -E utf8 mhdb
$ cd database
$ psql -U mh mhdb
mhdb=# \i init_postgres.sql
```

    * MySQL

    ```bash
$ mysql -u root -p
mysql> CREATE DATABASE mhdb DEFAULT CHARACTER SET utf8;
mysql> CREATE USER 'mh'@'localhost' IDENTIFIED BY 'hunter';
(Default password "hunter" in context.xml)
mysql> GRANT ALL PRIVILEGES ON mhdb.* TO 'mh'@'localhost';
mysql> FLUSH PRIVILEGES;
mysql> exit
$ cd database
$ mysql -u mh -p mhdb
mysql> source init_mysql.sql
```

1. Build with Ant

    ```bash
$ cp build.properties.sample build.properties
$ vi build.properties
tomcat.dir=/path/to/tomcat
$ ant
$ ant deploy
```

1. You can access to the MHP3 Miner Ranking Site

    ```bash
http://localhost:8080/mhp3-miner-ranking/
```
