package manager;

import java.util.Hashtable;
import java.util.Set;
import dao.Reader;
import dao.Writer;

public class Manager {
		private Hashtable<Character, Integer> characterCounter;
		private Hashtable<String,String> reactions;
	
		public Manager() {		
				characterCounter = new Hashtable <Character ,Integer>();
				characterCounter.put('A', 0);
				characterCounter.put('F', 0);
				characterCounter.put('R', 0);
				characterCounter.put('V', 0);
				characterCounter.put('H', 0);
				characterCounter.put('T', 0);
				characterCounter.put('N', 0);

				reactions = new Hashtable <String,String>();
				reactions.put("FA", "Evaporación");
				reactions.put("FH", "Derretido");
				reactions.put("FR", "Sobrecarga");
				reactions.put("RA", "Electro-carga");
				reactions.put("RH", "Superconductor");
				reactions.put("AH", "Congelar");
				reactions.put("VF", "Torbellino");
				reactions.put("VR", "Torbellino");
				reactions.put("VA", "Torbellino");
				reactions.put("VH", "Torbellino");
				reactions.put("TF", "Cristalizar");
				reactions.put("TR", "Cristalizar");
				reactions.put("TA", "Cristalizar");
				reactions.put("TH", "Cristalizar");
				reactions.put("FN", "Quemadura");
		}
	
		public void init() {		
				elementalStatistic();
				elementalReaction();				
		}
	
		private void elementalStatistic() {
				Reader read= new Reader("Files/Personajes.txt");
				Writer write=new Writer("Files/ResultadoElementos.txt", false);
				int totalCharacters=0;
				int totalElements=0;
				String line;
				char letter;
				
				while((line = read.getLine())!=null) {
						totalCharacters++;
						letter=line.charAt(line.length()-1);
						characterCounter.put((letter), characterCounter.get(letter)+1);	
						if(characterCounter.get(letter)==1)
								totalElements++;
				}
				read.close();
				write.writeOnFile("Total personajes:"+totalCharacters
								  +"\nTotal personajes por elemento:"+totalElements
								  +"\nTotal de personajes por elemento:\n"+getNumberCharactersByElement());
				write.close();
		}
	
		private void elementalReaction() {
				Reader read= new Reader("Files/Elementos.txt");
				Writer write=new Writer("Files/ResultadoElementos.txt", true);
				String str, reaction;
				String result="";
				int letter= read.getChar();
				
				while(letter!=-1) {					
						str=""+(char)letter;				
						letter= read.getChar();
						str+=(char)letter;
						reaction = (reactions.get(str)!=null)? reactions.get(str):reactions.get(reverseStr(str));
						result+=(reaction!=null)?"\n"+reaction:"";
						letter=(reaction!=null)?read.getChar():letter;
				}
				read.close();
				write.writeOnFile(result);
				write.close();
		}

		private String getNumberCharactersByElement() {
				String result="";
				Set<Character> elements = characterCounter.keySet();
				
				for (Character element : elements) {
						if(characterCounter.get(element)>0)
								result+="\t-"+element+": "+characterCounter.get(element)+"\n";
				}
				return result;		
		}

		private String reverseStr(String str) {
				return new String(new char[]{str.charAt(1), str.charAt(0)});		
		}
}