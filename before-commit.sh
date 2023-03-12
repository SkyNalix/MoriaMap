#!/bin/sh

if ! cd -- "$(git rev-parse --show-toplevel)"; then
  echo 'Not in a git repository'
  exit 1
fi

if ./gradlew javadoc build test; then
  echo 'No errors found, you can commit'
else
  echo 'Errors encountered, fix them before committing'
fi
