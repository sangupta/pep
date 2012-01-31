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

/**
 * 
 * @author sangupta
 * @since 31 Jan 2012
 * @version 1.0
 */
public class Pep {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutionOptions options = parseOptions(args);
		
		if(options == null) {
			return;
		}

		new Pep().run(options);
	}


	private void run(ExecutionOptions options) {
		File file = new File("C:/projects/git/presentations/NoSQL/NoSQL.md");
		Generator generator = new Generator(file, options);
		generator.execute();
		
		System.out.println("Done!");
	}


	private static ExecutionOptions parseOptions(String[] args) {
		return new ExecutionOptions();
	}

}
