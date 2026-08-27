# Piscine Java

**A solo Java project inspired by 42’s methodology**

From basic language constructs to a production-shaped money-transfer system — one domain, sixteen progressive modules.

---

## What this is

This is a self-directed learning project that follows the pedagogical style of 42 (one persistent domain, progressive difficulty, concrete implementation before abstraction).

It is **not** an official 42 piscine. It is a personal reconstruction of the structure and ideas of the Java curriculum, centred on a single money-transfer domain that is first explored in SQL (see the companion **Ledger** project) and then reimplemented in Java.

The same business objects grow from in-memory collections all the way to a containerised Spring Boot service with authentication, optimistic locking, an immutable event store, cache invalidation and rate limiting.

---

## The domain spine

Every major module works on the same business objects:

- `User` (id, name, balance)
- `Transaction` (UUID, sender, recipient, amount, debit/credit)

A transfer is stored as a debit/credit pair sharing the same identifier. The classic “unacknowledged transfer” problem (a transfer recorded for only one of the two users) is solved first in SQL with `EXCEPT` and later in Java by walking two collections.

That spine is then extended:

| Modules | What is added |
|---------|----------------|
| 00–01 | Language fundamentals + domain models + collections |
| 02–03 | Threads, concurrency primitives, ExecutorService |
| 04–09 | I/O, sockets, wire protocols |
| 10–12 | Spring Boot, REST, JDBC / JPA |
| 13 | Migrations, indexes, locking, cursor pagination |
| 14 | Password hashing, JWT, secure upload, Docker |
| 15 | Ports & Adapters, immutable audit log, cache, metrics, rate limiting |

---

## Module overview

### 00 — Language fundamentals
Strict constraints (no user-defined classes except static helpers). Sum of digits, primality, stream processing until a sentinel, histograms, simple timetable.

### 01 — OOP & Collections
Domain modelling under the single-responsibility principle. Custom lists, business-logic layer that can detect unacknowledged transfers, interactive menu.  
Optional deepening: replace `double` with `BigDecimal` (scale 2, banker’s rounding) and measure accumulated error.

### 02–03 — Threads
From raw `Thread` / `Runnable` and a hand-rolled worker pool to `ExecutorService` + `CompletableFuture` pipelines (timeouts, fallbacks, `allOf`).

### 04–09 — I/O & Sockets
File signatures (magic numbers), multi-threaded chat, JSON exchange, and a strict tag-value wire protocol with checksum validation.

### 10–12 — Spring Boot & Persistence
The domain becomes an HTTP API. JDBC then JPA mapping of the same objects. Database-generated primary keys replace the earlier singleton ID generator.

### 13 — Advanced Persistence
- Versioned migrations with Flyway (editing an already-applied migration must fail)
- N+1 problem demonstrated and fixed with `@EntityGraph` / `JOIN FETCH`
- `EXPLAIN ANALYZE` before and after a covering index
- Optimistic (`@Version` + retry) vs pessimistic (`SELECT … FOR UPDATE`) locking under 100 concurrent debits
- Cursor pagination vs `OFFSET` on a 200 k-row table

### 14 — Security & Containers
- BCrypt password hashing (failed login never reveals whether the user existed)
- JWT access + opaque refresh tokens
- Secure file upload (magic-byte validation + path normalisation)
- Multi-stage Dockerfile (JDK build → JRE runtime, non-root, healthcheck) + Compose waiting on Postgres readiness

### 15 — Architecture, Audit & Observability
- Ports & Adapters layout (`domain` packages import neither Spring nor JPA)
- Sealed event hierarchy + append-only store + `reconstituteBalanceAt(userId, instant)`
- Cache invalidation on every balance-changing operation
- Structured logging with request-scoped ID in MDC + Micrometer metrics
- Token-bucket rate limiter keyed by authenticated user (not by IP)

---

## Design decisions the project forces you to feel

1. **Client-side arithmetic is the Lost Update.**  
   Writing `balance = balance - 20` is safe under `READ COMMITTED`; writing a literal computed from a stale read is not. That is exactly what most ORM `save(entity)` calls do — which is why optimistic locking appears.

2. **One JVM is not a distributed system.**  
   An in-memory rate limiter works until the service sits behind a load balancer. The Redis module in the Ledger project is the distributed counterpart.

3. **The same question, two tools.**  
   A running balance can be a window function or an event fold. Having built both turns the trade-off discussion into something concrete.

4. **Events are never updated.**  
   If a fact was wrong, a later event corrects it; the record of the mistake stays. The same rule is enforced in the SQL schema by using `status = 'REVERSED'` instead of `DELETE`.

---

## Repository layout

```
├── day00/ … day15/
├── extensions/
│   ├── precision-bigdecimal/
│   ├── executor-completablefuture/
│   └── wire-protocol/
└── docker/
    ├── Dockerfile
    └── docker-compose.yml
```

Each day contains the source, a short note on non-obvious choices, and (where relevant) before/after measurements.

---

## Tech stack

- Java 21 (LTS)
- Spring Boot, Spring Security, Spring Data JPA
- Flyway
- PostgreSQL
- Docker / Docker Compose
- JUnit / Mockito
- Micrometer

---

## Status

Personal / solo project — ongoing.

Inspired by the teaching methodology of 42 School (progressive projects, one persistent domain, emphasis on understanding mechanisms rather than memorising frameworks). Not an official 42 curriculum item and not subject to any formal evaluation.

---

**Companion project:** [Ledger (SQL / Data Engineering)](https://github.com/JBYoussef/ledger)  
**Author:** [José Bofengola](https://github.com/JBYoussef)
