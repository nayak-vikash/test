# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

Use the Maven wrapper (`.\mvnw.cmd` on Windows, `./mvnw` elsewhere) — the wrapper uses `distributionType=only-script`, so it downloads Maven 3.9.14 on first run.

**Set `JAVA_HOME` to a JDK 25 first.** On this machine `java` on `PATH` is JDK 17 (`C:\soft\jdk-17.0.17_windows-x64_bin\jdk-17.0.17`), but the POM sets `java.version=25`, so compilation with 17 fails — javac 17 cannot target release 25. JDK 25 is at `C:\soft\jdk-25_windows-x64_bin\jdk-25.0.4`:

```powershell
$env:JAVA_HOME = "C:\soft\jdk-25_windows-x64_bin\jdk-25.0.4"
```

```powershell
.\mvnw.cmd spring-boot:run                          # run the API on http://localhost:8080
.\mvnw.cmd test                                     # all tests
.\mvnw.cmd test -Dtest=BlogApiApplicationTests      # single test class
.\mvnw.cmd test "-Dtest=BlogApiApplicationTests#contextLoads"   # single test method
.\mvnw.cmd clean package                            # build executable jar into target/
```

`spring-boot-devtools` is on the runtime classpath, so a recompile (`.\mvnw.cmd compile`, or IntelliJ's build) restarts a running app automatically.

`BlogAPI.postman_collection.json` is the manual test surface — import it into Postman to exercise every endpoint. All endpoints take **query params**, not JSON bodies.

## Architecture

This is an EmbarkX Java/Spring Boot course starter — a deliberately minimal single-module Spring Boot app in package `com.embarkx.blogapi`:

- `BlogApiApplication` — standard `@SpringBootApplication` entry point.
- `BlogController` — the entire application. One `@RestController` at `/api/posts` holding a `private static List<String> posts`. Posts are encoded as the raw string `title + ":" + content`, and the path `{id}` is a **list index**, not an identity.

There is no service layer, no repository, no entity/DTO types, and no database. `application.properties` sets only `spring.application.name`. When adding features, expect to introduce these layers rather than find them.

### The bugs are intentional

The Postman collection names them explicitly ("Bug: crashes", "Bug: hardcoded 5000", "Bug: string concat"). This repo is exercise material for spotting and fixing them — missing input validation on `createPost`, unguarded `posts.get(id)` / `posts.remove(id)` throwing `IndexOutOfBoundsException` on bad indices, the magic `5000` in `validateContent`, and `getTotalWordCount` concatenating `"100" + "200" + "300"` into `"100200300"` instead of summing. **Do not silently "clean these up"** while doing unrelated work; fix them when asked, and keep the corresponding Postman requests in sync.

### Spring Boot 4 / Java 25 specifics

- Parent is `spring-boot-starter-parent:4.0.5` on Java 25. Boot 4 renamed the web starters: this project uses **`spring-boot-starter-webmvc`** (not `spring-boot-starter-web`) and **`spring-boot-starter-webmvc-test`** (not `spring-boot-starter-test`). Follow Boot 4 conventions — much Boot 2/3 guidance and many older examples will not apply verbatim.
- The POM wires Lombok as an `annotationProcessorPaths` entry in `maven-compiler-plugin` and excludes it from the repackaged jar, but Lombok is **not declared as a dependency**. Lombok annotations will not resolve until a `org.projectlombok:lombok` (provided/optional) dependency is added.
