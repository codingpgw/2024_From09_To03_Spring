package com.pcwk.ehr.markdown.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class MarkdownServiceImpl implements MarkdownService {
	final Logger log = LogManager.getLogger(getClass());
	
	public MarkdownServiceImpl() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **MarkdownServiceImpl()**             │");
		log.debug("└───────────────────────────────────────┘");
	}

	@Override
	public String convertMarkdownToHtml(String markdown) {
		log.debug("│ markdown	            			   │"+markdown);
		
		Parser parser = Parser.builder().build();
		Node document = parser.parse(markdown);
		
		HtmlRenderer render = HtmlRenderer.builder().build();
		
		String html = render.render(document);
		log.debug("│ html	            			   │\n"+html);
		
		
		return html;
	}

}
