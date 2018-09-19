A Giter8 template for standing up Play Framework (2.6.x) in Scala with Slick and Postgres, including Evolutions.

**To use this template, you must have SSH credentials set up for your git bash with an account that has access, since it is a private repository.**

To get started, assuming SSH credentials are set up:
1. Set up a Postgres database on localhost.
2. Open a terminal and navigate to the parent directory where you want to create the project.
3. Execute `sbt new excellaco/play-slick-postgres.g8`
4. Navigate to the directory where you set up the project.
5. Run `sbt`
6. At sbt prompt, run `compile` then `run`
7. Check localhost:9000/docs

Note: The IDEA libraries definition is looking for Scala 2.12.6. You may need to update this definition depending
on any errors your IDE gives you.

Written in 2018 by Dale Gartman <dale.gartman@excella.com>

License information is pending, but this repository should be assumed Excella Proprietary until and unless otherwise
determined.
