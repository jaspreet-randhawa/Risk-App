package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class sets up the countries and continents on the Risk game board.
 * @author yaomeng
 *
 */
public class Board {

	public int i;
	public int j;
	
	public boolean isLoaded;
	public boolean isAdjacent;
	
	public String name;
	
	public String[] continentsArray;
	public String[] adjacenciesArray;
	
	public ArrayList<Country> countriesList;
	public ArrayList<Country> adjacenciesList;
	public ArrayList<Country> memberCountries;
	public ArrayList<Country> unoccupied;
	public ArrayList<Continent> continentsList;

    public HashMap<String, Country> countriesMap;
    public HashMap<String, Continent> continentsMap;
	
    public Board() {
    	super();
    }

    /**
     * Loads the information needed to construct the board and constructs the country and continent objects
     * needed for the board from three files.  The first file lists all the countries.  The second file lists 
     * all of the continents and which countries are on a given continent.  The third file lists the adjacencies 
     * for each country.
     * @param countriesArray
     * @param adjacenciesFileArray
     * @param continentsFileArray
     * @return
     */
    public boolean loadBoard(String[] countriesArray, String[] adjacenciesFileArray, String[] continentsFileArray) {
		
		isLoaded = false;
	
		countriesMap = new HashMap<String, Country>();
		continentsMap = new HashMap<String, Continent>();
		
		// Populates countriesMap
		for (i = 0; i < countriesArray.length; i++) {
		
			countriesMap.put(countriesArray[i], new Country(countriesArray[i]));
		}
		
		countriesList = new ArrayList<Country>(countriesMap.values());
		
		// Populates country adjacencies
		for (i = 0; i < adjacenciesFileArray.length; i++) {
		
			System.out.println("Building new adjacenciesArray with " + adjacenciesFileArray[i] + "...");	
			
			// Splits each line into the individual country names
			adjacenciesArray = adjacenciesFileArray[i].split(",");
			
			System.out.println("Building new adjacenciesList...");
			
			adjacenciesList = new ArrayList<Country>();
			
			// Creates a list of countries adjacent to the country in index 0
			for (j = 1; j < adjacenciesArray.length; j++) {
			
				System.out.println("Adding to adjacenciesList: " + adjacenciesArray[j]);
				
				adjacenciesList.add(countriesMap.get(adjacenciesArray[j]));
			
			}
			
			// Adds the adjacencies to the country
			countriesMap.get(adjacenciesArray[0]).addAdjacencies(adjacenciesList);
		}
		
		// Populates continents
		for (i = 0; i < continentsFileArray.length; i++) {
		// Splits the data of each line
			continentsArray = continentsFileArray[i].split(",");
			
			memberCountries = new ArrayList<Country>();
				
			// Populates memberCountries, starting at index 2
			for (j = 2; j < continentsArray.length; j++) {
			
				System.out.println("Adding memberCountry to " + continentsArray[0] + ": " + continentsArray[j]);
				
				memberCountries.add(countriesMap.get(continentsArray[j]));
			}
			
			// Populates continents hash map
			continentsMap.put(continentsArray[0], new Continent(continentsArray[0], Integer.parseInt(continentsArray[1]), memberCountries));
		}
		
		// Sets isLoaded to true if successful
		isLoaded = true;
		
		return isLoaded;
	}

    /**
     * Returns a list containing the continent objects the board has
     * @return
     */
    public ArrayList<Continent> getContinents() {
		return new ArrayList<Continent>(continentsMap.values());
    }


    /**
     * Returns the continent object whose name is the string continentName
     **/
    public Continent getContinentByName(String continentName) {
		return continentsMap.get(continentName);
    }


    /**
     * Returns the number of bonus armies awarded to a player for controlling all the countries in
     * the continent whose name is the string continentName
     * @param continentName
     * @return
     */
    public int getBonusAmrines(String continentName) {
		return continentsMap.get(continentName).getBonusArmies();
    }


    /**
     * Returns a list of the country objects that are in the continent specified 
     * by the string continentName
     * @param continentName
     * @return
     */
    public ArrayList<Country> getMemberCountries(String continentName) {
		return continentsMap.get(continentName).getMemberCountries();
    }


    /**
     * Returns a list of all of the country objects in the board
     * @return
     */
    public ArrayList<Country> getCountries() {
		return countriesList;
    }

    /**
     * Returns the country object for the country specified by the string
     * countryName
     * @param countryName
     * @return
     */
    public Country getCountryByName(String countryName) {
		return countriesMap.get(countryName);
    }


    /**
     * Sets the occupant of the country object specified by the string countryName
     * to be the player object supplied as an argument.
     * @param countryName
     * @param occupant
     */
    public void setOccupant(String countryName, Player occupant) {
		countriesMap.get(countryName).setOccupant(occupant);
    }


    /**
     * Returns the player object that currently occupies the country specufied by
     * string countryName
     * @param countryName
     * @return
     */
    public Player getOccupant(String countryName) {
		return countriesMap.get(countryName).getOccupant();
    }


    /**
     * Sets the number of armies currently in the country specified by the string
     * countryName to the integer supplied as an argument
     * @param countryName
     * @param numberArmies
     */
    public void setNumberArmies(String countryName, int numberArmies) {
		countriesMap.get(countryName).setNumArmies(numberArmies);
    }


    /**
     * Returns the number of armies currently in the country specified by the string
     * countryName
     * @param countryName
     * @return
     */
    public int getNumberArmies(String countryName) {
		return countriesMap.get(countryName).getArmies();
    }


    /**
     * Returns a list of the country objects that are the countries adjacent to the country
     * specified by the string countryName on the board
     * @param countryName
     * @return
     */
    public ArrayList<Country> getAdjacencies(String countryName) {
		return countriesMap.get(countryName).getAdjacencies();
    }
	
	public ArrayList<Country> getUnoccupied() {

		unoccupied = new ArrayList<Country>();
		
		for (i = 0; i < countriesList.size(); i++) {
		
			if (countriesList.get(i).hasPlayer() == false) {	
				unoccupied.add(countriesList.get(i));
			}
		}
		return unoccupied;
	}
	
	public boolean checkAdjacency(String countryA, String countryB) {
		if (countriesMap.get(countryA).getAdjacencies().contains(countriesMap.get(countryB))) {
			isAdjacent = true;
		} else {
			isAdjacent = false;
		}
		
		return isAdjacent;
	}

}