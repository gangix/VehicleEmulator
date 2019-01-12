DB Issues:
	I used Embedded H2 DB.
	I created 3 tables for Transactions as (Refuel,Alert,Incident)
	Spring JPA auto creates tables under "jdbc:h2:mem:testdb"

Application Issues:
	I used Spring ApplicationEvent to call method per 5 second. 
	
	When will two vehicles report incident? It wasn t clear so if two cars have less than %25 remaining gas 
	i decided they will reported as incident. Checked per 30 seconds. 
	
	When will a vehicle report as defected? It wasn t clear so i decided every 30 mins a vehicleType tank size
	decreases as %3.
	

