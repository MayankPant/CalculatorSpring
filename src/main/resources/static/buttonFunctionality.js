function insertText(elemID, text) {
        var elem = document.getElementById(elemID);
        elem.value += text;
    }
        function clearText(elemID) {
        elem.value = '';
    }
        function clearChar(elemID) {
        var elem = document.getElementById(elemID);
        let exp = elem.value;
        let len = elem.value.length;
        elem.value = exp.substring(0, len - 1);
    }