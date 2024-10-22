# practical-

intall mysql 
create mysql schema name=dp
now change password in application properties

install kafka in your system and run 
now create three topic 

bin\kafka-topics.bat --create --topic student-create-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

bin\kafka-topics.bat --create --topic student-update-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

bin\kafka-topics.bat --create --topic student-delete-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

run this spring boot project and first create university and add student , and perfom crud operation
