package com.koreait.shoefly.command.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koreait.shoefly.command.BaseCommand;

public interface ReviewCommand extends BaseCommand {

	public Logger logger = LoggerFactory.getLogger(ReviewCommand.class);
	
}
