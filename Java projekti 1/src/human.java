
public class human {
	private String Name;
	private String Age;
	private car Owned_car;
	public human(String name, String age, car owned_car)
	{
		Name = name;
		Age = age;
		Owned_car = owned_car;
	}
	
	public String toString()
	{
		String str = "Name: " + Name + " Age: " + Age;
	    if(Owned_car != null)
	    {
	    	str += (" Car: " + Owned_car.toString());
	    }
	    return str;
	}
	
	public String toCSV()
	{
		String str =  Name + "," + Age;
	    if(Owned_car != null)
	    {
	    	str += ("," + Owned_car.toCSV());
	    }
	    return str;
	}
}
