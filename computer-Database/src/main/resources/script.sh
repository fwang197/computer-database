mysql -uroot -psql < ~/Documents/spec-cdb-master/config/db/1-SCHEMA.sql 

mysql -uroot -psql < ~/Documents/spec-cdb-master/config/db/2-PRIVILEGES.sql 

mysql -uroot -psql computer-database-db < ~/Documents/spec-cdb-master/config/db/3-ENTRIES.sql 
