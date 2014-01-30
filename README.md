Monster Hunter Portable 3rd Miner Ranking Site
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

Installation
--------
1. Git clone latest source
1. Create database, only supported PostgreSQL

    ```bash
$ createuser -U postgres -P mh
(Default password "hunter" in context.xml)
$ createdb -U postgres -E unicode mhdb
$ cd database
$ psql -U mh mhdb
mhdb=# \i init_postgres.sql
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
