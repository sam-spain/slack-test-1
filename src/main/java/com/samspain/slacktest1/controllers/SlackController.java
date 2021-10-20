package com.samspain.slacktest1.controllers;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.api.ApiTestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/slack")
public class SlackController {

    @GetMapping()
    public String get() throws SlackApiException, IOException {
        Slack slack = Slack.getInstance();
        ApiTestResponse response = slack.methods().apiTest(r -> r.foo("bar"));
        return response.toString();
    }
}
