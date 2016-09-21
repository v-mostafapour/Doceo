package pipeline;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PatternTemplate {
	File patternFile= new File("patternfile.txt");
	FileWriter writer;
	public PatternTemplate()  {
		try {
			writer=new FileWriter(patternFile);
		} catch (IOException e) {
			System.err.println("patternfile.txt "+" File cannot write...");
		}
	}
	
	private static ArrayList<String> paternstemplatelist=new ArrayList<>();
	Map<String,ArrayList<String>> verbmap=new HashMap<String,ArrayList<String>>();
	
	
	ArrayList<String> verbforms;	
		
//	public void patternMaker(VarlikEylem varlikEylem){
//		String pattern;
//		for(String varlik:varlikEylem.getVarlik()){			
//			for(String eylem:varlikEylem.getEylem()){				
//				pattern=".*"+(varlik)+"*"+".*"+"was|were"+eylem+"ed";				
//			}
//		}		
//	}
	public ArrayList<String> getPatternsOfVerb(String eylem){
		if(verbmap.containsKey(eylem))
			return verbmap.get(eylem); // returns all patterns of given verb in regular expression form
		else
			return null;//
	}


	public void updateVerbPatternTemplate(String varlik, String yeniEylem ) {
		
		if(!(verbmap.keySet().contains(yeniEylem))){
			//creates different form of the given verb 
			// entity comes as prefix of verb
			verbforms=new ArrayList<>();
			verbforms.add(".*"+varlik+".*"+yeniEylem);
			verbforms.add(".*"+varlik+".*"+yeniEylem+"ed");
			verbforms.add(".*"+varlik+".*"+yeniEylem+"ed"+" by");
			verbforms.add(".*"+varlik+".*"+"is "+yeniEylem+"ed"+" by");			
			verbforms.add(".*"+varlik+".*"+"was "+yeniEylem+"ed");
			verbforms.add(".*"+varlik+".*"+"was "+yeniEylem+"ed"+" by");
			verbforms.add(".*"+varlik+".*"+"were "+yeniEylem+"ed");
			verbforms.add(".*"+varlik+".*"+"were "+yeniEylem+"ed"+" by");
			
			// // entity comes as Suffix of verb
			
			verbforms.add(yeniEylem+".*"+varlik);
			verbforms.add(yeniEylem+"ed"+ ".*"+varlik);
			verbforms.add(yeniEylem+"ed"+" by"+".*"+varlik);
			verbforms.add("is "+yeniEylem+"ed"+" by"+".*"+varlik);			
			verbforms.add("was "+yeniEylem+"ed"+".*"+varlik);
			verbforms.add("was "+yeniEylem+"ed"+" by"+".*"+varlik);
			verbforms.add("were "+yeniEylem+"ed"+".*"+varlik);
			verbforms.add("were "+yeniEylem+"ed"+" by"+".*"+varlik);
			//adds given verb as key and its forms as value
			verbmap.put(yeniEylem, verbforms);
		}//if
		else{
			verbforms=verbmap.get(yeniEylem);
			verbmap.remove(yeniEylem);//replace key with new values
			//creates different form of the given verb / this will be changes to Set to prevent repeating patterns
			verbforms.add(".*"+varlik+".*"+yeniEylem);
			verbforms.add(".*"+varlik+".*"+yeniEylem+"ed");
			verbforms.add(".*"+varlik+".*"+yeniEylem+"ed"+" by");
			verbforms.add(".*"+varlik+".*"+"is "+yeniEylem+"ed"+" by");			
			verbforms.add(".*"+varlik+".*"+"was "+yeniEylem+"ed");
			verbforms.add(".*"+varlik+".*"+"was "+yeniEylem+"ed"+" by");
			verbforms.add(".*"+varlik+".*"+"were "+yeniEylem+"ed");
			verbforms.add(".*"+varlik+".*"+"were "+yeniEylem+"ed"+" by");
			
			// // entity comes as Suffix of verb
			
			verbforms.add(yeniEylem+".*"+varlik);
			verbforms.add(yeniEylem+"ed"+ ".*"+varlik);
			verbforms.add(yeniEylem+"ed"+" by"+".*"+varlik);
			verbforms.add("is "+yeniEylem+"ed"+" by"+".*"+varlik);			
			verbforms.add("was "+yeniEylem+"ed"+".*"+varlik);
			verbforms.add("was "+yeniEylem+"ed"+" by"+".*"+varlik);
			verbforms.add("were "+yeniEylem+"ed"+".*"+varlik);
			verbforms.add("were "+yeniEylem+"ed"+" by"+".*"+varlik);
			
			//adds given verb as key and its forms as value
			verbmap.put(yeniEylem, verbforms);			
		}//else
			 //		paternstemplatelist.add(".*"+(varlik)+"*"+".*"+"was|were"+eylem+"ed");
			 //		paternstemplatelist.add(".*"+(varlik)+"*"+".*"+"is|has"+eylem+"ed");
	}//addToPatternTemplate
	
	
}
