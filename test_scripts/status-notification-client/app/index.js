var EventBus = require('vertx3-eventbus-client');

var eb = new EventBus('http://192.168.1.6:8405/eventbus/');

eb.onopen = function () {
  eb.registerHandler('com.kyle.ie.status_notification', function (err, msg) {
    console.log(msg.body);
  });
};

setTimeout(function() {
  console.log("Finsihed")
}, 60000)
