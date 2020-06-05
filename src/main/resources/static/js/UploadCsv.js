function uploadOnChange(file) {
    let filename = file.value;
    let lastIndex = filename.lastIndexOf("\\");
    if (lastIndex >= 0) {
        filename = filename.substring(lastIndex +1);
    }
    document.getElementById('fileName').value = filename;
}

function clear(){
    document.getElementById('fileName').value = "";
}