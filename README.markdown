A Giter8 template for standing up Play Framework (2.6.x) in Scala with Slick and Postgres, including Evolutions.

To get started:
1. Set up a Postgres database on localhost.
2. Open a terminal and navigate to the parent directory where you want to create the project.
3. Execute `sbt new excellaco/play-slick-postgres.g8` and enter the desired parameters
(example DB URL: localhost/playdb)
4. Navigate to the directory where you set up the project.
5. Run `sbt`
6. At sbt prompt, run `compile` then `run`
8. Check localhost:9000/api-docs/

To run tests, run `test` at the sbt prompt.

To run Scala style checks and tests and generate a coverage report,
run `sbt clean scalastyle coverage test coverageReport`.

If packaging or distributing afterward, run `sbt clean test`
(scoverage might leave behind problematic artifacts in distributions if not cleaned).

Written in 2018 by Dale Gartman <dale.gartman@excella.com>
