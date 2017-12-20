/**
 * Created by vishruthkrishnaprasad on 21/11/17.
 */
'use strict';
let path = require('path');
app.factory('ResultService', ResultService);

function ResultService() {
    return {
        readResults: readResults,
        flushResults: flushResults
    };

    // Reads the input file for computing results.
    function readResults(fileName) {
        let resultfile = path.join(__dirname, `../${fileName}`);
        let x = fs.readFileSync(resultfile, "utf8");
        return x;
    }

    function flushResults() {
        let resultfile = path.join(__dirname, `../results.txt`);
        let visualfile = path.join(__dirname, `../visualLineDifference.txt`);
        fs.writeFileSync(resultfile, "");
        fs.writeFileSync(visualfile, "");
    }
}