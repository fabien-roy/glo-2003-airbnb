# Contributing to GLO-2003 (H2020) - Team 8 project

**Contributions are welcome!**

## Code of conduct

Before contributing to the project, please read our [code of conduct](https://github.com/glo2003/glo2003-h2020-eq08/blob/master/CODE_OF_CONDUCT.md).

## Code style

We use (Google Java Code Style)[https://google.github.io/styleguide/javaguide.html]. It is checked pre-commit and during CI check. To format code, use `mvn git-code-format:format-code-DglobPattern=**/*`.

## Issues

We track our issues with GitHub issues. Each issue must have at least one person assigned, a date of delivery, an associated milestone and correct labels.

Milestones represent release numbers. For example, release 1 would be milestone `1`. `1.1` would be release 1 hotfix.

## Test driven development

Every single piece of code added to the application must be written using test driven development. For TDD, we follow the tree basic steps : write failing tests for new feature, write basic code to get tests to pass and finally reformat newly added code. Once the new feature is correctly implemented, commit.

Tests are located in `src/test/java/ca/ulaval/glo2003`. The package naming must be identitical to `src/main/java/ca/ulaval/glo2003`. Test class names must have a `Test` prefix. For instance, the test class for `Main.java` would be `MainTest.java`

## Bug reporting

When a bug is spotted in the application, it must be reported as an issue on GitHub issues. There is a `bug` label.

## Pull requests

We use trunk based development with `master` as a main branch. Every PR adding a feature to the application or solving a bug must be merged into `master`.

For each issue, there must be at least one PR (more PRs could be added if the issue is reopened). This PR must build and pass the CI check. Also, two reviewers must approve the PR before it is merged into `master`.

The one in charge of merging the PR is the one in charge of the associated issue.

## When is a milestone achieved / Definition of done

A milestone is achieved once every of its issues are solved. This includes everything to add for a new release, from adding features, to solving bugs and improving performance.

## Contributors

- Pakendam Bigou-Lare / bigpkd
- Thomas Fenot / arowwa
- Hamza Ben Kirane / flyingwhalez
- Fabien Roy / ExiledNarwal28
- Sandrine Diffo Lambo / sandrasandra0
- Hugo Lafleur / hugeflower
- Vincent Masse / vince2848
- Christophe Robillard / Robzilla888
