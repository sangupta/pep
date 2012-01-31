/**
 *
 * Pep - Slideshows using HTML5
 * Copyright (c) 2012, Sandeep Gupta
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.pep.macros;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.sangupta.pep.ContentAndClasses;
import com.sangupta.pep.IMacro;
import com.sangupta.pepmint.Formatter;
import com.sangupta.pepmint.Lexer;
import com.sangupta.pepmint.Pepmint;

/**
 * 
 * @author sangupta
 * @since 31 Jan 2012
 * @version 1.0
 */
public class CodeHighlightingMacro implements IMacro {
	
	private static final Pattern PATTERN = Pattern.compile("(<pre.+?>(<code>)?\\s?!(\\w+?)\\n(.*?)(</code>)?</pre>)", Pattern.UNICODE_CASE | Pattern.MULTILINE | Pattern.DOTALL);
	
	private static Pepmint JARMINT = null;
	
	private static Formatter formatter = null;
	
	private static final Map<String, Lexer> lexerCache = new HashMap<String, Lexer>();

	@Override
	public ContentAndClasses process(String content, File parentDir) {
		Matcher matcher = PATTERN.matcher(content);
		while(matcher.find()) {
			String language = matcher.group(3);
			String codeBlock = matcher.group(4);
			
			codeBlock = StringEscapeUtils.unescapeHtml(codeBlock);
			
			content = format(language, codeBlock, null);
		}
		
		return new ContentAndClasses(content);
	}
	
	private synchronized String format(final String format, final String code, String formatterParams) {
		if(StringUtils.isEmpty(formatterParams)) {
			formatterParams = "";
		}
		
		if(JARMINT == null) {
			JARMINT = new Pepmint();
		}
		
		Lexer lexer = null;
		if(lexerCache.containsKey(format)) {
			lexer = lexerCache.get(format);
		} else {
			lexer = JARMINT.newLexer(format);
			lexerCache.put(format, lexer);
		}
		
		if(formatter == null) {
			formatter = JARMINT.newHtmlFormatter(formatterParams);
		}
		
		String formattedCode = JARMINT.highlight(code, lexer, formatter);
		
		return formattedCode;
	}
	
}
