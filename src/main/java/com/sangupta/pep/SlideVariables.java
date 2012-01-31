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

public class SlideVariables {
	
	private String header;
	
	private String title;
	
	private int level;
	
	private String content;
	
	private String[] classes;
	
	private int number;
	
	private String relativeSourcePath;
	
	private String absoluteSourcePath;
	
	private String presenterNotes;
	
	// Usual accessors follow

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the classes
	 */
	public String[] getClasses() {
		return classes;
	}

	/**
	 * @param classes the classes to set
	 */
	public void setClasses(String[] classes) {
		this.classes = classes;
	}

	/**
	 * @return the presenterNotes
	 */
	public String getPresenterNotes() {
		return presenterNotes;
	}

	/**
	 * @param presenterNotes the presenterNotes to set
	 */
	public void setPresenterNotes(String presenterNotes) {
		this.presenterNotes = presenterNotes;
	}

	/**
	 * @return the relativeSourcePath
	 */
	public String getRelativeSourcePath() {
		return relativeSourcePath;
	}

	/**
	 * @param relativeSourcePath the relativeSourcePath to set
	 */
	public void setRelativeSourcePath(String relativeSourcePath) {
		this.relativeSourcePath = relativeSourcePath;
	}

	/**
	 * @return the absoluteSourcePath
	 */
	public String getAbsoluteSourcePath() {
		return absoluteSourcePath;
	}

	/**
	 * @param absoluteSourcePath the absoluteSourcePath to set
	 */
	public void setAbsoluteSourcePath(String absoluteSourcePath) {
		this.absoluteSourcePath = absoluteSourcePath;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

}
