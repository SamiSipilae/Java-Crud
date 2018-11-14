
public class car {
	private String Brand;
	private String Model;
	private String Number_plate;
	private String Year;
	
	public car(String brand, String model, String number_plate, String year)
	{
		Brand = brand;
		Model = model;
		Number_plate = number_plate;
		Year = year;
	}

	public String toString()
	{
		String str = "Brand: " + Brand + " Model: " + Model + " Year: " + Year +" Number plate: " + Number_plate;
		return str;
	}

	public String toCSV()
	{
		String str = Brand + "," + Model + "," + Number_plate  +"," + Year;
		return str;
	}
}
