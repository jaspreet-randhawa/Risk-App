package Model;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import Model.AdjacencyMatrixConnectedGraph;
import junit.framework.TestCase;

public class AdjacencyMatrixConnectedGraphTest extends TestCase{
	
	File file= new File("MatrixFileTest.txt");
	//CountryMap cMap=new CountryMap();
			
	AdjacencyMatrixConnectedGraph adjMatrix;
	
	protected void setUp (){
		
		
		
	}
	
	public void testCheckAdjacency(){
		//cMap.makeMatrixFile();
		AdjacencyMatrixConnectedGraph adjMatrix = new AdjacencyMatrixConnectedGraph();
		assertTrue(adjMatrix.checkAdjacency());
	}

}