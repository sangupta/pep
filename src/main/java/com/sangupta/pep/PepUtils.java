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

public class PepUtils {
	
	public static String encodeImageFromUrl(String imageUrl) {
		return imageUrl;
	}

	public static String getAbsoluteUrl(String imageUrl, File parentDir) {
		if(isAbsoluteUrl(imageUrl)) {
			return imageUrl;
		}
		
		String basePath = parentDir.getAbsolutePath();
		String path = basePath + File.separator + imageUrl;
		File file = new File(path);
		if(file.exists()) {
			String finalPath = file.getAbsolutePath();
			finalPath = finalPath.substring(basePath.length() + 1);
			
			return finalPath;
		}
		
		return null;
	}

	private static boolean isAbsoluteUrl(String imageUrl) {
		if(imageUrl.startsWith("http://") || imageUrl.startsWith("https://") || imageUrl.startsWith("ftp://")) {
			return true;
		}
		
		return false;
	}

}
