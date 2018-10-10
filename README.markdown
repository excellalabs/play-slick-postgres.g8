[![Build Status](https://travis-ci.org/excellaco/play-slick-postgres.g8.svg?branch=master)](https://travis-ci.org/excellaco/play-slick-postgres.g8)

# play-slick-postgres.g8

## What is it?
A Giter8 seed (template) for standing up Play Framework (2.6.x) in Scala with Slick and Postgres, including Evolutions.
The seed will generate a working sample app that you can then update to suit your needs.
It includes a Swagger UI for API documentation, a few helpful test libraries, and Statement Coverage testing.
Scalafmt is run in the compile stage, with the default Scalafmt settings.
Tests are included for the example API.

## How do I use it?

### Quickstart
1. Set up a Postgres database on localhost. If you don't want to install Postgres, you can use a docker image by following the instructions here: [Docker Hub Link](https://hub.docker.com/_/postgres/).
2. Open a terminal and navigate to the parent directory where you want to create the project.
3. Execute `sbt new excellaco/play-slick-postgres.g8` and enter the desired parameters
(example DB URL: localhost/playdb)
4. Navigate to the directory where you set up the project.
5. Run `sbt` to open the sbt prompt.
6. At sbt prompt, run `compile` then `run` to start the play app. 
8. Check http://localhost:9000/api-docs/

*Note: to use a branch of this repository as your seed, you can simply append `--branch branch-name` when using `sbt new`*

### Running Tests
To run tests, run `test` at the sbt prompt.

To run Scala style and format checks, run tests, and generate a coverage report, within sbt:
`> clean
> scalastyle
> scalafmt::test
> coverage
> test
> coverageReport
> coverageOff`

The last step is important (scoverage might cause issues in distributions if left on).
If you want to be extra careful, run a `clean` as well.

### My build is failing! Help!
If your build fails and it's not due to a compilation error, you may want to check
if the style and coverage settings are too stringent for you.

-----
Written in 2018 by Dale Gartman <dale.gartman@excella.com>
