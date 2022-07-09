package com.suminjin.simpleroom.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.suminjin.simpleroom.model.MessageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ChatSocketController {

	private final SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping(value = "/enter")
	public void enterChat(MessageDTO message) {

	}

}
