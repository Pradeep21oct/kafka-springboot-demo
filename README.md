# kafka-springboot-demo
kafka Spring boot demo
cd kafka_2.12-2.1.1/
How to start Zookeeper Server on console:
    zookeeper-server-start  config/zookeeper.properties
		
how to start kafka Server :
        kafka-server-start config/server.properties 
				
How to run Console consumer:
        kafka-console-consumer --bootstrap-server 127.0.0.1:9092 --topic first_topic --group my-first-Application
				

How to run console produce:
        kafka-console-producer --broker-list 127.0.0.1:9092 --topic first_topic


