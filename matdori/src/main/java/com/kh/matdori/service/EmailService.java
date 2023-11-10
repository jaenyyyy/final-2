package com.kh.matdori.service;

import java.io.IOException;

import javax.mail.MessagingException;

public interface EmailService {

		void sendCelebration(String email) throws MessagingException, IOException;
	
	}



