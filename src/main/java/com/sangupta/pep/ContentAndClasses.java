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

package com.sangupta.pep;

import java.util.ArrayList;
import java.util.List;

public class ContentAndClasses {
	
	private String content;
	
	private final List<String> classes = new ArrayList<String>();
	
	public ContentAndClasses() {
		// do nothing
	}
	
	public ContentAndClasses(String content) {
		this();
		this.content = content;
	}
	
	public ContentAndClasses(String content, List<String> classes) {
		this(content);
		if(classes != null && !classes.isEmpty()) {
			this.classes.addAll(classes);
		}
	}
	
	public void addClass(String className) {
		this.classes.add(className);
	}
	

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the classes
	 */
	public List<String> getClasses() {
		return classes;
	}

}
