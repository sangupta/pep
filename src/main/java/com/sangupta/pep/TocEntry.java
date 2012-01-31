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

public class TocEntry {
	
	private String title;
	
	private int slideNumber;
	
	private int level;

	public TocEntry(String title2, int slideNumber2, int level2) {
		this.title = title2;
		this.slideNumber = slideNumber2;
		this.level = level2;
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
	 * @return the slideNumber
	 */
	public int getSlideNumber() {
		return slideNumber;
	}

	/**
	 * @param slideNumber the slideNumber to set
	 */
	public void setSlideNumber(int slideNumber) {
		this.slideNumber = slideNumber;
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

}
