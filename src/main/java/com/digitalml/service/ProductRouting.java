package com.digitalml.service;

import static spark.Spark.*;
import spark.*;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Context.Builder;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;

import static net.logstash.logback.argument.StructuredArguments.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ProductRouting {

    private static final Logger logger = LoggerFactory.getLogger("product:1");

	public void foo() {}
	
    public static void main(String[] args) {
    
        get("/ping", (req, res) -> {
            return "pong";
        });
        
        get("/halt", (request, response) -> {
			stop();
			response.status(202);
			return "";
		});
		
        // Handle timings
        
        Map<Object, Long> timings = new ConcurrentHashMap<>();
        
        before(new Filter() {
        	@Override
        	public void handle(Request request, Response response) throws Exception {
        		timings.put(request, System.nanoTime());
        	}
        });
        
        afterAfter(new Filter() {
        	@Override
        	public void handle(Request request, Response response) throws Exception {
        		long start = timings.remove(request);
        		long end =  System.nanoTime();
        		logger.info("log message {} {} {} {} ns", value("apiname", "product"), value("apiversion", "1"), value("apipath", request.pathInfo()), value("response-timing", (end-start)));
        	}
        });
        
        get("/policy", (req, res) -> {
        
			Handlebars handlebars = new Handlebars();
			Map<String, Map> content = new HashMap<>();
			List<HttpResponse<String>> responses = new ArrayList<>();

			Map inputs = new HashMap<>();
			for (Map.Entry<String, String> x : req.params().entrySet()) {
                inputs.put(x.getKey().substring(1), x.getValue());
            }
			for (Map.Entry<String, String[]> x : req.queryMap().toMap().entrySet()) {
				inputs.put(x.getKey(), x.getValue()[0]);
			}


            if (responses.size()>0) {
            
                System.out.println(responses.get(0).getStatus());
                System.out.println(responses.get(0).getStatusText());
                System.out.println(responses.get(0).getBody());
            
			    return responses.get(0).getStatus() + " " + responses.get(0).getStatusText() + " " + responses.get(0).getBody();
            }

            StringBuffer sb = new StringBuffer();
            return "Get Policy Info by Registration " + sb.toString();
        });
        post("/images", (req, res) -> {
        
			Handlebars handlebars = new Handlebars();
			Map<String, Map> content = new HashMap<>();
			List<HttpResponse<String>> responses = new ArrayList<>();

			Map inputs = new HashMap<>();
			for (Map.Entry<String, String> x : req.params().entrySet()) {
                inputs.put(x.getKey().substring(1), x.getValue());
            }
			for (Map.Entry<String, String[]> x : req.queryMap().toMap().entrySet()) {
				inputs.put(x.getKey(), x.getValue()[0]);
			}


            if (responses.size()>0) {
            
                System.out.println(responses.get(0).getStatus());
                System.out.println(responses.get(0).getStatusText());
                System.out.println(responses.get(0).getBody());
            
			    return responses.get(0).getStatus() + " " + responses.get(0).getStatusText() + " " + responses.get(0).getBody();
            }

            StringBuffer sb = new StringBuffer();
            return "Upload Claim Support Image " + sb.toString();
        });
        post("/tow", (req, res) -> {
        
			Handlebars handlebars = new Handlebars();
			Map<String, Map> content = new HashMap<>();
			List<HttpResponse<String>> responses = new ArrayList<>();

			Map inputs = new HashMap<>();
			for (Map.Entry<String, String> x : req.params().entrySet()) {
                inputs.put(x.getKey().substring(1), x.getValue());
            }
			for (Map.Entry<String, String[]> x : req.queryMap().toMap().entrySet()) {
				inputs.put(x.getKey(), x.getValue()[0]);
			}


            if (responses.size()>0) {
            
                System.out.println(responses.get(0).getStatus());
                System.out.println(responses.get(0).getStatusText());
                System.out.println(responses.get(0).getBody());
            
			    return responses.get(0).getStatus() + " " + responses.get(0).getStatusText() + " " + responses.get(0).getBody();
            }

            StringBuffer sb = new StringBuffer();
            return "RequestTowruck " + sb.toString();
        });
        post("/", (req, res) -> {
        
			Handlebars handlebars = new Handlebars();
			Map<String, Map> content = new HashMap<>();
			List<HttpResponse<String>> responses = new ArrayList<>();

			Map inputs = new HashMap<>();
			for (Map.Entry<String, String> x : req.params().entrySet()) {
                inputs.put(x.getKey().substring(1), x.getValue());
            }
			for (Map.Entry<String, String[]> x : req.queryMap().toMap().entrySet()) {
				inputs.put(x.getKey(), x.getValue()[0]);
			}


            if (responses.size()>0) {
            
                System.out.println(responses.get(0).getStatus());
                System.out.println(responses.get(0).getStatusText());
                System.out.println(responses.get(0).getBody());
            
			    return responses.get(0).getStatus() + " " + responses.get(0).getStatusText() + " " + responses.get(0).getBody();
            }

            StringBuffer sb = new StringBuffer();
            return "Sign Claim Submission " + sb.toString();
        });
    }
}