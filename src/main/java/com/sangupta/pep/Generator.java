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
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.sangupta.pep.macros.CodeHighlightingMacro;
import com.sangupta.pep.macros.EmbedImagesMacro;
import com.sangupta.pep.macros.FixImagePathsMacro;
import com.sangupta.pep.macros.FxMacro;
import com.sangupta.pep.macros.NotesMacro;
import com.sangupta.pep.macros.QRMacro;

public class Generator {
	
	private static final IMacro[] DEFAULT_MACROS = { new QRMacro(), 
													 new NotesMacro(), 
													 new FxMacro(), 
													 new CodeHighlightingMacro(),
													 new EmbedImagesMacro(),
													 new FixImagePathsMacro()
												   };
	
	private static final Pattern PATTERN = Pattern.compile("(<h(\\d+?).*?>(.+?)</h\\d>)\\s?(.+)?", Pattern.DOTALL | Pattern.UNICODE_CASE); // (<h(\d+?).*?>(.+?)</h\d>)\s?(.+)?

	private static final int TOC_MAX_LEVEL = 2;
	
	private File inputFile = null;
	
	private File parentDir = null;
	
	private ExecutionOptions options;
	
	private final List<TocEntry> toc = new ArrayList<TocEntry>();

	public Generator(File inputFile, ExecutionOptions options) {
		this.inputFile = inputFile;
		this.parentDir = this.inputFile.getAbsoluteFile().getParentFile();
		this.options = options;
	}

	public void execute() {
		try {
			this.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void write() throws IOException {
		// get the html presentation
		String html = this.render();
		
		// write the presentation code
		FileUtils.writeStringToFile(new File(parentDir, options.getDestinationFile()), html, options.getEncoding());
		
		// copy the theme if specified
		if(this.options.isCopyTheme()) {
			copyResource(getScreenCSS());
			copyResource(getPrintCSS());
			copyResource(getJS());
		}
	}
	
	private void copyResource(Resource resource) throws IOException {
		if(resource == null) {
			return;
		}
		
		FileUtils.copyFileToDirectory(new File(resource.getUrl()), this.parentDir);
	}
	
	private String render() throws IOException {
		final String template = FileUtils.readFileToString(new File("./themes/default/base.html"));
		
		Slide[] slides = fetchContents();
		Map<String, Object> contextVariables = getTemplateVariables(slides);
		
		VelocityContext context = new VelocityContext();
		if(contextVariables != null) {
			for(Entry<String, Object> entry : contextVariables.entrySet()) {
				context.put(entry.getKey(), entry.getValue());
			}
		}
		
		StringWriter writer = new StringWriter();
		Velocity.evaluate(context, writer, "landslide", template);
		
		writer.close();
		
		String html = writer.toString();
		return HtmlUtils.tidyHtml(html);
	}

	private Map<String, Object> getTemplateVariables(Slide[] slides) {
		final Map<String, Object> map = new HashMap<String, Object>();
		
		// read the presentation title
		String headTitle = slides[0].getSlideVariables().getTitle(); 
		map.put("headTitle", headTitle);
		
		String toc = null;
		int numSlides = 0;
		for(int slideIndex = 0; slideIndex < slides.length; slideIndex++) {
			Slide slide = slides[slideIndex];
			numSlides++;
			
			SlideVariables vars = slide.getSlideVariables();
			vars.setNumber(numSlides);
			if(StringUtils.isNotEmpty(vars.getTitle()) && (vars.getLevel() <= TOC_MAX_LEVEL)) {
				addTocEntry(vars.getTitle(), vars.getLevel(), numSlides);
			} else {
				addTocEntry("-", 1, numSlides);
			}
		}
		
		map.put("slides", slides);
		map.put("toc", toc);
		map.put("embed", options.isEmbed());
		map.put("numSlides", numSlides);
		
		map.put("screenCSS", getScreenCSS());
		map.put("printCSS", getPrintCSS());
		
		map.put("js", getJS());
		
		map.put("userCSS", getUserCSS());
		map.put("userJS", getUserJS());
		
		return map;
	}

	private List<Resource> getUserJS() {
		return null;
	}

	private List<Resource> getUserCSS() {
		return null;
	}

	private Resource getJS() {
		Resource resource = new Resource();
		resource.setUrl("slides.js");
		return resource;
	}

	private Resource getScreenCSS() {
		Resource resource = new Resource();
		resource.setUrl("screen.css");
		return resource;
	}
	
	private Resource getPrintCSS() {
		Resource resource = new Resource();
		resource.setUrl("print.css");
		return resource;
	}

	/**
	 * Read the markdown input file and create the {@link Slide} objects of each
	 * individual slide.
	 * 
	 * @return
	 * @throws IOException
	 */
	private Slide[] fetchContents() throws IOException {
		Parser parser = new Parser(this.inputFile);
		String slideArray = parser.parse();
		
		String[] slides = slideArray.split("<hr.+>");
		
		Slide[] val = new Slide[slides.length];
		for(int index = 0; index < slides.length; index++) {
			String slide = slides[index];
			slide = slide.trim();
			
			SlideVariables variables = getSlideVariables(slide);
			Slide sl = new Slide(slide, variables);
			val[index] = sl;
		}
		
		return val;
	}

	private SlideVariables getSlideVariables(String slideContents) {
		SlideVariables vars = new SlideVariables();
		
		Matcher matcher = PATTERN.matcher(slideContents);
		
		if(matcher != null && matcher.matches()) {
			vars.setHeader(matcher.group(1));
			vars.setLevel(Integer.valueOf(matcher.group(2)));
			vars.setTitle(matcher.group(3));
			vars.setContent(matcher.group(4));
		} else {
			vars.setHeader("");
			vars.setTitle("");
			vars.setContent(slideContents);
			vars.setLevel(0);
		}
		
		// process slide classes
		ContentAndClasses cc = processMacros(vars);
		
		String content = cc.getContent();
		vars.setContent(content);
		vars.setClasses(cc.getClasses().toArray(new String[0]));
		
		if(StringUtils.isNotEmpty(content)) {
			content = content.trim();
			Pattern p2 = Pattern.compile("<h\\d[^>]*>presenter notes</h\\d>", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.DOTALL);
			Matcher m2 = p2.matcher(content);
			
			if(m2 != null && m2.matches()) {
				vars.setPresenterNotes(content.substring(m2.end()).trim());
				content = content.substring(0, m2.start());
				
				vars.setContent(content);
			}
		}
		
		vars.setRelativeSourcePath(this.inputFile.getPath());
		vars.setAbsoluteSourcePath(this.inputFile.getAbsolutePath());
		
		return vars;
	}

	private ContentAndClasses processMacros(SlideVariables vars) {
		List<String> classes = new ArrayList<String>();
		
		String content = vars.getContent();
		
		if(StringUtils.isNotEmpty(content)) {
			content = content.trim();
			
			for(IMacro macro : DEFAULT_MACROS) {
				ContentAndClasses cc = macro.process(content, this.parentDir);
				content = cc.getContent();
				
				classes.addAll(cc.getClasses());
			}
		}
		
		return new ContentAndClasses(content, classes);
	}

	private void addTocEntry(String title, int level, int slideNumber) {
		toc.add(new TocEntry(title, slideNumber, level));
	}
}
