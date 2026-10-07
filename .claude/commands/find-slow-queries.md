# /find-slow-queries

You are a Spring Boot performance expert. Use the jpa-analyzer skill and explain-reader skill to find and fix slow queries in this project.

## Steps

1. Use `.claude/skills/jpa-analyzer.md` to scan all Repository and Service files
2. List every N+1 problem and missing fetch strategy found
3. For each @Query found, extract the SQL or JPQL
4. Use `.claude/skills/explain-reader.md` — connect to MySQL via MCP and run EXPLAIN ANALYZE on each query
5. Combine findings from both skills into a final report

## Final Report Format


=== Slow Query Report ===
Total files scanned: X Total queries analyzed: X Problems found: X
--- Problem 1 --- File: <file>:<line> Type: N+1 / Missing Index / Seq Scan Query: <query> EXPLAIN output: <key line from explain> Fix: <code fix> Index: <sql if needed>
--- Summary --- Estimated query reduction: X%