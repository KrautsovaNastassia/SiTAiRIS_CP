$( document ).ready(function() {
    hideFlights();
    hideTickets();
    authAnimate();
    addSortListeners();
    setDataInSelect();
    onDownload();
    disableLinks();
});
var isToBig = true;


function hideFlights() {
    if(location.pathname === "/WebApplication/faces/findflight.xhtml"){
        var from = $(".cityFrom").text().trim(), to = $(".cityTo").text().trim();
        var rows = $("tbody").eq(0).children("tr");
        for (var i = 0; i < rows.size(); i++) {
            var currentFrom = rows.eq(i).children("td").eq(0).text();
            var currentTo = rows.eq(i).children("td").eq(1).text();
            if(currentFrom != from || currentTo != to){
                rows[i].remove();
            }
        }
        addErrorMessage();
    }
}

function addErrorMessage() {
    if($("tbody tr").size() == 0){
        $("thead").empty();
        $("form").eq(1).prepend($("<p>", {text: "Error, no such flights were found!"}));
    }
}

function hideTickets() {
    if(location.pathname === "/WebApplication/faces/userindex.xhtml"){
        var username = $("#username").text().trim();
        var rows = $("tbody").eq(1).children("tr");
        for (var i = 0; i < rows.size(); i++) {
            var currentUsername = rows.eq(i).children("td").eq(6).text();
            if(username != currentUsername){
                rows[i].remove(); 
            }
        }
         addNotFoundMessage();
    }
}

function addNotFoundMessage() {
    if($("tbody").eq(1).children("tr").size() == 0){
        $("form").eq(2).remove();
    }
}

function authAnimate() {
    if(location.pathname === "/WebApplication/faces/auth.xhtml"){
        var auth = $("#onAuth"), registry = $("#onRegistry");
        auth.click(onAuth);
        registry.click(onRegistry);
    }
}

function onAuth(event) {
    event.preventDefault();
    $(".auth").css({display: "block"});
    $("#onAuth").addClass("disabled");
    $(".registry").css({display: "none"});
    $("#onRegistry").removeClass("disabled");
}

function onRegistry(event) {
    event.preventDefault();
    $(".auth").css({display: "none"});
    $("#onAuth").removeClass("disabled");
    $(".registry").css({display: "block"});
    $("#onRegistry").addClass("disabled");  
}

function addSortListeners(parameters) {
    $("thead tr th").click(addSorting);
}

function addSorting() {
    var id = getCurrentId(this);
    var body = $(this).parent().parent().parent().children("tbody");
    var rows = body.children("tr");
    var isIntValue = isNeedIntCheck(rows, id);
    rows.sort(function(a, b) { 
        a = $(a);   b = $(b);
        if(isMore(a.children().eq(id).text(), b.children().eq(id).text(), isIntValue)){
            return -1;
        } else if(isEqual(a.children().eq(id).text(), b.children().eq(id).text(), isIntValue)){
            return 0;
        } else {
            return 1;
        }
    }).appendTo(body);
    isToBig = !isToBig;
}

function isMore(value1, value2, isIntValue) {
    if(isIntValue) {
        value1 = Number.parseInt(value1);
        value2 = Number.parseInt(value2);
    }
    if(isToBig) {
        return value1 > value2;
    } else {
        return value1 < value2;        
    }           
}

function isEqual(value1, value2, isIntValue) {
    if(isIntValue) {
        value1 = Number.parseInt(value1);
        value2 = Number.parseInt(value2);
    }
    return value1 == value2; 
}

function getCurrentId(thisElement) {
    var currentText = $(thisElement).text();
    var fields = $(thisElement).parent().children();
    for (var i = 0; i < fields.size(); i++) {
        if(fields.eq(i).text() == currentText)  break;
    }
    return i;
}

function isNeedIntCheck(rows, id) {
    for (var i = 0; i < rows.size(); i++) {
        if(isNaN(rows.children().eq(id).text())){
            return false;
        }
    }
    return true;
}

function setDataInSelect() {
    var values = $("thead tr th");
    for (var i = 0; i < values.size()-1; i++) {
        $("select").append($("<option>", {text: values.eq(i).text()}));
    }
    $("#findField").keyup(onFind);
    $("select").change(onFind);
}

function onFind(event) {
    var id = getFieldId(), findedText = $("#findField").val().toUpperCase();
    var rows = $("tbody tr").removeClass("findedRedBorder");
    for (var i = 0; i < rows.size(); i++) {
        var currentText = rows.eq(i).children().eq(id).text().toUpperCase();
        if(currentText == findedText){
            rows.eq(i).addClass("findedRedBorder");
        }
    }
}

function getFieldId() {
    var currentText = $("select option:selected").text() ;
    var fields = $("thead tr").children();
    for (var i = 0; i < fields.size(); i++) {
        if(fields.eq(i).text() == currentText)  break;
    }
    return i;
}

function onDownload() {
    var downloads = $(".download");
    for (var i = 0; i < downloads.size(); i++) {
        var fileToDownload = "data:application/octet-stream;base64, " + btoa( generateMessage(downloads.eq(i)));
        downloads.attr("download", "YoureTicket.txt");
        downloads.attr("href", fileToDownload);  
    }
}

function generateMessage(download) {
    var cells = download.parent().parent().children("td");
    var text = "#" + cells.eq(0).text() + " " + cells.eq(1).text() + " - " + cells.eq(2).text()
        + ". Class - " + cells.eq(3).text() + ". " + cells.eq(4).text() + "$";
    return text;
}

function addPasswordInput() {
    $();
}


function disableLinks() {
    if(location.pathname === "/WebApplication/faces/userindex.xhtml"){
        var rows = $("tbody").eq(1).children("tr");
        for (var i = 0; i < rows.size(); i++) {
            if(rows.eq(i).children("td").eq(5).text() == "INACTIVE"){
                rows.eq(i).children("td").eq(7).children("a").eq(0).replaceWith(function(){
                    return $("<label>", {html: $(this).html()});
                });
            }
        }
    } 
}
