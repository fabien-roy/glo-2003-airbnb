#!/usr/bin/env node

'use strict';

const raml2html = require('raml2html');
const path = require('path');
const fs = require('fs');

const ramlFile = path.join(__dirname, 'api.raml');
const config = raml2html.getConfigForTheme();

const logError = (error) => console.log('error : ' + error);

raml2html.render(ramlFile, config).then(
  result => {
    let htmlFile = path.join(__dirname, 'api.html');
    fs.writeFile(htmlFile, result, error => {
      if (error) logError(error);
    })
  },
  error => {
    logError(error);
  }
);

