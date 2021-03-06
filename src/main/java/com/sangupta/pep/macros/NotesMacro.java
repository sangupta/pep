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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.sangupta.pep.ContentAndClasses;
import com.sangupta.pep.IMacro;

public class NotesMacro implements IMacro {
	
	private static final Pattern PATTERN = Pattern.compile("(.*)<p>\\.notes:\\s?(.*?)</p>(.*)", Pattern.DOTALL);

	@Override
	public ContentAndClasses process(String content, File parentDir) {
		final ContentAndClasses cc = new ContentAndClasses(content);
		
		if(StringUtils.isEmpty(content)) {
			return cc;
		}
		
		Matcher matcher = PATTERN.matcher(content);
		if(matcher.matches()) {
			StringBuilder builder = new StringBuilder();
			builder.append(matcher.group(1));
			builder.append("<p class=\"notes\">");
			builder.append(matcher.group(2));
			builder.append("</p>");
			builder.append(matcher.group(3));
			
			String newContent = builder.toString();
			if(!content.equals(newContent)) {
				cc.setContent(newContent);
				cc.addClass("has_notes");
			} else {
				cc.setContent(content);
			}
		}
		
		return cc;
	}

}
