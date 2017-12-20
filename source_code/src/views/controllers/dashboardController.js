/**
 * Created by vishruthkrishnaprasad.
 */
'use strict';

const {dialog} = require('electron').remote;
app.controller('DashboardController', DashboardController);

function DashboardController($state, FileService, $scope, $q, $rootScope) {

    let vm = this;
    let file1 = null;
    let file2 = null;
    let file1content = null;
    let file2content = null;

    vm.viewStats = viewStats;
    vm.getPath = getPath;
    vm.readJavaFile = readJavaFile;
    vm.upload = upload;


    /**
     * init is called when the page is loaded.
     * The show results button is disabled initially.
     */
    function init() {
        vm.showResults = false;
        $(".fa-pulse").hide();
    }

    init();

    /*
        Moves the user to Statistics page
     */
    function viewStats()
    {
        $state.go('statistics');
    }

    /*
        Allows user to browse the files from their local system.
      */
    function browseFile()
    {
        const deferred = $q.defer();
        dialog.showOpenDialog((fileNames) => {
            $scope.filenames = fileNames;
            deferred.resolve(fileNames);
        });
        return deferred.promise;
    }

    /**
     * Stores the path of the file chosen and reads its contents.
     * @param {int} fileNumber - Indicates if file is original or the suspected one.
     */
    function getPath(fileNumber) {
        browseFile(fileNumber)
            .then(function success(fileNames) {
                // Original File
                if (fileNumber === 1) {
                    file1 = fileNames[0];
                    vm.file1 = file1;
                    readJavaFile(vm.file1, 1);
                }
                // Suspected File
                else {
                    file2 = fileNames[0];
                    vm.file2 = file2;
                    readJavaFile(vm.file2, 2);
                }
            });
    }

    /**
     * Reads the contents of the java files.
     * @param {String} fileName - File name that is being read.
     * @param {int} value - Indicates if file is original or the suspected one.
     */
    function readJavaFile(fileName, value) {
        // Checks if the uploaded file has a .java extension
        if (fileName.includes(".java")) {
            // Original File
            if (value === 1) {
                vm.log = "";
                file1content = FileService.readGivenFile(fileName);
            }
            // Suspected File
            else {
                file2content = FileService.readGivenFile(fileName);
                $rootScope.suspectedFile = fileName;
                vm.log = "";
            }
        }
        // For a file with no .java extension [Assumed as a non java file]
        else {
            vm.log = "Please choose java files ONLY";
        }
        vm.file1content = file1content;
        vm.file2content = file2content;
    }

    /**
     * Uploads the two files and feeds the same to the jar file.
     * When the jar file is being executed, a spinner is shown to indicate that the files are being processed.
     */
    function upload() {
        // If Original File is not uploaded
        if (file1 === null) {
            vm.log = "Please upload file1 [JAVA file]";
        }
        // If Suspected File is not uploaded
        else if (file2 === null) {
            vm.log = "Please upload file2 [JAVA file]";
        }
        else {
            vm.log = "";
            // Spinner is shown
            $(".fa-pulse").show();

            // Communicates with the jar file
            FileService.sendToJar(file1, file2)
                .then(function success(result) {
                        // Spinner is hidden
                        $(".fa-pulse").hide();
                        vm.showResults = true;
                    },
                    function error(err) {
                        // Spinner is hidden
                        $(".fa-pulse").hide();
                        vm.log = "Please re-upload parsable java files";
                    });
        }
    }
}