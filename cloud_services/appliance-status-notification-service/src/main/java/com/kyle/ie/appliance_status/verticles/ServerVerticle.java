package com.kyle.ie.appliance_status.verticles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.kyle.ie.appliance_status.json.PAppStatusUpdatePloadJsonConverter;
import com.kyle.ie.appliance_status.models.StatusNotificationPayload;
import com.kyle.ie.appliance_status.services.IStatusNotificationService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

@Component
public class ServerVerticle extends AbstractVerticle {
	
	private static final String NOTIFICATION_ADDRESS = "com.kyle.ie.status_notification";
	private static final String EB_ENDPOINT = "/eventbus/**";
	private static final String REST_NOTIF_ENDPOINT = "/notification";
	
	private static final int SERVER_PORT = 8405;
	
	@Autowired
	private IStatusNotificationService _statNotService;

	@Override
	public void start() throws Exception {

	    Router router = Router.router(vertx);

	    // Allow events for the designated addresses in/out of the event bus bridge
	    BridgeOptions opts = new BridgeOptions()
	      .addOutboundPermitted(new PermittedOptions().setAddress(NOTIFICATION_ADDRESS));

	    // Create the event bus bridge and add it to the router.
	    SockJSHandler ebHandler = SockJSHandler.create(vertx).bridge(opts);
	    router.route(EB_ENDPOINT).handler(ebHandler);
	    
	    router.route(REST_NOTIF_ENDPOINT + "*").handler(BodyHandler.create());
	    router.post(REST_NOTIF_ENDPOINT).handler(this::notify);

	    // Start the web server and tell it to use the router to handle requests.
	    vertx.createHttpServer().requestHandler(router::accept).listen(SERVER_PORT);

	}
	
	private void notify(RoutingContext routingContext) {
		JsonObject requestBody = routingContext.getBodyAsJson();
		routingContext.response().setStatusCode(HttpStatus.OK.value());
		routingContext.response().end();
		StatusNotificationPayload pload = _statNotService.prepareNotification(new PAppStatusUpdatePloadJsonConverter().convert(requestBody));
	    EventBus eb = vertx.eventBus();
	    eb.publish(NOTIFICATION_ADDRESS, Json.encode(pload));
	}
	

}
