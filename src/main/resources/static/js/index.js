$( window ).ready(function() {
  connect();
  $( "#send" ).click(function() { sendMessage(); });
});

var stompClient = null;

function connect() {
    var socket = new SockJS('http://localhost:8082/websocket');
      stompClient = Stomp.over(socket);

  stompClient.connect({}, function (frame) {
      stompClient.subscribe('/topic/pushNotification', function (notification) {
          $('#textArea').val(notification);
       });
  });

}
function sendMessage(){
    stompClient.send("/app/notificationHaWebSocketDoor", {}, JSON.stringify({'message': $("#sendArea").val()}));
}