## HowTo run an example
- mvn clean install
- java -jar target/batch-0.0.1-SNAPSHOT.jar

## HowTo enable the MySQL schema for JobRepository
- Spin-up the MySQL in Docker
- Find the schema generation example **schema-mysql.sql** with `<Ctrl> + <Shift> + N`
- Open the DB client (in my case `SQLectron`) and execute the sequence of steps:
```
create database spring_batch_test;
USE spring_batch_test;

<execute the whole schema-mysql.sql>
```
- Check that the schema is valid (as soon as jobs are done, there will be some records in this table)
```
USE spring_batch_test;

select * from BATCH_JOB_EXECUTION;
```