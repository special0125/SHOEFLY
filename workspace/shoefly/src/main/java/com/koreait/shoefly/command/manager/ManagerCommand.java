package com.koreait.shoefly.command.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koreait.shoefly.command.BaseCommand;

/**
 * ManagerController -&gt; ManagerCommand
 * 
 * @author 박세환
 * @see ManagerController
 */
public interface ManagerCommand extends BaseCommand {

	public Logger logger = LoggerFactory.getLogger(ManagerCommand.class);
	
}
