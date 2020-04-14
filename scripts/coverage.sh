#!/bin/bash

mvn test
mvn jacoco:report

if [[ "$OSTYPE" == "linux-gnu" ]]; then
  xdg-open target/site/jacoco/index.html
elif [[ "$OSTYPE" == "darwin"* ]]; then
  open target/site/jacoco/index.html
else
  echo "Well, well, well. You use a weird OS for a programmer."
  echo "Open 'target/site/jacoco/index.html'"
fi
