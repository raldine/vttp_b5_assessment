package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import vttp.batch5.sdf.task01.models.BikeEntry;

// Use this class as the entry point of your program

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException{

		File file = new File("day.csv");

		FileReader fr = new FileReader(file);

		BufferedReader br = new BufferedReader(fr);
		
		String line = "x";

		ArrayList<String[]> wholeList = new ArrayList<>();


		while ((line=br.readLine())!=null){

			String[] temp = line.split(",");
			wholeList.add(temp);


		}

		br.close();


		// for(String [] s : wholeList){
		// 	System.out.println(toStringSA(s));
		// }


		//remove col header
		wholeList.remove(0);


		ArrayList<BikeEntry> allEntries = new ArrayList<>();

		for(String[] s : wholeList){


			allEntries.add(BikeEntry.toBikeEntry(s));



		}

		// System.out.println(toStringBikeEntry(allEntries.get(10)));

		// get total count of cyclist per entry

		Map<BikeEntry, Integer> entryCounts = new HashMap<>();

		for(BikeEntry entry : allEntries){

			int totalCount = entry.getCasual() + entry.getRegistered();

			entryCounts.put(entry, totalCount);

		}

		// System.out.println(" total cyclist for entry 10 is " + toStringEntryCount(allEntries.get(10), entryCounts));

		//sort from highest to lowest count

		LinkedHashMap<BikeEntry, Integer> sortedMap = entryCounts.entrySet()
													.stream()
													.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
													.collect(Collectors.toMap(
														Map.Entry::getKey,
														Map.Entry::getValue,
														(oldValue, newValue) -> oldValue, LinkedHashMap::new));

		// toStringMap(sortedMap); // testing to check sorted results

		// System.out.println("now printing results...");

		// for(BikeEntry b : sortedMap){


		// 	String temp = toPrintResult(b, sortedMap);
		// }

		LinkedHashMap<BikeEntry, Integer> results = getFirstFive(sortedMap);

		toPrintResult(results);


		



		

	}// end of main


	public static void toPrintResult(Map<BikeEntry, Integer> sortedmap){


		String para = "";
		int i = 0;

			for(BikeEntry b : sortedmap.keySet()){

				para+= toStringPosition(i);
				para+= toStringSeason(b.getSeason()) + " (season), ";
				para+= "on a " + toStringDay(b.getWeekday()) + " (day) in the month of " + toStringMonth(b.getMonth()) + "( month). ";
				para+= "There was a total of " + sortedmap.get(b) + " (total) cyclist. ";
				para+= "The weather was " + toStringWeather(b.getWeather()) + "( weather). ";
				para+= toStringDay(b.getWeekday()) + "( day)" + toStringHoliday(b.isHoliday()) +"\n";

				i++;
	
	
	
			}

	

	

		System.out.println(para);


	}



	public static String toStringSA(String[] sa){


		return Arrays.toString(sa);

	}


	public static String toStringBikeEntry(BikeEntry b){

		String result = "";

		result+= b.getSeason() + ", ";
		result+= b.getMonth() + ", ";
		result+= b.isHoliday() + ", ";
		result+= b.getWeekday() + ", ";
		result+= b.getWeather() + ", ";
		result+= b.getTemperature() + ", ";
		result+= b.getHumidity() + ", ";
		result+= b.getWindspeed() + ", ";
		result+= b.getCasual() + ", ";
		result+= b.getRegistered() + "\n";


		return result;

	
	}

	public static int toStringEntryCount(BikeEntry entry, Map<BikeEntry, Integer> map){

		int result = map.get(entry);

		


		return result;



	}

	public static void toStringMap(Map<BikeEntry, Integer> sortedmap){


		String result = "";

		for(BikeEntry b : sortedmap.keySet()){

			result+= toStringBikeEntry(b);
			result+= "---->" + String.valueOf(sortedmap.get(b));
			result+= "\n";




		}

		System.out.println(result);


	}


	public static String toStringWeather(int i){

		String results = "";

		if (i == 1){

			results = "Clear, Few clouds, Partly cloudy, Partly cloudy";


		} else if (i == 2){

			results = "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist";
		} else if (i == 3) {

			results = "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds";
		} else {

			results = "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog";
		}

		return results;

	}

	public static String toStringHoliday(boolean b){

		String results = "";

		if(b==true){

			results = " was a holiday.\n";



		} else {
			
			results = " was not a holiday.\n";
		}


		return results;

	}

	public static LinkedHashMap<BikeEntry, Integer> getFirstFive(LinkedHashMap<BikeEntry, Integer> map){
		Iterator<Map.Entry<BikeEntry, Integer>> iterator = map.entrySet().iterator();
		

		LinkedHashMap<BikeEntry, Integer> results = new LinkedHashMap<>();

		int i = 0;

		while(iterator.hasNext() && i < 5){

			Map.Entry<BikeEntry, Integer> temp  = iterator.next();
			results.put(temp.getKey(),temp.getValue());

			i++;


		}


		return results;
	}

	// to print season, day and month in proper

	public static String toStringSeason(int i){

		String s = "";

		if (i == 1){

			s = "Spring";
		} else if (i == 2){

			s = "Summer";

		} else if (i == 3){

			s = "Fall";

		} else {

			s = "Winter";

		}

		return s;
	}

	public static String toStringDay(int i){

		String s = "";

		if (i == 0){

			s = "Sunday";
		} else if (i == 1){

			s = "Monday";

		} else if (i == 2){

			s = "Tuesday";

		} else if (i == 3){

			s = "Wednesday";

		} else if (i == 4){

			s = "Thursday";

		} else if (i == 5){

			s = "Friday";

		} else if (i == 6){

			s = "Saturday";

		}

		return s;
	}

	public static String toStringMonth(int i){

		String s = "";

		if (i == 1){

			s = "January";
		} else if (i == 2){

			s = "February";

		} else if (i == 3){

			s = "March";

		} else if (i == 4){

			s = "April";

		} else if (i == 5){

			s = "May";

		} else if (i == 6){

			s = "June";

		} else if (i == 7){

			s = "July";

		} else if (i == 8){

			s = "August";

		} else if (i == 9){

			s = "September";

		} else if (i == 10){

			s = "October";

		} else if (i == 11){

			s = "November";

		} else {
			
			s = "December";
		}

		return s;
	}

	//for first part of para - position

	public static String toStringPosition(int i){

		String s = "";

		if (i == 0){

			s = "The highest (position) recorded number of cyclists was in ";
		
		} else if (i == 1){

			s = "The second highest (position) recorded number of cyclists was in ";

		} else if (i == 2){

			s = "The third highest (position) recorded number of cyclists was in ";

		} else if (i == 3){

			s = "The fourth highest (position) recorded number of cyclists was in ";

		} else {

			s = "The fifth highest (position) recorded number of cyclists was in ";

		}




		return s;


		}







	
	}





	//for reference
	// BikeEntry entry = new BikeEntry();
	// entry.setSeason(toInt(cols[0]));
	// entry.setMonth(toInt(cols[1]));
	// entry.setHoliday(toBoolean(cols[2]));
	// entry.setWeekday(toInt(cols[3]));
	// entry.setWeather(toInt(cols[4]));
	// entry.setTemperature(toFloat(cols[5]));
	// entry.setHumidity(toFloat(cols[6]));
	// entry.setWindspeed(toFloat(cols[7]));
	// entry.setCasual(toInt(cols[8]));
	// entry.setRegistered(toInt(cols[9]));
	// return entry;

