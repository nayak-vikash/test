Review PR #$ARGUMENTS against our standards in CLAUDE.md and .claude/commands/CLAUDE.md.
Use the `gh` CLI (authenticated via GH_TOKEN). The repo is in $GITHUB_REPOSITORY (owner/name).
1. Fetch the diff for PR #$ARGUMENTS with `gh pr diff $ARGUMENTS` and the head commit SHA with `gh pr view $ARGUMENTS --json headRefOid -q .headRefOid`
2. Check every changed Java file against the standards in CLAUDE.md and .claude/commands/CLAUDE.md
3. Post inline comments on each violation with a suggested fix, as a single review:
   `gh api repos/$GITHUB_REPOSITORY/pulls/$ARGUMENTS/reviews --input review.json`
   where review.json has `commit_id`, `event` ("COMMENT"), `body` (the final summary comment), and `comments` (array of `{path, line, side: "RIGHT", body}`)
4. The review `body` is the final summary: a table of each standard with pass/fail, plus any other issues found
