A hands-on Spring Boot project built to explore and implement core 
JPA/Hibernate and database concepts beyond basic CRUD.

Key concepts implemented:
- Transaction management with @Transactional (commit, rollback, propagation)
- Isolation levels (READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE) with 
  live demos of dirty read, non-repeatable read, and phantom read scenarios
- Pessimistic and Optimistic locking to handle concurrent data access
- Lazy vs Eager loading strategies and resolving LazyInitializationException
- N+1 query problem — identification and fix using JOIN FETCH and Entity Graphs
- Spring Profiles (dev/prod) for environment-specific configuration
- Global Exception Handling with @ControllerAdvice

Tech Stack: Java, Spring Boot, Spring Data JPA, Hibernate, MySQL
