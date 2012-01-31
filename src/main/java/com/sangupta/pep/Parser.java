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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.petebevin.markdown.MarkdownProcessor;

public class Parser {
	
	private File inputFile;

	public Parser(File inputFile) {
		this.inputFile = inputFile;
	}

	public String parse() {
		try {
			String contents = FileUtils.readFileToString(this.inputFile);
			if(contents.startsWith("\ufeff")) { // check for Unicode BOM
				contents = contents.substring(1);
			}
			
			MarkdownProcessor markdownProcessor = new MarkdownProcessor();
			String html = markdownProcessor.markdown(contents);
			return html;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
