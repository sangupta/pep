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

import org.apache.commons.lang.StringUtils;

import com.sangupta.pep.ContentAndClasses;
import com.sangupta.pep.IMacro;
import com.sangupta.pep.PepUtils;

public class FixImagePathsMacro implements IMacro {

	@Override
	public ContentAndClasses process(String content, File parentDir) {
		Matcher matcher = EmbedImagesMacro.PATTERN.matcher(content);
		while(matcher.find()) {
			// String imageTag = matcher.group();
			String imageUrl = matcher.group(1);
			
			// encode image
			String absoluteUrl = PepUtils.getAbsoluteUrl(imageUrl, parentDir);
			if(absoluteUrl == null) {
				System.out.println("Failed to resolve image from url: " + imageUrl);
				continue;
			}

			// replace original image with encoded image
			content = StringUtils.replace(content, imageUrl, absoluteUrl, 1);
		}
		
		return new ContentAndClasses(content);
	}

}
