package com.samspain.slacktest1.controllers;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Attachment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/slack")
public class SlackController {

    @Value("${SLACK_TOKEN}")
    private String SLACK_TOKEN;

    @GetMapping()
    public String get() throws SlackApiException, IOException {
        Slack slack = Slack.getInstance();
        MethodsClient methods = slack.methods(SLACK_TOKEN);

        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("#random").text("Hello from Java!").build();

        return methods.chatPostMessage(request).toString();
    }

    @PostMapping()
    public String send(@RequestParam String messageText) throws SlackApiException, IOException {
        Slack slack = Slack.getInstance();
        MethodsClient methods = slack.methods(SLACK_TOKEN);
        Attachment attachment = new Attachment();
        attachment.setFilename("error_information.txt");
        attachment.setText("This contains an error message");
        ChatPostMessageRequest request = ChatPostMessageRequest.builder().attachments(Arrays.asList(attachment))
                .channel("#random").text(messageText).build();

        ChatPostMessageResponse response = methods.chatPostMessage(request);
        if (response.isOk()) {
            return "Message sent okay";
        } else {
            return "Message failed!";
        }
    }
}
