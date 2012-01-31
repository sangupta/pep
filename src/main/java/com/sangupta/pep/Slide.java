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

public class Slide {
	
	private String slideData;
	
	private SlideVariables slideVariables;

	public Slide(String slide, SlideVariables variables) {
		this.slideData = slide;
		this.slideVariables = variables;
	}

	/**
	 * @return the slideData
	 */
	public String getSlideData() {
		return slideData;
	}

	/**
	 * @param slideData the slideData to set
	 */
	public void setSlideData(String slideData) {
		this.slideData = slideData;
	}

	/**
	 * @return the slideVariables
	 */
	public SlideVariables getSlideVariables() {
		return slideVariables;
	}

	/**
	 * @param slideVariables the slideVariables to set
	 */
	public void setSlideVariables(SlideVariables slideVariables) {
		this.slideVariables = slideVariables;
	}

}
