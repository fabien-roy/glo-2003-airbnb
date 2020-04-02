# Contributing to GLO-2003 (H2020) - Team 8 project

**Contributions are welcome!**

## Code of conduct

Before contributing to the project, please read our [code of conduct](CODE_OF_CONDUCT.md). Our [team contract](Contrat%20d'%C3%A9quipe.pdf) is also a good insight of our team's rule, but it is completely in french.

## Task tracking

### User stories

Documentation about user stories is placed in [/docs](/docs). This is where we place the established technical requirements for each release.

### Issues

We track our issues with GitHub issues. Each issue must have at least one person assigned, a date of delivery, an associated milestone and correct labels.

Milestones represent release numbers. For example, release 1 would be milestone `1`. `1.1` would be release 1 hotfix.

### Project board

Issues must be placed on the [project board](https://github.com/glo2003/glo2003-h2020-eq08/projects/1). There are 5 columns in this board : 

- Maybe : Optional issues that are not prioritized in the current sprint
- ToDo : Issues that must be done to deliver the current iteration
- InProgress : Self-explanatory
- InReview : Issues currently in review
- Done : Closed issues (see : Definition of done)

The person in charge of an issue is in charge of moving it across the project board.

### Bug reporting

Everything in the app is unit tested. We also have requests and tests to run in Postman to mimic integration tests. It is placed in [/resources](/resources).

When a bug is spotted in the application, it must be reported as an issue on GitHub issues. There is a `bug` label. It must be added in the ToDo column of the project, above all non-bugs issues, ordered by priority.

### Pull requests

We use trunk based development with `master` as a main branch. Every PR adding a feature to the application or solving a bug must be merged into `master`.

For each issue, there must be at least one PR (more PRs could be added if the issue is reopened). This PR must build. Also, two reviewers must approve the PR before it is merged into `master`. Once it is merged, it will have to pass CI and CD check on `master`.

To follow this trend, PR names are as following : `[CODE] What is added` (ex : `[AUL] Convert bed request`).

The one in charge of merging the PR is the one in charge of the associated issue.

To review a PR is a lot of things. First, you must read each added line, understand them, make sur they make sense and point out if there is any way to improve it. You must then pull the branch, test the app, make sure it works in execution using Postman and call it a day. Only approve PRs that are 100% ready to merge. Otherwise, request changes explaining clearly what must be added for approval.

### When is a milestone achieved / Definition of done

A milestone is achieved once every of its issues are solved. This includes everything to add for a new release, from adding features, to solving bugs and improving performance.

For issues, they are closed once all described tasks are confirmed done be team (closed PR). 

## Development

### Code style

We use [Google Java Code Style](https://google.github.io/styleguide/javaguide.html). It is checked pre-commit and during CI check. To format code, use `mvn git-code-format:format-code-DglobPattern=**/*`.

No comment should be in the source code. Some exceptions are small explanations. In those rare cases, comments are clear and tiny.

TODOs are okay, as long as they do not make it to the release. They can be used to mark where a certain issue must be done (in which case, an issue number is much appreciated). In almost all other cases, they should be removed an converted to an actual issue.

### Test driven development

Every single piece of code added to the application must be written using test driven development. For TDD, we follow the tree basic steps : write failing tests for new feature, write basic code to get tests to pass and finally reformat newly added code. Once the new feature is correctly implemented, commit.

Tests are located in `src/test/java/ca/ulaval/glo2003`. The package naming must be identical to `src/main/java/ca/ulaval/glo2003`. Test class names must have a `Test` prefix. For instance, the test class for `Main.java` would be `MainTest.java`

Unit tests must have sections Arrange-Act-Assert separated by one blank line. Set up of tests must be extracted as much as possible from unit tests.

### Git

Normally, every branch is a fork of `master`. Some exceptions are features building upon PRs that are not done being reviewed.

Our branch names are as following : `code_whatIsAdded` (ex : `aul_convertBedRequest`). Always camelcase.

We try, as must as possible, to format commit messages as following : `[CODE] What is added` (ex : `[AUL] Add empty BedRequest class`)

## Contributors

- Pakendam Bigou-Lare / bigpkd
- Thomas Fenot / arowwa
- Hamza Ben Kirane / flyingwhalez
- Fabien Roy / ExiledNarwal28
- Sandrine Diffo Lambo / sandrasandra0
- Hugo Lafleur / hugeflower
- Vincent Masse / vince2848
- Christophe Robillard / Robzilla888
