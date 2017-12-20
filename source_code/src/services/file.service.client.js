'use strict';
const spawn = require('child_process').spawn;
app.factory('FileService', FileService);

function FileService($q) {
    return {
        readGivenFile: readGivenFile,
        sendToJar: sendToJar
    };

    // Reads the input file for computing results.
    function readGivenFile(filename) {
        return fs.readFileSync(filename, "utf8");
    }

    // Jar receives 2 input files as input and executes plagiarism detection.
    function sendToJar(fileName1, fileName2) {
        const deferred = $q.defer();
        const jar = spawn('java', ['-jar', `${__dirname}/jarFiles/backend_jar/backend.jar`, fileName1, fileName2]);
        jar.stdout.on('data', (data) => {
            deferred.resolve(1);
        });
        jar.stderr.on('data', (data) => {
            deferred.reject();
        });
        jar.on('close', (code) => {
            deferred.resolve();
        });
        return deferred.promise;
    }
}