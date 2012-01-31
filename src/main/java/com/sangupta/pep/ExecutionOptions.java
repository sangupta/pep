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

public class ExecutionOptions {
	
	private boolean copyTheme = true;
	
	private boolean debug = false;
	
	private String destinationFile = "presentation.html";
	
	private boolean direct = false;
	
	private boolean embed = false;
	
	private String encoding = "utf-8";
	
	private boolean relative = false;
	
	private String theme = "default";
	
	private boolean verbose = false;
	
	private boolean lineNumbers = false;
	
	// Usual accessors follow

	/**
	 * @return the copyTheme
	 */
	public boolean isCopyTheme() {
		return copyTheme;
	}

	/**
	 * @return the debug
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * @return the destinationFile
	 */
	public String getDestinationFile() {
		return destinationFile;
	}

	/**
	 * @return the direct
	 */
	public boolean isDirect() {
		return direct;
	}

	/**
	 * @return the embed
	 */
	public boolean isEmbed() {
		return embed;
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @return the relative
	 */
	public boolean isRelative() {
		return relative;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @return the verbose
	 */
	public boolean isVerbose() {
		return verbose;
	}

	/**
	 * @return the lineNumbers
	 */
	public boolean isLineNumbers() {
		return lineNumbers;
	}
	
}
