// 
// Assignment 3 
// Question: Part 2
// Written by: Mamadou Camara(40315009) and Diego Chidiac(40315295) 
// 
package tariffs;

import java.util.NoSuchElementException;

public class TariffList implements TariffPolicy {
	private TariffNode head;
	private int size;
	
	//Default Constructor
	public TariffList() {
		this.head = null;
		this.size = 0;
	}
	
	//Copy Constructor
	public TariffList(TariffList other) {
		if (other == null || other.head == null) {
			head = null;
			size = 0;
			return;
		}
		this.head = new TariffNode(other.head);
		TariffNode position = this.head;
		TariffNode otherPosition = other.head.link;
		
		while(otherPosition != null) {
			TariffNode newNode = new TariffNode(otherPosition.tariff, null);
			position.setNewLink(newNode);
			position = newNode;
			otherPosition = otherPosition.link;
		}
	}
	
	
	//Return the size
	public int getSize() {	
		return size;
	}
	
	//Display method
	public void display() {
		TariffNode position = head;
		
		if(head == null) {
			System.out.println("There are no elements in this list");
		}
		else {
			while(position != null) {
				System.out.println(position);
				position = position.link; //Move to next node
			}	
		}	
	}
	
	//addToStart method
	public void addToStart(Tariff tariff) {
		head = new TariffNode(tariff,head);  
		size++;
	}
	
	//addToIndex method
	public void insertAtIndex(Tariff tariff,int index) throws NoSuchElementException {
	   if (index < 0 || index >= size ) {
            throw new NoSuchElementException();
       }
	   if (index == 0) {
	        addToStart(tariff);
	        return;
	    }

	    TariffNode position = head;
	    for (int i = 0; i < index - 1; i++) {
	        position = position.link;
	    }

	    TariffNode newNode = new TariffNode(tariff, position.link);
	    position.link = newNode;
	    size++;
	}
	//deleteFromIndex
	public void deleteFromIndex(int index) {
		if (index < 0 || index >= size) {
			throw new NoSuchElementException();
		}
		if (index == 0) {
			deleteFromStart();
			return;
		}
		
		TariffNode position = head;
		for (int i = 0; i < index - 1; i++) {
			position = position.link;
		}
		
		position.setNewLink(position.link.link);
		size--;
	}
	//deleteFromStart method
	public Tariff deleteFromStart() throws NoSuchElementException{
		if(size == 0) { //If node is empty
			 throw new NoSuchElementException();
		}
		else {
			//If node isn't empty
			Tariff t = head.tariff;
			head = head.link;
			size--;
			return t;
		}
	}
	//replaceAtIndex() method
	public void replaceAtIndex(Tariff t, int index) {
		 if (index < 0 || index >= size || size == 0 ) {
	            throw new NoSuchElementException();
	        }
		   else {
			   
			   		
			   //Create position node
			   TariffNode position = head;
				   
			   //Find the node at index
			   for (int i = 0; i < index; i++) {
				   position = position.link;
		       }
		       //Modify its tariff
		       position.setNewTariff(t);            

		       } 
	}
	
	//find() method
	public TariffNode find(String origin,String destination, String category) {
		boolean b = false;
		int iterations = 0;
		
		//Create position node
		TariffNode position = head;
		
		//If empty return false
		if(head == null) {
			return null;
		}
		else {
			//Search the linked list for a matching node
			while(position != null) {
				if(position.tariff.getOriginCountry().equals(origin) 
						&& position.tariff.getDestinationCountry().equals(destination)
						&& position.tariff.getProductCategory().equals(category)) {
					break;
				}
				position = position.link;
				iterations++;
			}
			System.out.println("Iterations: " + iterations);
			return position;
		}
		
	}
	//contains() method
	public boolean contains(String origin, String destination, String category) {
		return find(origin, destination, category) != null;
	}
	// Equals method
    public boolean equals(TariffList other) {
    	
        if (other == null || this.size != other.size) return false;

        TariffNode currentThis = this.head;
        TariffNode currentOther = other.head;
        
        //Check both list to see if they are equal
        while (currentThis != null && currentOther != null) {
            if (!currentThis.equals(currentOther))
                return false;
            currentThis = currentThis.link;
            currentOther = currentOther.link;
        }

        return true;
    }
	
	@Override
	public String evaluateTrade(double proposedTariff, double minimumTariff) {
		if (proposedTariff >= minimumTariff) {
	        return "accepted";
	    } else {
	        double difference = minimumTariff - proposedTariff;
	        double twentyPercent = 0.20 * minimumTariff;
	        if (difference <= twentyPercent) {
	            return "conditionally accepted";
	        } else {
	            return "rejected";
	        }
	    }
	}
	
	//Inner class
	public class TariffNode {
		private Tariff tariff;
		private TariffNode link;
		
		//Default constructor
		public TariffNode() {
			this.tariff = null;
			this.link = null;
		}
		
		//Parameterized constructor
		public TariffNode(Tariff tariff,TariffNode link) {
			this.tariff = tariff;
			this.link = link;
		}
		
		//Copy constructor
		public TariffNode(TariffNode otherTariffNode) {
			if (otherTariffNode == null) {
		        this.tariff = null;
		        this.link = null;
		    } else {
		        this.tariff = new Tariff(otherTariffNode.tariff);
		        this.link = null;
		    }
		}
		
		//Clone method
		public TariffNode clone(TariffNode otherTariffNode) {
			return new TariffNode(otherTariffNode);
		}
		
		//Getters (Keep ?)
		public double getMinimumTariff() {
			return tariff.getMinimumTariff();
		}
		
		//Setters
		public void setNewTariff(Tariff newTariff) {
			this.tariff = newTariff;
		}
		public void setNewLink(TariffNode newLink) {
			this.link = newLink;
		}
		
		//toString the tariff info
		@Override
		public String toString() {
		    return tariff.toString();
		}
		//Equals method
		public boolean equals(Object otherObject) {
			//Check if object is null
			if(otherObject == null) {
				return false;
			}
			//Check if object is a product
			if(this.getClass() != otherObject.getClass()) {
				return false;
			}
			//Create a product object since we are sure it is one at this point
			TariffNode otherTariffNode = (TariffNode) otherObject;
			
			//Return if it's equal or not
			return this.tariff.equals(otherTariffNode.tariff);
		}
	}
}