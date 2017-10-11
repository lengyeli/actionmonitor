$(document).ready(function(){
    var stompClient = null;

    // function sendName() {
    //     stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
    // }

    function showDbActionEvents(message) {
        $("#dbChangeEvents").append("<tr><td>" + message + "</td></tr>");
    }

    $(function () {
        var socket = new SockJS('/action-monitor-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {

            $("#titleLabel").text('Websocket connection established');
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/actionmonitor', function (websocketMessage) {
                showDbActionEvents(JSON.parse(websocketMessage.body).content);
            });
        });
    });
});