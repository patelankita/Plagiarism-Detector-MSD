/**
 * Created by vishruthkrishnaprasad.
 */
'use strict';

app.controller('StatsController', StatsController);

function StatsController($state, ResultService, FileService, $rootScope) {
    let vm = this;
    let setOfCopiedLines = new Set();
    vm.inputfile = [];

    vm.swapVisual = toggleStats;
    vm.backButton = backButton;
    vm.readAnswers = readAnswers;
    vm.readJavaFile = readJavaFile;
    vm.lineCopied = lineCopied;

    /**
     * init is called when the page is loaded.
     * The visual representation is shown to the user.
     * All the results are received frmo the output of jar file.
     */
    function init() {
        vm.picRep = true;
        vm.table = false;
        vm.answers = [];
        let suspectedFile = $rootScope.suspectedFile;
        vm.suspectedFile = suspectedFile;
        vm.inputfile = readJavaFile(suspectedFile).split("\n");

        readAnswers();
        getLineNumbers();

        vm.classinput = setOfCopiedLines;
        ResultService.flushResults();
    }

    init();

    /**
     * User can choose whether he wants visual representation of the results or a tabular format.
     * @param {int} option - 1 = Visual Representation; 2 = Table Format.
     */
    function toggleStats(option) {
        if (option === 2) {
            vm.picRep = false;
            vm.table = true;
        }
        else {
            vm.picRep = true;
            vm.table = false;
        }
    }

    /**
        Allows user to go back to the Dashboard.
     */
    function backButton() {
        $state.go('dashboard');
    }

    /**
     * Reads answers from the jar file's output stored in the form of results.txt.
     * Prints the results on the page.
     */
    function readAnswers() {
        let results = ResultService.readResults("results.txt");
        let resultrange = results.split("\n");

        // Line Similarity result is converted to a readable format for the user
        if (resultrange[1] === "0.0") {
            resultrange[1] = "Match";
        }
        else {
            resultrange[1] = "Not a match";
        }

        // If there is no Structural similarity, print 0.0
        if (!resultrange[2]) {
            resultrange[2] = "0.0%";
        }

        vm.answers = {
            "Comment Comparison": resultrange[0],
            "Line Comparison": resultrange[1],
            "Structural Similarity": resultrange[2]
        };
    }

    /**
     * Read the file from the filename.
     * @param {String} fileName - can be results.txt or visualLineDifference.txt.
     */
    function readJavaFile(fileName) {
        return FileService.readGivenFile(fileName);
    }

    /**
     * Reads the visualLineDifference.txt file to mark hits sites.
     */
    function getLineNumbers() {

        let newlist = [];
        let redlines = ResultService.readResults("visualLineDifference.txt");

        // Clean the file's contents and store in an appropriate format
        let listoflines = redlines.split("File 2: ");
        cleanReadFile(listoflines, newlist);
        populateFreshList(newlist);
    }

    /**
     * Clean the contents of the read in the listoflines
     * @param {Array[]}listoflines - lines copied from original file.
     * @param {Array[]} newlist - contains line numbers of copied lines.
     */
    function cleanReadFile(listoflines, newlist) {
        for (let i = 1; i < listoflines.length; i++) {
            let lineNumber = listoflines[i].replace("Line ", "").replace("to ", "").split(" ");
            strToIntLineNumber(lineNumber, newlist);
        }
    }

    /**
     * Clean the contents of the read in the listoflines
     * @param {Array[]} lines - lines copied from original file.
     * @param {Array[]} newlist - contains line numbers of copied lines.
     */
    function strToIntLineNumber(lines, newlist) {
        for (let j = 0; j < lines.length; j++) {
            newlist.push(parseInt(lines[j].replace("\n", "")));
        }
    }

    /**
     * Add the line numbers between the given range to the setOfCopiedLines
     * @param {Array[]} newlist - contains line numbers of copied lines.
     */
    function populateFreshList(newlist) {
        let startIndex, endIndex;
        for (let i = 0; i < newlist.length - 1; i = i + 2) {
            startIndex = newlist[i];
            endIndex = newlist[i + 1];
            addToSetOfCopiedLines(startIndex, endIndex);
        }
    }

    /**
     * Add the line numbers between the given range to the setOfCopiedLines
     * @param {int} start - start line number of the copied range.
     * @param {int} end - end line number of the copied range.
     */
    function addToSetOfCopiedLines(start, end) {
        for(let j = start; j <= end; j++) {
            setOfCopiedLines.add(j-1);
        }
    }

    /**
     * Returns true if the given index is the line number that was copied
     * @param {int} index - max value of it is the last line of the suspected file.
     */
    function lineCopied(index) {
        return setOfCopiedLines.has(index);
    }

}


