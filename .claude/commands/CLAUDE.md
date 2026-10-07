# Our Java/Spring Boot Standards
- Use constructor injection, no @Autowired on fields
- All REST endpoints must have @Valid on request bodies
- Every public method needs a unit test
- No raw SQL — use JPA or named queries only
- Services must not call repositories from other domains


## JPA & Query Standards
- No FetchType.EAGER on @OneToMany or @ManyToMany
- All @OneToMany relationships must use @EntityGraph at query level
- No child collection access inside loops — always fetch upfront
- Every foreign key column must have an index
- Any WHERE clause column on a table >1000 rows needs an index