[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d0f04e048bba4a01a7ef0166bf5b8d32)](https://www.codacy.com/gh/codacy/codacy-pmd7?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=codacy/codacy-pmd7&amp;utm_campaign=Badge_Grade)
[![Build Status](https://circleci.com/gh/codacy/codacy-pmd7.svg?style=shield&circle-token=:circle-token)](https://circleci.com/gh/codacy/codacy-pmd7)

# Codacy PMD

This is the docker engine we use at Codacy to have [pmd](https://pmd.github.io/) support.
You can also create a docker to integrate the tool and language of your choice!
Check the **Docs** section for more information.

## Usage

#### Publish the docker

```
sbt 'set version in Docker := "dev"' 'set name := "pmd7"' docker:publishLocal
```

#### Run the docker

```
docker run --user=docker --rm=true -v <PATH-TO-CODE>:/src -v <PATH-TO>/ruleset.xml:/src/ruleset.xml pmd7:dev
```
> Make sure all the volumes mounted have the right permissions for user `docker`

#### Generate Docs

1. Update `toolVersionKey` in `build.sbt`

2. Run the DocGenerator
```sh
sbt "runMain com.codacy.pmd.DocGenerator"
```

## Docs

[Tool Developer Guide](https://support.codacy.com/hc/en-us/articles/207994725-Tool-Developer-Guide)

[Tool Developer Guide - Using Scala](https://support.codacy.com/hc/en-us/articles/207280379-Tool-Developer-Guide-Using-Scala)

## Test

We use the [codacy-plugins-test](https://github.com/codacy/codacy-plugins-test) to test our external tools integration.
You can follow the instructions there to make sure your tool is working as expected.

## Agent Playbook: Updating This Repository End-to-End

This section is written for an AI coding agent (or a human) tasked with updating this repo — most commonly bumping the wrapped PMD version, but also base image / orb / dependency bumps. Follow it top to bottom; it tells you what to change, how to regenerate derived files, how to test locally, and how to interpret CI so you can iterate on failures without guessing.

### 1. What this repository is

This is a **Codacy engine**: a thin Scala wrapper (`src/main/scala/com/codacy/Engine.scala`, built with sbt on `codacy-engine-scala-seed`) that packages [PMD](https://pmd.github.io/) (a Java-based static analyzer covering Java, Apex, JSP, JavaScript/ECMAScript, PL/SQL, Velocity, XML, Visualforce, Kotlin, Swift, etc.) as a Docker image Codacy's platform can run against a customer's source code. The `src/main/resources/docs/` directory is not just documentation — it is **machine-consumed configuration**:

- `docs/patterns.json` — the full list of PMD rules ("patterns") Codacy knows about, their parameters/defaults, categories, languages, and which are enabled out of the box. Generated file, do not hand-edit.
- `docs/description/description.json` + `docs/description/*.md` — human-readable titles/descriptions per pattern, used in the Codacy UI. Generated file, do not hand-edit.
- `docs/tests/*` and `docs/multiple-tests/*` — fixtures used by `codacy-plugins-test` to validate the engine actually produces the results it claims to for real code samples.
- `docs/tool-description.md` — short blurb about the tool, hand-maintained.

So yes, this repo follows the `patterns.json`-style pattern-engine structure. All the generated artifacts above come from **`DocGenerator`** (`src/main/scala/com/codacy/pmd/DocGenerator.scala`, run via `sbt "runMain com.codacy.pmd.DocGenerator"`). Unlike some sibling engines, this `DocGenerator` does **not** clone anything over the network — it reads the ruleset XML files (`rulesets/*` / `category/*`) that ship *inside* the `pmd-*` jars pulled in as regular sbt dependencies. This means bumping `toolVersionKey` in `build.sbt` and re-resolving dependencies is what actually changes which rules/descriptions get discovered; `DocGenerator` then just reflects whatever XML resources are on the classpath into JSON/Markdown.

### 2. Files that encode versions — check all of these on every update

| File | What it controls | What to check |
|---|---|---|
| `build.sbt` → `toolVersionKey` | The PMD release bundled (`pmd-core`, `pmd-java`, `pmd-jsp`, `pmd-javascript`, `pmd-plsql`, `pmd-velocity`, `pmd-xml`, `pmd-visualforce`, `pmd-apex`, `pmd`, `pmd-kotlin`, `pmd-swift` — all pinned to the same version) | Bump to the target PMD version. Confirm all `pmd-*` artifacts exist for that version on Maven Central. |
| `build.sbt` → `com.codacy" %% "codacy-engine-scala-seed"` | Codacy's engine SDK/base library | Check Maven Central for newer versions if asked to update it; not tied to PMD bumps. |
| `build.sbt` → `scalaVersion` | Scala compiler version | Bump only if asked, or if required by a new `codacy-engine-scala-seed`/dependency. |
| `build.sbt` → `dockerBaseImage` (`amazoncorretto:<n>-alpine<...>`) | JRE the packaged app runs on | Bump when PMD's minimum JDK requirement rises, per past commits this tends to move in lockstep with `scalaVersion`/plugin bumps — check PMD's release notes rather than bumping opportunistically. |
| `.circleci/config.yml` → `codacy/base` orb | Shared CircleCI steps (checkout, versioning, sbt build, docker publish, tagging) | Bump alongside `project/plugins.sbt`'s `codacy-sbt-plugin` — past bump commits changed both together. |
| `.circleci/config.yml` → `codacy_plugins_test` orb | Runs `codacy-plugins-test` (`run_multiple_tests: true`) in CI after the image is built | Check the latest published version if asked; usually left alone in pure PMD bumps. |
| `project/plugins.sbt` → `codacy-sbt-plugin` | sbt plugin providing the `codacy/base`-orb-compatible build tasks | Keep in sync with the `codacy/base` orb version bump. |
| `.gitignore` | Not version-bearing but has been touched incidentally in past bump commits (editor/AI-tool ignores) | Only touch if actually relevant; don't bump for its own sake. |

Look at recent bump commits for the shape of a typical diff: `git log --oneline --all | grep -iE "bump|update|upgrade|version"`, then `git show <hash>`. The most recent one (`cdd91bd`, "TCE-1230 Bump PMD7 7.16.0") touched `build.sbt` (`toolVersionKey`, `scalaVersion`, `play-json`, `codacy-engine-scala-seed`, `scala-xml`, `dockerBaseImage`), `.circleci/config.yml` (`codacy/base` orb), `project/plugins.sbt` (`codacy-sbt-plugin`), `.gitignore`, and the regenerated `docs/description/*.md` + `docs/patterns.json` + `docs/description/description.json` — all in one commit.

### 3. Step-by-step update procedure

1. **Bump `toolVersionKey`** in `build.sbt` to the target PMD version (and any of the other `build.sbt`/`.circleci/config.yml`/`project/plugins.sbt` versions in scope for the task, per the table above).
2. **Regenerate the docs.** No network access or extra tools (pandoc, git clone) are required here — the ruleset data comes from the `pmd-*` jars resolved by sbt:
   ```sh
   sbt "runMain com.codacy.pmd.DocGenerator"
   ```
   This rewrites `docs/patterns.json`, `docs/description/description.json`, and `docs/description/*.md` under `src/main/resources/docs/`. Review the diff for new/removed/renamed rules and stale fixture references in `docs/tests/*` / `docs/multiple-tests/*`.
3. **Compile / sanity build:**
   ```sh
   sbt compile
   ```
4. **Build the Docker image locally** (mirrors what CI's `publish_docker_local` job does):
   ```sh
   sbt 'set version in Docker := "dev"' 'set name := "pmd7"' docker:publishLocal
   ```
5. **Run `codacy-plugins-test` locally** before pushing — clone https://github.com/codacy/codacy-plugins-test and run its `pattern`, `json`, and `multiple` DockerTest commands (CI's `codacy_plugins_test/run` step runs with `run_multiple_tests: true`, i.e. all of them) against your local `pmd7:dev` image.
6. **Iterate on failures**, re-running only the relevant DockerTest command after each fix.
7. **Commit** the version bump(s) together with the regenerated `docs/` files in one change — this matches how every prior bump in this repo's history was committed.
8. **Push and open a PR.** CI (`.circleci/config.yml`) runs `codacy/checkout_and_version` -> `publish_docker_local` (builds the image, runs `Test / scalafmt / test`, `docker:publishLocal`, saves the image tarball) -> `plugins_test` (`codacy_plugins_test/run`, multiple tests) -> `codacy/publish_docker` (master only) -> `codacy/tag_version` (master only, requires `publish_docker`).
9. **Poll the PR's real CI checks until they all pass — local validation is NOT the finish line.** After every push, run `gh pr checks <pr-url>` and keep re-polling (short sleep while any check is `pending`) until all checks finish. If a check fails, fetch its actual log (CircleCI API/UI for the failing job — don't guess), find the true root cause, fix it, push again (never `--no-verify`, never force-push), and re-poll. Repeat until every check is green. **The CI environment's toolchain can differ from your local one**, so a clean local run does not guarantee CI passes. Only stop iterating when every check passes, or you hit a genuine product/infra decision that needs a human — in which case explain it in the PR rather than guessing.

### 4. Common failure modes and fixes

| Symptom | Likely cause | Fix |
|---|---|---|
| `DocGenerator` produces an empty or unchanged `patterns.json` | `toolVersionKey` bump didn't actually resolve new `pmd-*` jars (stale sbt cache) | Run `sbt clean` / force a fresh `sbt update` before re-running `DocGenerator` |
| `pattern`/`json` DockerTest fails after a bump | A rule was renamed/removed/added upstream in the new PMD version | Re-run `DocGenerator`; confirm the change matches PMD's release notes/changelog |
| `multiple` DockerTest fails on a specific fixture folder (`docs/multiple-tests/*`) | Expectations stale for new tool behavior | Regenerate/update the expected results to match the new (verified correct) output |
| CI `codacy/publish_docker`/`codacy/tag_version` jobs don't run on your branch | Expected — gated to the `master` branch only via the `filters: branches: only: master` clause | Nothing to fix |

### 5. Definition of done

- Version bump(s) reflected in all files that encode them (`build.sbt`, and `.circleci/config.yml` / `project/plugins.sbt` if orbs/plugins were in scope).
- `docs/patterns.json`, `docs/description/description.json`, and `docs/description/*.md` regenerated via `DocGenerator` and committed, with any fixture inconsistencies in `docs/tests/*` / `docs/multiple-tests/*` resolved.
- `sbt compile` succeeds.
- Docker image builds successfully via `docker:publishLocal`.
- `codacy-plugins-test` (`pattern`, `json`, `multiple`) all pass locally against the freshly built image.
- **After pushing and opening/updating the PR, every CI check on it is green.** Poll `gh pr checks <pr-url>` and iterate on any failure (fetch the real CI log, fix, push, re-poll) until all pass — a passing local build is not sufficient, because the CI toolchain can differ from your local one (see step 9).

## What is Codacy?

[Codacy](https://www.codacy.com/) is an Automated Code Review Tool that monitors your technical debt, helps you improve your code quality, teaches best practices to your developers, and helps you save time in Code Reviews.

### Among Codacy’s features:

 - Identify new Static Analysis issues
 - Commit and Pull Request Analysis with GitHub, BitBucket/Stash, GitLab (and also direct git repositories)
 - Auto-comments on Commits and Pull Requests
 - Integrations with Slack, HipChat, Jira, YouTrack
 - Track issues in Code Style, Security, Error Proneness, Performance, Unused Code and other categories

Codacy also helps keep track of Code Coverage, Code Duplication, and Code Complexity.

Codacy supports PHP, Python, Ruby, Java, JavaScript, and Scala, among others.

### Free for Open Source

Codacy is free for Open Source projects.
