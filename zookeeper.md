# Zookeeper

### What is Zookeeper?

- Internal com.schachte.autocomplete.data structure represented as a tree
- Each zNode has a path
- zNode can be persistent or ephemereal
- Each zNode can store com.schachte.autocomplete.data
- You can watch for changes within each zNode

### Role of Zookeeper in Kafka?

- Broker registration with heartbeat mechanisms
- Maintains a list of topics alongside:
  - Their config (partitions, replication factor, additional config)
  - The list of ISRs (in-sync replicas) for partitions
- Performs leader elections in case some brokers go down
- Stores the kafka cluster id 
- Handles ACLs
  - topics
  - consumer groups
  - users

### Zookeeper Architecture Quorum Sizing

`How many zookeepers do you actually need?`

- Needs to have a strict majority of servers up to form a strict majority when votes happen
- You need 2N+1 servers up to enable majority voting
- If you have 2N+1 servers, then N number of servers can go down

